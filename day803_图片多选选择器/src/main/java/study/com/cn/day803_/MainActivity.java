package study.com.cn.day803_;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, recyclerViewAdapter.onSelectNumChangeListener, PopupWindow.OnDismissListener {

    private RecyclerView showAllPic;
    private TextView picName;
    private TextView picNum;
    private int MAX_NUM = 9;//最多选择几张照片
    int selectNum=0;//选中的个数
    private int mMaxCount;
    private File mCurrentDir;
    private ProgressDialog progressDialog;
    private ArrayList<ImageFloder> mImageList = new ArrayList<ImageFloder>();
    private recyclerViewAdapter adapter;
    private List<String> mImgs=new ArrayList<>();//扫描到的数据
    private List<String> mImgsAll = new ArrayList<String>();//处理后的数据
    private String dirPath;
    private PopupWindow popupWindow;
    private TextView checkNum;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0X110) {
                //绑定数据到view中
                BindDataFromView();
                progressDialog.dismiss();
            }
        }
    };

    /**
     * 设置数据
     */
    private void BindDataFromView() {
        if (mCurrentDir == null) {
            Toast.makeText(MainActivity.this, "没查找到图片", Toast.LENGTH_SHORT).show();
            return;
        } else {
            picName.setText(mCurrentDir.getName());
            picNum.setText(mMaxCount + "");
            mImgsAll.add("first");//第一个照相动能，不需要删除
            mImgs = Arrays.asList(mCurrentDir.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String filename) {
                    if (filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith("png")) {
                        return true;
                    }
                    return false;
                }
            }));
            mImgsAll.addAll(mImgs);
                adapter = new recyclerViewAdapter(MainActivity.this, mImgsAll, mCurrentDir.getAbsolutePath(),MAX_NUM,this);
                showAllPic.setAdapter(adapter);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initData();
    }


    public void initData() {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

        } else {
            Toast.makeText(MainActivity.this, "存储卡不存在", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog = ProgressDialog.show(this, "", "正在加载");
        new Thread(new Runnable() {
            @Override
            public void run() {

                Uri mImgUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

                ContentResolver contentResolver = MainActivity.this.getContentResolver();


                // 只查询jpeg和png的图片
                Cursor mCursor = contentResolver.query(mImgUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or "
                                + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png"},
                        MediaStore.Images.Media.DATE_MODIFIED);
                Set<String> mDirPaths = new HashSet<String>();
                while (mCursor.moveToNext()) {
                    String path =
                            mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null) {
                        continue;
                    }

                    dirPath = parentFile.getAbsolutePath();

                    ImageFloder imageFloder = null;

                    if (mDirPaths.contains(dirPath)) {
                        continue;

                    } else {
                        mDirPaths.add(dirPath);
                        imageFloder = new ImageFloder();
                        imageFloder.setDir(dirPath);
                        imageFloder.setFirstImagePath(path);
                    }

                    if (parentFile.list() == null) {
                        continue;
                    }
                    int picSize = parentFile.list(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String filename) {

                            if (filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith("png")) {
                                return true;
                            }
                            return false;
                        }

                    }).length;

                    imageFloder.setCount(picSize);
                    mImageList.add(imageFloder);

                    if (picSize > mMaxCount) {
                        mMaxCount = picSize;
                        mCurrentDir = parentFile;
                    }
                }
                mCursor.close();
                //扫描完成清空
                mDirPaths.clear();
                handler.sendEmptyMessage(0X110);

            }

        }).start();

    }

    public void initUI() {
        //用于显示所有的图片
        showAllPic = (RecyclerView) findViewById(R.id.recycler_allPic);
        //用于显示相册的名字
        picName = (TextView) findViewById(R.id.tv_pic_name);
        picName.setOnClickListener(this);
        //用于显示图片的数量
        picNum = (TextView) findViewById(R.id.tv_pic_num);
        //选择了几个
        checkNum = (TextView) findViewById(R.id.checkNum);
        checkNum.setText("("+selectNum+"/9)");
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        showAllPic.setLayoutManager(layoutManager);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_pic_name:

                if (mImageList != null) {
                    showSelectPop(v);
                    onLightOff();
                }
                break;
        }
    }

    /**
     * 选择文件夹的pop
     * @param v
     */
    private void showSelectPop(View v) {
        popupWindow = new PopupWindow(MainActivity.this);
        View view = View.inflate(MainActivity.this,R.layout.pop_checkdir, null);
        popupWindow.setAnimationStyle(R.style.PopAnim);
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) MainActivity.this.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        popupWindow.setWidth(metrics.widthPixels);
        popupWindow.setHeight((int) (metrics.heightPixels*0.6));
        popupWindow.setContentView(view);
        ListView showDir = (ListView) view.findViewById(R.id.pop_check_picDir);
        showDir.setAdapter(new PopAdapter(mImageList, MainActivity.this, new PopAdapter.onSelectChangeListener() {
            @Override
            public void onChangeListener(ImageFloder imageFloder) {
                if (imageFloder!=null) {//
                    mCurrentDir = new File(imageFloder.getDir());
                    mMaxCount = imageFloder.getCount();
                    mImgsAll.clear();
                    BindDataFromView();
                }
                popupWindow.dismiss();
            }
        }));
        //设置点击
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            popupWindow.showAtLocation(showAllPic, Gravity.BOTTOM, 0, v.getHeight());
        }
        popupWindow.setOnDismissListener(this);
    }

    /**
     * 高亮
     */
    public  void onLightOn(){
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 1.0f;
        getWindow().setAttributes(attributes);
    }

    /**
     * 阴影
     */
    public void onLightOff() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = .3f;
        getWindow().setAttributes(attributes);
    }

    @Override
    public void onNumAdd() {
        selectNum++;
        checkNum.setText("("+selectNum+"/9)");
    }

    @Override
    public void onNumDown() {
        selectNum--;
        checkNum.setText("("+selectNum+"/9)");
    }

    @Override
    public void onDismiss() {
        onLightOn();
    }
}
