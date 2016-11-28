package contentprovider.day.com.cn.day616_fragmentandactivitygetdata;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ann on 2016/6/16.
 */
public class AFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_a, container, false);
        TextView textView3 = (TextView) view.findViewById(R.id.textView3);
        //获取从MainActivity中传过来的数据
        Bundle arguments = getArguments();
        if (arguments != null) {
            String a = arguments.getString("a");
            textView3.setText(a);
        }
        return view;
    }
}
