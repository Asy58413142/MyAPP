package study.com.cn.day728_;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity {

    private GestureDetectorCompat detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initUI();
        detector = new GestureDetectorCompat(this, new GestureDetector.OnGestureListener() {
            //触摸屏幕按下瞬间的动作
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }
            //按下短暂的按压效果，但不一定产生长按的效果
            @Override
            public void onShowPress(MotionEvent e) {

            }
            //点击抬起的动作
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }
            //滑动的过程
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }
            //长按   在onShowPress之后执行
            @Override
            public void onLongPress(MotionEvent e) {

            }

            /**
             * //滑动的结果（切换）
             * @param e1  事件1
             * @param e2  事件2
             * @param velocityX
             * @param velocityY
             * @return
             */
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (e1.getX()-e2.getX()>120) {
                    startActivityAnimLeft();
                    Toast.makeText(BaseActivity.this, "左滑", Toast.LENGTH_SHORT).show();
                } else if (e2.getX() - e1.getX() > 120) {
                    startActivityAnimRight();
                    Toast.makeText(BaseActivity.this, "右滑", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将触摸事件交给探测者处理
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    protected abstract void initUI();

    /**
     * 左滑
     */
    public abstract void startActivityAnimLeft();

    /**
     * 右滑
     */
    public abstract void startActivityAnimRight();
}
