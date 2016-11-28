package contentprovider.day.com.cn.day618_sqlite;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listview = (ListView) findViewById(R.id.listview);
        SQLDBHandler handler = new SQLDBHandler(this);
        handler.insert("你", 18, 88);
        handler.insertAPI("我", 19, 99);
        handler.insertAPI("他", 444, 66);
        handler.insertAPI("ta", 444, 1);
        handler.insertAPI("ta", 444, 66);
//        handler.delete("我");
//        handler.deleteAPI("你");
        handler.upDate("他", "66");
        handler.upDateAPI("她", "1");
        ArrayList<Student> query = handler.query();
        Log.d("Main", String.valueOf(query));
        ArrayList<Student> list = handler.queryAPI();
        Log.d("Main1", String.valueOf(list));


        Cursor cursor = handler.queryAPI1();
        //db  和cursor不能关闭  否则报错
        listview.setAdapter(new SQLAdapter(this,cursor,CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER));
    }

    //建表的时候必须有“_id”这个字段，否则会停止工作
    class SQLAdapter extends CursorAdapter {
        //推荐使用这个构造器
        public SQLAdapter(Context context, Cursor c, int flags) {
            super(context, c, flags);
        }

        //生成itemView
        //继承自BaseAdapter，自动实现了view复用
        //一个屏幕显示几个item就newView几次
        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {

            return getLayoutInflater().inflate(android.R.layout.simple_list_item_2, null);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            TextView textView2 = (TextView) view.findViewById(android.R.id.text2);
            String string = cursor.getString(cursor.getColumnIndex(MySQLiteDatabaseHelper.CL_NAME));
            textView.setText(string);
            int age = cursor.getInt(cursor.getColumnIndex(MySQLiteDatabaseHelper.CL_AGE));
            int score = cursor.getInt(cursor.getColumnIndex(MySQLiteDatabaseHelper.CL_SCORE));
            textView2.setText(age+"岁" + score+"分");

        }
    }

}
