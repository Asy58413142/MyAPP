package study.com.cn.day805_;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * Created by ann on 2016/8/8.
 */
public class SlidingMenuExampleXml extends AppCompatActivity {


    private SlidingMenu slidingMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidingmenu1);
        initToolBar();

        initSlidingMenu();

    }

    private void initSlidingMenu() {

        DisplayMetrics metrics = new DisplayMetrics();
        SlidingMenuExampleXml.this.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        slidingMenu = (SlidingMenu) findViewById(R.id.slidingmenu);
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        slidingMenu.setMenu(R.layout.sliding_menu);
        slidingMenu.setSecondaryMenu(R.layout.sliding_menu);
        slidingMenu.setBehindWidth((int) (metrics.widthPixels * .3));
        slidingMenu.setFadeDegree(0.35f);
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("SlidingMenuExampleXml");
        SlidingMenuExampleXml.this.setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_action_slidingMenu1:

                if (slidingMenu.isMenuShowing()) {
                    slidingMenu.toggle();
                } else {
                    slidingMenu.showMenu();
                }
                break;
            case R.id.menu_action_slidingMenu:

                if (slidingMenu.isMenuShowing()) {
                    slidingMenu.toggle();
                } else {
                    slidingMenu.showSecondaryMenu();
                }
                break;
        }
        return super.onContextItemSelected(item);
    }
}
