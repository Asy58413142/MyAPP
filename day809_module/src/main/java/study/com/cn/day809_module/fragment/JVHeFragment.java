package study.com.cn.day809_module.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import study.com.cn.day809_module.R;
import study.com.cn.day809_module.presenter.JHFragmentPresenterImpl;
import study.com.cn.day809_module.view.JHFragmentView;


public class JVHeFragment extends Fragment implements JHFragmentView {

    @BindView(R.id.jvhe_listView)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jvhe, container, false);
        ButterKnife.bind(this, view);
        /**
         * 设置RecyclerView 的方法要在这里不能再getView 方法中
         * 否则会出现不能显示的情况
         */
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layout);
        JHFragmentPresenterImpl presenter = new JHFragmentPresenterImpl(getActivity(), this);
        presenter.setView2Data();
        return view;
    }

    public RecyclerView getView() {
        return recyclerView;
    }

}
