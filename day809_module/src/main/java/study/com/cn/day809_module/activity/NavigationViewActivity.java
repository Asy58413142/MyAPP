package study.com.cn.day809_module.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import study.com.cn.day809_module.R;
import study.com.cn.day809_module.utils.CircleTransform;

/**
 * Created by ann on 2016/8/11.
 */
public class NavigationViewActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.navigationview)
    NavigationView navigationView;
    @BindView(R.id.navigationview_drawerlayout)
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigationview);
        ButterKnife.bind(this);
        //获取头部的控件
        View view = navigationView.getHeaderView(0);
        ImageView header_img = (ImageView) view.findViewById(R.id.navigation_header_img);
        Picasso.with(this).load("http://img0.bdstatic.com/img/image/shouye/xiaoxiao/%E5%AE%A0%E7%89%A983.jpg")
                .transform(new CircleTransform())
                .placeholder(R.drawable.ic_child_care)
                .error(R.drawable.ic_close)
                .into(header_img);
        initToolBar();
        initDrawerLayout();
        navigationView.setNavigationItemSelectedListener(this);
//        navigationView.setItemBackground(); //设置菜单条目的背景
//        navigationView.setItemIconTintList(); //设置菜单图标的颜色，选中未选中
//        navigationView.setItemTextColor(); //设置菜单文字的颜色，选中未选中

    }

    private void initDrawerLayout() {

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.string_open, R.string.string_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);

    }

    private void initToolBar() {
        toolbar.setTitle("NavigationView");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        String name = null;
        switch (item.getItemId()) {
            case R.id.menu_navigation_view_item0:
                name= (String) item.getTitle();
                break;
            case R.id.menu_navigation_view_item3:
                name= (String) item.getTitle();
                break;
        }
        Toast.makeText(NavigationViewActivity.this,name, Toast.LENGTH_SHORT).show();
        item.setChecked(true);
        drawerLayout.closeDrawers();
        return true;
    }
}
