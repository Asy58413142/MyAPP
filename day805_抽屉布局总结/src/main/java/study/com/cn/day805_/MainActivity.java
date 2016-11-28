package study.com.cn.day805_;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ListView drawer_menu;
    private LinearLayout container;
    private List<String> menuName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        DrawerMenu2Data();
        toolbar.setTitle("Chou");
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                Log.e("===========", "onDrawerSlide: /"+slideOffset );
                openDrawerAnim(drawerView,slideOffset);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
    }

    private void initUI() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //设置DrawerLayout的宽度
        LinearLayout drawer_left = (LinearLayout) findViewById(R.id.drawer_left);
        ViewGroup.LayoutParams layoutParams = drawer_left.getLayoutParams();
        layoutParams.width = getDrawerLayoutWidth();
        drawer_left.setLayoutParams(layoutParams);
        //用于偏移
        container = (LinearLayout) findViewById(R.id.container);
        //DrawerLayout属性的设置
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);//阴影颜色

        //listView的设置
        drawer_menu = (ListView) findViewById(R.id.drawer_menu);
        drawer_menu.setOnItemClickListener(this);
    }

    public void DrawerMenu2Data() {
        View view = getLayoutInflater().inflate(R.layout.listiview_header_view, null);
        drawer_menu.addHeaderView(view);
        menuName = new ArrayList<String>();
        menuName.add("SlidingMenuExample");
        menuName.add("SlidingMenuExampleActivity");
        menuName.add("SlidingMenuExampleXml");
        menuName.add("ToolBar");
        drawer_menu.setAdapter(new DrawerMenuAdapter(this, R.layout.drawer_menu_item, R.id.drawer_menu_item_text, menuName));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_action_drawLayout:
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public int getDrawerLayoutWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        MainActivity.this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return (int) (metrics.widthPixels * .6f);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
        if (position!=0) {
            drawerLayout.closeDrawer(GravityCompat.START);
            menuName.get(position-1);
            startActivity(menuName.get(position-1));
        }

    }

    private void startActivity(String s) {
        try {
            Intent intent = new Intent(MainActivity.this, Class.forName("study.com.cn.day805_."+s));
            MainActivity.this.startActivity(intent);
        } catch (ClassNotFoundException e) {
            Toast.makeText(MainActivity.this, "没有找到"+s, Toast.LENGTH_SHORT).show();
        }
    }


    private void openDrawerAnim(View drawerView, float offset) {
        View mContent = drawerLayout.getChildAt(0);
        View mMenu = drawerView;
        float scale = 1 - offset;
        container.setX(mMenu.getMeasuredWidth() * (1 - scale));
        mContent.invalidate();
    }
}
