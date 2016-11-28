package study.com.cn.day809_module.model;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import study.com.cn.day809_module.R;
import study.com.cn.day809_module.adapter.MDrawerMenuAdapter;
import study.com.cn.day809_module.bean.MDDrawerMenu;

/**
 * Created by ann on 2016/8/9.
 */
public class MDrawerMenuFragmentModelImpl  implements MDrawerMenuFragmentModel {

    @Override
    public void setDrawerMenuData(ListView view, List<MDDrawerMenu> menuList) {
        view.setAdapter(new MDrawerMenuAdapter(menuList,view.getContext()));
    }

    @Override
    public List<MDDrawerMenu> dataProduct() {
        String[] menuName = {"你不存在了", "但生活还在。", "得非所愿", "愿非所得", "终于放弃了虚伪的正义", "手握真正的杀意了吗？"};
        int[] menuImg = {R.drawable.ic_close,
                R.drawable.ic_ac_unit,
                R.drawable.ic_beach_access,
                R.drawable.ic_arrow_back,
                R.drawable.ic_child_care,
                R.drawable.ic_toys};
        List<MDDrawerMenu> menus = new ArrayList<>();
        for (int i = 0; i <menuName.length ; i++) {
            MDDrawerMenu menu = new MDDrawerMenu();
            menu.setName(menuName[i]);
            menu.setImg(menuImg[i]);
            menus.add(menu);
        }
        return menus;
    }
}
