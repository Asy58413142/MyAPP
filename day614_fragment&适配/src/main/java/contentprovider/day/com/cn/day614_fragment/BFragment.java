package contentprovider.day.com.cn.day614_fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ann on 2016/6/15.
 */
public class BFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_b, container, false);
        //获取同一个activity中的fragment的id都是可以的
//        Button button = (Button) getActivity().findViewById(R.id.button2);
//        button.setText("这是获取同一个activity中的组件");
        return view;
    }
}
