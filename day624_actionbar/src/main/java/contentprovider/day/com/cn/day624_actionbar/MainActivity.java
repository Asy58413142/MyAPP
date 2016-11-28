package contentprovider.day.com.cn.day624_actionbar;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private ListView listview;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.ic_launcher);
        //设置上这两个属性以后才会显示出logo图片
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        //显示向左的箭头
        actionBar.setDisplayHomeAsUpEnabled(true);
        //让这个箭头可以点击
        actionBar.setHomeButtonEnabled(true);
        //利用loader加载短信数据
        getSupportLoaderManager().initLoader(0, null, this);
        listview = (ListView) findViewById(R.id.listview);
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                null, new String[]{"body", "phone"}, new int[]{android.R.id.text1,
                android.R.id.text2}, SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listview.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //找到SearchView
        MenuItem search_view = menu.findItem(R.id.search_view);
        SearchView view = (SearchView) MenuItemCompat.getActionView(search_view);
        //设置监听器
        view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //当输入内容发生变化时
                Bundle args=new Bundle();
                args.putString("search",newText);

                getSupportLoaderManager().restartLoader(0, args, MainActivity.this);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     *
     * @param id
     * @param args
     * @return
     */
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        if (args != null) {
            String search = args.getString("search");
            return new CursorLoader(this, Uri.parse("content://sms/"),null,"body like ? ",new String[]{"% "+search +" %"},null);
        }
        return new CursorLoader(this, Uri.parse("content://sms/"),null,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
