package contentprovider.day.com.cn.day714_stickylistheaderslistview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class MainActivity extends AppCompatActivity {

    private StickyListHeadersListView stickyListHeadersListView;
    private List<Group> groupList;
    private List<Item> itemList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        groupList = new ArrayList<Group>();
        for (int i = 0; i < 10; i++) {
            groupList.add(new Group(i, "分组" + (i + 1)));
        }
        itemList1 = new ArrayList<Item>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                itemList1.add(new Item(i, "我的好友" + j, "第" + (i + 1) + "组，好友：" + j));
            }
        }
        stickyListHeadersListView = (StickyListHeadersListView) findViewById(R.id.stickListHeaderListView);

        stickyListHeadersListView.setAdapter(new stickListHeaderListViewAdapter());
    }


    class stickListHeaderListViewAdapter extends BaseAdapter implements StickyListHeadersAdapter{


        @Override
        public int getCount() {
            return itemList1.size();
        }

        @Override
        public Item getItem(int position) {
            return itemList1.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = getLayoutInflater().inflate(android.R.layout.simple_list_item_2, null);
            TextView textView1 = (TextView) convertView.findViewById(android.R.id.text1);
            TextView textView2 = (TextView) convertView.findViewById(android.R.id.text2);


            textView1.setText(itemList1.get(position).getItemName());
            textView2.setText(itemList1.get(position).getItemInfo());
            return convertView;
        }

        /**
         * 设置头视图
         * @param position  所属组的id
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getHeaderView(int position, View convertView, ViewGroup parent) {

            View view = LayoutInflater.from(MainActivity.this).inflate(android.R.layout.simple_list_item_1, null);
            view.setBackgroundColor(Color.DKGRAY);
            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setText(groupList.get(itemList1.get(position).getGroupId()).getGroupName());
            return view;
        }

        /**
         * 分组
         * @param position  所属组的id
         * @return
         */
        @Override
        public long getHeaderId(int position) {
            return itemList1.get(position).getGroupId();
        }
    }
}

