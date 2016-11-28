package study.com.cn.day729_animator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn;
    private Button btn1;
    private LinearLayout cart;
    private int[] benLocation;
    private int[] ben1Location;
    private int[] cartLocation;
    private PathMeasure pathMeasure;
    private float[] currentPoition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        btn1 = (Button) findViewById(R.id.btn1);

        cart = (LinearLayout) findViewById(R.id.cart);
        btn.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        benLocation = new int[2];
        ben1Location = new int[2];
        //获取商品1的起点坐标
        btn.getLocationInWindow(benLocation);
        //获取商品2的起点坐标
        btn1.getLocationInWindow(ben1Location);
        cartLocation = new int[2];
        //获取商品购物车的起点坐标
        cart.getLocationInWindow(cartLocation);


        float startX = benLocation[0]+btn.getWidth()/ 2;
        float startY = btn.getHeight() / 2;

        float endX = cartLocation[0] +cart.getWidth()/2-btn.getWidth()/2;
        float endY = cartLocation[1];


        Path path = new Path();
        path.moveTo(startX, startY);
        path.quadTo((startX + endX) / 2, startY, endX, endY);
        pathMeasure = new PathMeasure(path, false);
        ValueAnimator objectAnimator = ValueAnimator.ofFloat(0, pathMeasure.getLength()).setDuration(3000);
        objectAnimator.setInterpolator(new BounceInterpolator());
        objectAnimator.start();
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {//float... values 有几个值就会调用几次


                float animatedValue = (float) animation.getAnimatedValue();


                currentPoition = new float[2];
                pathMeasure.getPosTan(animatedValue, currentPoition, null);

                btn.setTranslationX(currentPoition[0]);
                btn.setTranslationY(currentPoition[1]);
//                btn.setAlpha(animatedValue);
//                btn.setScaleX(animatedValue);
//                btn.setScaleY(animatedValue);

//                btn.setX(30*animatedValue);
//                btn.setY((180/animatedValue) * (180/animatedValue) * 10 / 2);
//                btn1.setX(180*animatedValue);
//                btn1.setY((180/animatedValue) * (180/animatedValue) * 10 / 2);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    btn.setTranslationZ(animatedValue);
                }
//                btn.setRotation(animatedValue*360);
            }
        });


    }

    public void onClick3(View v) {

        Animator animator = AnimatorInflater.loadAnimator(MainActivity.this, R.animator.animator1);
        animator.setTarget(btn);
        animator.start();
    }

    public void onClick2(View v) {

        PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofFloat("rotationX", 0, 180);
        PropertyValuesHolder propertyValuesHolder1 = PropertyValuesHolder.ofFloat("rotationY", 0, 180);
        PropertyValuesHolder propertyValuesHolder2 = PropertyValuesHolder.ofFloat("alpha", 0, 1);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(btn, propertyValuesHolder, propertyValuesHolder1, propertyValuesHolder2);
        objectAnimator.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float input) {
                return 0;
            }
        });
        objectAnimator.setDuration(5000);
        objectAnimator.start();
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public void onClick1(View v) {


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofArgb(btn, "textColor", Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW);
            ObjectAnimator objectAnimator1 = ObjectAnimator.ofArgb(btn, "backgroundColor", Color.YELLOW, Color.GREEN, Color.BLUE, Color.RED);
            objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
            objectAnimator1.setRepeatMode(ValueAnimator.REVERSE);
            objectAnimator.setRepeatCount(5);
            objectAnimator1.setRepeatCount(5);
            objectAnimator.setDuration(1000);
            objectAnimator1.setDuration(1000);
            objectAnimator.start();
            objectAnimator1.start();
        }

    }
}
