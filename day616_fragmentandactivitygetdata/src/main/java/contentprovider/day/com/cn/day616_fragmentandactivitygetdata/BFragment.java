package contentprovider.day.com.cn.day616_fragmentandactivitygetdata;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ann on 2016/6/16.
 */
public class BFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_b, container, false);

        MainActivity activity = (MainActivity) getActivity();
        Bundle bundle = new Bundle();
        bundle.putString("data","这是从fragment中向activity传数据");
        activity.setData(bundle);
        return view;
    }
}
