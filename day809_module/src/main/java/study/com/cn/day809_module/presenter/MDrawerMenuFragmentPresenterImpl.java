package study.com.cn.day809_module.presenter;

import study.com.cn.day809_module.model.MDrawerMenuFragmentModel;
import study.com.cn.day809_module.model.MDrawerMenuFragmentModelImpl;
import study.com.cn.day809_module.presenter.MDrawerMenuFragmentPresenter;
import study.com.cn.day809_module.view.MDrawerMenuFragmentView;

/**
 * Created by ann on 2016/8/9.
 */
public class MDrawerMenuFragmentPresenterImpl implements MDrawerMenuFragmentPresenter {
    MDrawerMenuFragmentModel model;
    MDrawerMenuFragmentView view;
    public MDrawerMenuFragmentPresenterImpl(MDrawerMenuFragmentView view) {
        this.view = view;
        model = new MDrawerMenuFragmentModelImpl();
    }

    @Override
    public void setData2View() {

        model.setDrawerMenuData(view.getView(),model.dataProduct());
    }
}
