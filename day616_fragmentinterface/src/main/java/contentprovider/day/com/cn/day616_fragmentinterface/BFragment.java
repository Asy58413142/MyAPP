package contentprovider.day.com.cn.day616_fragmentinterface;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by ann on 2016/6/16      .
 */
public class BFragment  extends ListFragment{


    private TextView fragment_b_tv;
    private PutDataToActivity putDataToActivity;
    interface PutDataToActivity{
        public void setData(String data);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PutDataToActivity) {
            putDataToActivity = (PutDataToActivity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_b, container, false);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1);

        for (int i=0;i<100;i++) {
//            adapter.add("item"+i);
        }

        setListAdapter(adapter);

        putDataToActivity.setData("这是Fragment给我的数据");
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView listView = getListView();
        TextView textView = (TextView) view.findViewById(android.R.id.empty);
        textView.setText("no data");
        listView.setEmptyView(textView);
    }
}
