package study.com.cn.day805_;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.View;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;

/**
 * Created by ann on 2016/8/8.
 */
public class SlidingMenuExampleActivity extends SlidingActivity{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidingmenu);
        setTitle("SlidingMenuExampleActivity");
        setBehindContentView(R.layout.sliding_menu);
        SlidingMenu slidingMenu = getSlidingMenu();
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        slidingMenu.setBehindOffset((int) (metrics.widthPixels*.3));//默认为一屏
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setShadowWidth(R.dimen.slidingMenu_shadow_width);
        slidingMenu.setMode(SlidingMenu.RIGHT);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu, null);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
}
