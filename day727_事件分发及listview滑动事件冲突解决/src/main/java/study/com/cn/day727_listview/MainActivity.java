package study.com.cn.day727_listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.listview)
    ListView listview;
    @ViewInject(R.id.ll_top)
    LinearLayout ll_top;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        com.lidroid.xutils.ViewUtils.inject(this);
        ll_top.setFocusable(true);
        ll_top.setFocusableInTouchMode(true);
        ll_top.requestFocus();
        List<Map<String,String >> stringList = new ArrayList<Map<String,String >>();
        for (int i = 0; i < 15; i++) {
            Map<String, String> map = new HashMap<String,String>();

            map.put("key", i + "ge");

            stringList.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, stringList,R.layout.item,new String[]{"key"},new int[]{R.id.text});
        listview.setAdapter(adapter);
        setListViewHeight(listview);
    }

    public void setListViewHeight(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        if (adapter == null) {
            return;
        }
        //获取数据的个数
        int count = adapter.getCount();
        int countHeight = 0;

        for (int i = 0; i < count; i++) {
            View view = adapter.getView(i, null, listView);
            //动态测量单条item的高度
            view.measure(0,0);
            //获取每一个的高度
            int height = view.getMeasuredHeight();
            countHeight += height;
        }

        int dividerHeight = listView.getDividerHeight()*(count-1);
        int allHeight = countHeight + dividerHeight;

        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        layoutParams.height = allHeight;
        listView.setLayoutParams(layoutParams);
    }
    @OnItemClick(R.id.listview)
    public void ItemClick(AdapterView<?> adapterView , View prent,int position,long id) {
        Log.e( "ItemClick:======= "+position,"++++++++++" );
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("=========", "MainActivity=======onTouchEvent========== " );
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("=========", "MainActivity=======dispatchTouchEvent========== " );
        return super.dispatchTouchEvent(ev);
    }
}
