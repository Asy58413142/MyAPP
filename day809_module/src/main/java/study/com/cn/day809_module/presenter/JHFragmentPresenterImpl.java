package study.com.cn.day809_module.presenter;

import android.app.Activity;

import study.com.cn.day809_module.model.JHFragmentModel;
import study.com.cn.day809_module.model.JHFragmentModelImpl;
import study.com.cn.day809_module.view.JHFragmentView;

/**
 * Created by ann on 2016/8/9.
 */
public class JHFragmentPresenterImpl implements JHFragmentPresenter {
    private Activity activity;
    JHFragmentView view;
    JHFragmentModel model;
    public JHFragmentPresenterImpl(Activity activity,JHFragmentView view) {
        this.view = view;
        model = new JHFragmentModelImpl();
        this.activity = activity;
    }

    @Override
    public void setView2Data() {
        model.bindData2View(activity,view.getView(),model.dataProduct());
    }
}
