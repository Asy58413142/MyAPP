package study.com.cn.day805_;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * Created by ann on 2016/8/8.
 */
public class SlidingMenuExample extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_slidingmenu);
        //设置显示ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("SlidingMenu1");
        toolbar.setLogo(R.drawable.ic_action_amazon);
        setSupportActionBar(toolbar);
        // 设置是否能够使用ActionBar来滑动
        toolbar.setNavigationIcon(R.drawable.ic_action_add);

        //1.
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        final SlidingMenu slidingMenu = new SlidingMenu(this);
        //开启的位置 设置滑动菜单显示的位置（左边、右边或者左右两边都有）
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        //设置触摸屏幕的模式（全屏触摸打开滑动菜单、边缘触摸打开滑动菜单或者触摸不能打开滑动菜单）
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        //设置阴影的宽度
        slidingMenu.setShadowWidth(R.dimen.slidingMenu_shadow_width);
        //设置阴影的背景
        slidingMenu.setShadowDrawable(Color.GREEN);
        //设置滑动菜单的宽度(主界面在SlidingMenu打开后的宽度)
        slidingMenu.setBehindOffset((int) (metrics.widthPixels*.3));
        //设置渐入渐出的效果
        slidingMenu.setFadeDegree(0.35f);

        slidingMenu.attachToActivity(this,SlidingMenu.SLIDING_WINDOW);
        slidingMenu.setMenu(R.layout.sliding_menu);



        slidingMenu.setOnOpenListener(new SlidingMenu.OnOpenListener() {
            @Override
            public void onOpen() {
                float behindScrollScale = slidingMenu.getBehindScrollScale();
            }
        });
        slidingMenu.setSecondaryOnOpenListner(new SlidingMenu.OnOpenListener() {
            @Override
            public void onOpen() {

            }
        });

    }
}
