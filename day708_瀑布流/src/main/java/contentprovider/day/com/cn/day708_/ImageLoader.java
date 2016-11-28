package contentprovider.day.com.cn.day708_;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ann on 2016/7/12.
 */
public class ImageLoader {
    public static void loadImage(final Activity context, final ImageView imageView, final String image) {

        new Thread(){
            @Override
            public void run() {
                super.run();

                try {
                    InputStream inputStream = context.getAssets().open("images/" + image);
                    final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    /**图片太大会内存溢出
                     *
                     */
                    inputStream.close();
                    //1.
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            imageView.setImageBitmap(bitmap);
//                        }
//                    });

                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
                            layoutParams.height = bitmap.getHeight() / bitmap.getWidth()*layoutParams.width;
                            imageView.setLayoutParams(layoutParams);
                            imageView.setImageBitmap(bitmap);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
