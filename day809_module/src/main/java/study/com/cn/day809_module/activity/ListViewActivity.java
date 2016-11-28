package study.com.cn.day809_module.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import study.com.cn.day809_module.R;
import study.com.cn.day809_module.adapter.ListViewAdapter;

/**
 * Created by ann on 2016/8/10.
 */
public class ListViewActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.listview_lv)
    ListView listView;
    private List<String> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ButterKnife.bind(this);
        initToolBar();
        initListViewData();
        listView.setAdapter(new ListViewAdapter(this,list));
    }

    private void initListViewData() {
        list = new ArrayList<>();
        list.add("ScrollView与ListView滑动冲突——固定控件大小");
        list.add("ScrollView与ListView滑动冲突——动态获取ListView的高度");
        list.add("ScrollView与ListView滑动冲突——重写ListView");
        list.add("ScrollView与ListView滑动冲突——事件分发");
    }

    private void initToolBar() {
        toolbar.setTitle("ListView");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_keyboard_arrow_left));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ListViewActivity.this.finish();
                overridePendingTransition(0,R.anim.close_activity);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
