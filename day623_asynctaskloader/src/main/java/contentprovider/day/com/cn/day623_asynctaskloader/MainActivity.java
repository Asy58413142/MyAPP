package contentprovider.day.com.cn.day623_asynctaskloader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, SwipeRefreshLayout.OnRefreshListener {

    private SimpleCursorAdapter adapter;
    private ListView listView;
    private SwipeRefreshLayout refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteHelper h = new SQLiteHelper(this);
        refresh = (SwipeRefreshLayout) findViewById(R.id.refresh);
        refresh.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary,R.color.colorPrimaryDark);
        refresh.setOnRefreshListener(this);
        SQLiteDatabase db = h.getWritableDatabase();
        ContentValues values = new ContentValues();
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, null, new String[]{"name", "score"}, new int[]{android.R.id.text1, android.R.id.text2},SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView = (ListView) findViewById(R.id.listView);
        onRefresh();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new SQLiteAsyncTaskLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
        listView.setAdapter(adapter);
        refresh.setRefreshing(false);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

    @Override
    public void onRefresh() {
        SQLiteHelper helper = new SQLiteHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
            db.delete("student", " name = ?", new String[]{"小明0"});
        getSupportLoaderManager().initLoader(0, null, this);
        adapter.notifyDataSetChanged();
    }

    /**
     * 1.创建 AsyncTaskLoader的子类
     * 2.重写 loadInBackground操作
     * 3.在 onStartLoading 中调用forceLoad 强制加载数据
     *
     * 4.必须用static 修饰
     */
    static class SQLiteAsyncTaskLoader extends android.support.v4.content.AsyncTaskLoader<Cursor>{

        public SQLiteAsyncTaskLoader(Context context) {
            super(context);
        }

        /**
         * 正在加载
         */
        @Override
        protected void onStartLoading() {
            super.onStartLoading();
            //强制加载数据
            forceLoad();
        }

        /**
         * 在工作线程里执行的加载操作
         * @return
         */
        @Override
        public Cursor loadInBackground() {

            SQLiteHelper helper = new SQLiteHelper(getContext());
            SQLiteDatabase db = helper.getWritableDatabase();
            //1.查出来的结果是否可以存在重复值  true 可以
            Cursor cursor = db.query(true, "student", null, null, null, null, null, null, null);
            return cursor;
        }


    }
}
