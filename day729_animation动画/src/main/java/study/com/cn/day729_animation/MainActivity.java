package study.com.cn.day729_animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button start;
    private TextView tv;
    private int x;
    private int y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tv.getLayoutParams();
        x = layoutParams.width;
        y = layoutParams.height;
        start = (Button) findViewById(R.id.btn);
        start.setOnClickListener(this);

    }

    public void onClick1(View x) {
//        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);

        RotateAnimation animation = new RotateAnimation(0,180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(3000);
        //设置重复次数
        animation.setRepeatCount(2);
        //Animation.RESTART返回到起点开始重复
        //Animation.REVERSE在结束为止开始重复
        animation.setRepeatMode(Animation.REVERSE);
//        tv.setAnimation(animation);
//        tv.invalidate();
//        animation.startNow();
        tv.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                scale.setDuration(3000);
                tv.startAnimation(scale);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

    }

    @Override
    public void onClick(View v) {
        View inflate = getLayoutInflater().inflate(R.layout.activity_main, null, false);
        PopupWindow popupWindow = new PopupWindow(inflate, 300, 200);
        popupWindow.setAnimationStyle(R.style.popstyle);
        popupWindow.showAtLocation(v, Gravity.CENTER,0,0);
    }
}
