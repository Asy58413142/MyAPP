package contentprovider.day.com.cn.day617_ex;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

/**
 * Created by ann on 2016/6/17.
 */
public class OneFragment extends ListFragment {

    long currTime;
    private File one_file;
    private File[] one_listFiles;
    private File[] nextFile;
    private OneDataAdapter adapter;
    private File back = null;
    private File parentFile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            one_file = Environment.getExternalStorageDirectory();

        } else {
            Log.d("ExternalStorage", "no");
        }

        one_listFiles = one_file.listFiles();
        adapter = new OneDataAdapter();
        setListAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setEmptyText("NO Data");
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        File file = one_listFiles[position];
        if (file.isDirectory()) {
            nextFile = file.listFiles();
            if (nextFile.length == 0) {
                back = file.getParentFile();
            }
            one_listFiles = nextFile;
            adapter.notifyDataSetChanged();
        }
    }

    public void onBackPressed() {
        if (back == null) {
            parentFile = one_listFiles[0].getParentFile().getParentFile();
        } else {
            parentFile = back;
            back = null;
        }
        if (parentFile.getPath().equals(one_file.getParent())) {
            if (System.currentTimeMillis() - currTime < 2000) {
                getActivity().finish();
            } else {
                Toast.makeText(getActivity(), "再点一次退出", Toast.LENGTH_SHORT).show();
                currTime = System.currentTimeMillis();
            }
        } else {
            one_listFiles = parentFile.listFiles();
            adapter.notifyDataSetChanged();
        }
    }


    class OneDataAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            if (one_listFiles != null) {
                return one_listFiles.length;
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return one_listFiles[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = getActivity().getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false);
            TextView one_name = (TextView) view.findViewById(android.R.id.text1);
            one_name.setText(one_listFiles[position].getName());
            return view;
        }
    }
}

