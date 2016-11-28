package contentprovider.day.com.cn.day623_cursorloader;

import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private ListView listView;
    private String uri = "content://contentprovider.day.com.cn.day618_sqlite/student";
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1.初始化loaderManager对象
        getSupportLoaderManager().initLoader(0, null, this);
        listView = (ListView) findViewById(R.id.listview);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        //2.创建CursorLoader对象
        //当查询的数据量大的时候需要异步加载，需要使用loader加载

        return new CursorLoader(this, Uri.parse(uri),null,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        //3.获取完数据的时候

        adapter = new SimpleCursorAdapter
                (this, android.R.layout.simple_list_item_2, data,
                        new String[]{"name","age"}, new int[]{android.R.id.text1, android.R.id.text2},
                        SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(adapter);
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        //4.清空原有的数据
        adapter.swapCursor(null);
    }
}
