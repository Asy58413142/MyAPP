package study.com.cn.day809_module.model;

import android.widget.ListView;

import java.util.List;

import study.com.cn.day809_module.bean.MDDrawerMenu;

/**
 * Created by ann on 2016/8/9.
 */
public interface MDrawerMenuFragmentModel {

    public void setDrawerMenuData(ListView view, List<MDDrawerMenu> menuList);

    List<MDDrawerMenu> dataProduct();
}
