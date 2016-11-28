package study.com.cn.day809_module.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import study.com.cn.day809_module.R;
import study.com.cn.day809_module.adapter.ViewPagerFragmentAdapter;
import study.com.cn.day809_module.fragment.JVHeFragment;
import study.com.cn.day809_module.fragment.MonkeyFragment;
import study.com.cn.day809_module.fragment.SnowFragment;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {


    @BindView(R.id.main_viewpager_container)
    ViewPager viewPager_container;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.main_viewpager_drawer)
    LinearLayout viewpager_drawer;
    @BindView(R.id.bottom_menu)
    RadioGroup menuGroup;
    private long currentTime;//用于保存退出的时间


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolBar();
        initViewPager();
        initDrawerLayout();

    }


    private void initViewPager() {
        menuGroup.setOnCheckedChangeListener(this);
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new JVHeFragment());
        fragments.add(new SnowFragment());
        fragments.add(new MonkeyFragment());
        viewPager_container.addOnPageChangeListener(this);
        viewPager_container.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager(), fragments));
    }

    /**
     * 初始化DrawerLayout
     */
    private void initDrawerLayout() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        ViewGroup.LayoutParams layoutParams =  viewpager_drawer.getLayoutParams();
        layoutParams.width = (int) (metrics.widthPixels * .7);
        viewpager_drawer.setLayoutParams(layoutParams);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
    }

    /**
     * 初始化ToolBar
     */
    public void initToolBar() {
        toolbar.setTitle("聚合");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.string_open, R.string.string_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                startSlideAnim(drawerView, slideOffset);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);
    }

    /**
     * 滑动的时候主界面区域跟着滑动
     *
     * @param drawerView
     * @param slideOffset
     */
    private void startSlideAnim(View drawerView, float slideOffset) {
        View container = drawerLayout.getChildAt(0);
        View drawer = drawerView;
        float offset = 1 - slideOffset;
        container.setX(drawer.getMeasuredWidth() * (1 - offset));
        container.invalidate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_main_close:
                if (System.currentTimeMillis() - currentTime < 2000) {
                    MainActivity.this.finish();
                } else {
                    Toast.makeText(MainActivity.this, "再点一次，即可退出程序", Toast.LENGTH_SHORT).show();
                    currentTime = System.currentTimeMillis();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.bottom_menu_jvhe:
                viewPager_container.setCurrentItem(0);
                getSupportActionBar().setTitle(R.string.string_jh);
                break;
            case R.id.bottom_menu_snow:
                viewPager_container.setCurrentItem(1);
                getSupportActionBar().setTitle(R.string.string_snow);
                break;
            case R.id.bottom_menu_monkey:
                viewPager_container.setCurrentItem(2);
                getSupportActionBar().setTitle(R.string.string_monkey);
                break;
        }
    }
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - currentTime < 2000) {
            MainActivity.this.finish();
        } else {
            Toast.makeText(MainActivity.this, "再点一次，即可退出程序", Toast.LENGTH_SHORT).show();
            currentTime = System.currentTimeMillis();
        } 
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //更改toolbar  text
        switch (position) {
            case 0:
                getSupportActionBar().setTitle(R.string.string_jh);
                break;
            case 1:
                getSupportActionBar().setTitle(R.string.string_snow);
                break;
            case 2:
                getSupportActionBar().setTitle(R.string.string_monkey);
                break;
        }
        //更改bottomMenu选中
        int count = menuGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            RadioButton childAt = (RadioButton) menuGroup.getChildAt(i);
            if (position == i) {
                childAt.setChecked(true);
            } else {
                childAt.setChecked(false);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
