package study.com.cn.day809_module.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import study.com.cn.day809_module.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SnowFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_snow, container, false);


        return view;
    }

}
