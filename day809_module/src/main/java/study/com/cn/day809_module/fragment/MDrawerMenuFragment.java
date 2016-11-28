package study.com.cn.day809_module.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import study.com.cn.day809_module.presenter.MDrawerMenuFragmentPresenterImpl;
import study.com.cn.day809_module.R;
import study.com.cn.day809_module.view.MDrawerMenuFragmentView;

/**
 * Created by ann on 2016/8/9.
 */
public class MDrawerMenuFragment extends Fragment implements MDrawerMenuFragmentView {


    @BindView(R.id.main_drawermenu_container)
    ListView menu_container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_drawermenu,null,false);
        ButterKnife.bind(this, view);

        MDrawerMenuFragmentPresenterImpl presenter = new MDrawerMenuFragmentPresenterImpl(this);
        presenter.setData2View();
        return view;
    }
    public ListView getView(){
        return menu_container;
    }

}
