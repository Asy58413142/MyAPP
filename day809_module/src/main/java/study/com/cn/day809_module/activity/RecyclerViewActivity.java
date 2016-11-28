package study.com.cn.day809_module.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import study.com.cn.day809_module.R;
import study.com.cn.day809_module.adapter.RecyclerViewAdapter;

/**
 * Created by ann on 2016/8/10.
 */
public class RecyclerViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public List<String> popMenus ;
    public List<String> url = new ArrayList<>();
    public RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);//默认为LinearLayout
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview_container)
    RecyclerView recyclerView;
    private DisplayMetrics metrics;
    private PopupWindow popupWindow;
    private int width;
    private int height;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        ButterKnife.bind(this);
        initToolBar();
        initUrlData();
        initRecyclerView();
        initPopData();
    }

    private void initUrlData() {
        url.add("http://image6.huangye88.com/2013/03/28/2a569ac6dbab1216.jpg");
        url.add("http://img1.3lian.com/2015/w2/59/d/47.jpg");
        url.add("http://img1.imgtn.bdimg.com/it/u=13148197,3827988600&fm=21&gp=0.jpg");
        url.add("http://news.ename.cn/data/article/201509/24/1443088866.png");
        url.add("http://img.pconline.com.cn/images/upload/upc/tx/ladybbs6/1211/07/c0/15432545_1352271606796_8_1024x1024.jpg");
        url.add("http://img.boqii.com/Data/BK/A/1303/5/img42011362467613_y.jpg");
        url.add("http://pic34.nipic.com/20131028/2455348_170839616000_2.jpg");
        url.add("http://img5q.duitang.com/uploads/item/201502/15/20150215052215_HjeHP.thumb.700_0.jpeg");
        url.add("http://img5.niutuku.com/shoujibizhi/2117/2117-26106.jpg");
        url.add("http://hiphotos.baidu.com/zhiduinishengqing/pic/item/ee6da3ffde44485c6c22eb6b.jpg");

    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(new RecyclerViewAdapter(this, url,width,height));
    }

    private void initPopData() {
        popMenus = new ArrayList<>();
        popMenus.add("横向ListView");
        popMenus.add("竖向ListView");
        popMenus.add("横向GridView");
        popMenus.add("竖向GridView");
        popMenus.add("瀑布流");
    }

    private void initToolBar() {
        toolbar.setTitle("RecyclerView");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_keyboard_arrow_left));
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;
        height = (int) (metrics.heightPixels * .5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recyclerview, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                RecyclerViewActivity.this.finish();
                //关闭动画
                overridePendingTransition(0,R.anim.close_activity);
                break;

            case R.id.menu_recyclerview_change:
                openPop();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openPop() {
        popupWindow = new PopupWindow(this);
        popupWindow.setWidth((int) (metrics.widthPixels * .5));
        popupWindow.setHeight((int) (metrics.heightPixels * .5));
        View view = getLayoutInflater().inflate(R.layout.pop_recycleyview, null);
        popupWindow.setContentView(view);
        popupWindow.setAnimationStyle(R.style.PopRecyclerAnim);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        ListView popMenu = (ListView) view.findViewById(R.id.pop_recyclerview_lv);
        popMenu.setAdapter(new ArrayAdapter(RecyclerViewActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1,popMenus));
        popMenu.setOnItemClickListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            popupWindow.showAsDropDown(toolbar, 0, 0, Gravity.RIGHT);
        } else {
            popupWindow.showAtLocation(toolbar,Gravity.TOP|Gravity.RIGHT,0,android.R.attr.actionBarSize);
        }
    }

    public <T extends View> T getView(int viewId) {
        return (T) this.findViewById(viewId);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        height = (int) (metrics.heightPixels * .5);
        switch (position) {
            case 0:
                width = (int) (metrics.widthPixels * .5);
                layout=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
                initRecyclerView();

                break;
            case 1:
                width = metrics.widthPixels;
                layout=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
                initRecyclerView();
                break;
            case 2:
                width = (int) (metrics.widthPixels * .5);
                layout=new GridLayoutManager(this,2,LinearLayoutManager.HORIZONTAL, false);
                initRecyclerView();
                break;
            case 3:
                width = (int) (metrics.widthPixels * .5);
                layout=new GridLayoutManager(this,2,LinearLayoutManager.VERTICAL, false);
                initRecyclerView();
                break;
            case 4:
                width = (int) (metrics.widthPixels /3);
                height = 3;
                layout = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
                initRecyclerView();
                break;

        }
        popupWindow.dismiss();
    }
}
