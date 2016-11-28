package study.com.cn.day727_viewpagerviewpager.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import study.com.cn.day727_viewpagerviewpager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZoneFragment extends Fragment {


    public ZoneFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_zone, container, false);
    }

}
