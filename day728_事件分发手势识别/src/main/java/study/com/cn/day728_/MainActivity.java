package study.com.cn.day728_;

import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by ann on 2016/7/28.
 */
public class MainActivity extends BaseActivity {

    private GestureOverlayView gestureOverlayView;
    private ImageView img;
    private GestureLibrary library;


    @Override
    protected void initUI() {
        setContentView(R.layout.activity_main);

        if (Environment.getExternalStorageState() .equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(Environment.getExternalStorageDirectory(), "/0250");
            if (!file.exists()) {
                file.mkdir();
            }
            File  file1 = new File(file, "gesture");
            library = GestureLibraries.fromFile(file1);
        }







        img = (ImageView) findViewById(R.id.img);




        gestureOverlayView = (GestureOverlayView) findViewById(R.id.gov);
        //对手势经行配置
        //设置开启淡入淡出的效果
        gestureOverlayView.setFadeEnabled(true);
        //3秒内完成动作
        gestureOverlayView.setFadeOffset(3000);
        //设置颜色
        gestureOverlayView.setGestureColor(Color.BLUE);
        //设置手势宽度
        gestureOverlayView.setGestureStrokeWidth(10);
        //设置多笔画手势类型
        gestureOverlayView.setGestureStrokeType(GestureOverlayView.GESTURE_STROKE_TYPE_MULTIPLE);


        //添加监听事件
        //监听手势的过程
        gestureOverlayView.addOnGesturingListener(new GestureOverlayView.OnGesturingListener() {
            @Override
            public void onGesturingStarted(GestureOverlayView overlay) {
                //开始绘制手势
            }

            @Override
            public void onGesturingEnded(GestureOverlayView overlay) {
                //结束绘制
            }
        });
        //监听手势结果
        gestureOverlayView.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
                //将手势转化成bitmap
                Bitmap bitmap = gesture.toBitmap(200, 200, 10, Color.GREEN);
                img.setImageBitmap(bitmap);
                if (recognizeGesture(library, gesture)) {
                    Toast.makeText(MainActivity.this, "密码正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                library.addGesture("1", gesture);
                library.save();

            }
        });
    }

    @Override
    public void startActivityAnimLeft() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.in_1,R.anim.out_1);
    }

    @Override
    public void startActivityAnimRight() {

    }

    public boolean recognizeGesture(GestureLibrary library,Gesture gesture){
        if (library.load()) {
            ArrayList<Prediction> recognize = library.recognize(gesture);
            if (recognize != null) {
                for (int i = 0; i < recognize.size(); i++) {

                    if (recognize.get(i).score > 0.7f) {

                        return true;

                    } else {
                        return false;
                    }
                }
            }
            return false;
        }
        return false;
    }
}
