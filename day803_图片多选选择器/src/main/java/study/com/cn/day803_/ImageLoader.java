package study.com.cn.day803_;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.LruCache;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by ann on 2016/8/3.
 */
public class ImageLoader {
    private static final int DEFAULT_THREAD_COUNT = 1;
    private static ImageLoader mInstance;
    //图片缓存对象
    private LruCache<String, Bitmap> mLruCache;
    //线程池
    private ExecutorService mThreadPool;
    //队列的调度方式
    private Type mType = Type.LIFO;


    //队列任务

    private LinkedList<Runnable> mTaskQueue;

    //后台轮询线程

    private Thread mPoolThread;

    private Handler mPoolThreadHandler;

    private Handler mUIHandler;
    private Semaphore mSemaphorePoolThreadHandler = new Semaphore(0);//信号量并发
    private Semaphore mSemaphoreThreadPool;//信号量并发

    /**
     * 单例构造
     *
     * @param threadCount
     * @param type
     */
    public ImageLoader(int threadCount, Type type) {
        init(threadCount, type);
    }

    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader(DEFAULT_THREAD_COUNT, Type.FIFO);
                }
            }
        }

        return mInstance;
    }

    public static ImageLoader getInstance(int threadCount, Type type) {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader(threadCount, type);
                }
            }
        }

        return mInstance;
    }


    /**
     * 通过反射获得对象的某个属性值
     *
     * @param o
     * @param fieldName
     * @return
     */
    public static int getImageFieldValue(Object o, String fieldName)  {
        int value = 0;
        try {
            Field field = ImageView.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            int fieldValue = field.getInt(o);
            if (fieldValue > 0 && fieldValue < Integer.MAX_VALUE) {
                value = fieldValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private void init(int threadCount, Type type) {

        mPoolThread = new Thread() {
            @Override
            public void run() {
                Looper.prepare();

                mPoolThreadHandler =  new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        //在线程池取出一个任务执行
                        mThreadPool.execute(getTasks());
                        try {
                            mSemaphoreThreadPool.acquire();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //释放信号量
                mSemaphorePoolThreadHandler.release();
                Looper.loop();
            }
        };

        mPoolThread.start();

            int maxMemory = (int) Runtime.getRuntime().maxMemory();
            int cacheMemory = maxMemory / 8;
            mLruCache = new LruCache<String, Bitmap>(cacheMemory) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes() * value.getHeight();
                }
            };

        mThreadPool = Executors.newFixedThreadPool(threadCount);

        mTaskQueue = new LinkedList<Runnable>();

        mType = type;

        mSemaphoreThreadPool = new Semaphore(0);
    }

    private Runnable getTasks() {

        if (mType == Type.FIFO) {
            return mTaskQueue.removeFirst();
        } else if (mType == Type.LIFO) {
            return mTaskQueue.removeLast();
        }
        return null;
    }

    public void loadImage(final String path, final ImageView imageView) {
        imageView.setTag(path);
        if (mUIHandler == null) {
            mUIHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    //获取的到图片，为imageview回调设置图片
                    ImageBeanHolder holder = (ImageBeanHolder) msg.obj;
                    Bitmap bm = holder.bitmap;
                    ImageView imageView = holder.imageView;
                    String path = holder.path;
                    //将path与getTag存储路径比对
                    if (imageView.getTag().toString().equals(path)) {
                        imageView.setImageBitmap(bm);
                    }
                }
            };
        }

        //根据path获取缓存中的图片
        Bitmap bitmap = getBitmapFromLurCache(path);

        if (bitmap != null) {
            refreashBitmap(path, imageView, bitmap);
        } else {
            addTask(new Runnable() {
                @Override
                public void run() {

                    //加载图片
                    //图片的压缩
                    //1.获取图片显示的大小
                    ImageSize imageSize = getImageViewSize(imageView);

                    //2.压缩图片

                    Bitmap bitmap = decodeSampledBitmapFromPath(path, imageSize.width, imageSize.height);

                    //3.加入到缓存中
                    addBitmapToLurCache(path, bitmap);

                    refreashBitmap(path, imageView, bitmap);
                    mSemaphoreThreadPool.release();
                }
            });
        }
    }

    /**
     * 发送消息
     *
     * @param path
     * @param imageView
     * @param bitmap
     */
    public void refreashBitmap(String path, ImageView imageView, Bitmap bitmap) {
        Message message = Message.obtain();

        ImageBeanHolder beanHolder = new ImageBeanHolder();
        beanHolder.bitmap = bitmap;
        beanHolder.imageView = imageView;
        beanHolder.path = path;
        message.obj = beanHolder;
        mUIHandler.sendMessage(message);
    }

    /**
     * 将图片加载到缓存中
     *
     * @param path
     * @param bitmap
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private void addBitmapToLurCache(String path, Bitmap bitmap) {
        if (getBitmapFromLurCache(path) == null) {
            if (bitmap != null) {
                mLruCache.put(path, bitmap);
            }
        }
    }

    /**
     * 根据图片需要显示的宽和高进行压缩
     *
     * @param path
     * @param width
     * @param height
     * @return
     */
    private Bitmap decodeSampledBitmapFromPath(String path, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//为了获取图片的大小但是不把图片加载到内寸中

        BitmapFactory.decodeFile(path, options);

        options.inSampleSize = caculateInSampleSize(options, width, height);

        //获得到inSampleSize再次解析图片
        options.inJustDecodeBounds = false;

        Bitmap bitmap = BitmapFactory.decodeFile(path, options);


        return bitmap;
    }

    /**
     * 计算缩方比例
     *
     * @param options
     * @param width
     * @param height
     * @return
     */
    private int caculateInSampleSize(BitmapFactory.Options options, int width, int height) {
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        int inSampleSize = 1;

        if (outWidth > width || outHeight > height) {
            int widthRadio = Math.round(outWidth * 1.0f / width);
            int heightRadio = Math.round(outHeight * 1.0f / height);
            inSampleSize = Math.max(width, height);
        }
        return inSampleSize;
    }

    /**
     * 获取图片的大小
     *
     * @param imageView
     * @return
     */
    private ImageSize getImageViewSize(ImageView imageView) {
        ImageSize imageSize = new ImageSize();

        DisplayMetrics displayMetrics = imageView.getContext().getResources().getDisplayMetrics();
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();


        //宽
        int width = imageView.getWidth();
        if (width <= 0) {
            width = layoutParams.width;
        }
        if (width <= 0) {
            width = getImageFieldValue(imageView,"mMaxWidth");
        }
        if (width <= 0) {
            width = displayMetrics.widthPixels;
        }

        int height = imageView.getWidth();
        if (height <= 0) {
            height = layoutParams.height;
        }
        if (height <= 0) {
            height =getImageFieldValue(imageView,"mMaxHeight");
        }
        if (height <= 0) {
            height = displayMetrics.heightPixels;
        }

        imageSize.height = height;
        imageSize.width = width;

        return imageSize;
    }

    private synchronized void addTask(Runnable runnable) {
        mTaskQueue.add(runnable);

        try {
            if (mPoolThreadHandler == null)
                mSemaphorePoolThreadHandler.acquire();
        } catch (InterruptedException e) {


        }

        mPoolThreadHandler.sendEmptyMessage(0X110);
    }

    /**
     * 从缓存中获取图片
     *
     * @param path
     * @return
     */
    private Bitmap getBitmapFromLurCache(String path) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            return mLruCache.get(path);
        } else {
            return null;
        }
    }

    enum Type {
        LIFO, FIFO;
    }

    public class ImageBeanHolder {
        Bitmap bitmap;

        ImageView imageView;

        String path;
    }

    public class ImageSize {
        int width;
        int height;
    }

}
