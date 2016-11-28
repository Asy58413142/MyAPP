package study.com.cn.day809_module.model;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import study.com.cn.day809_module.adapter.JHFragmentAdapter;

/**
 * Created by ann on 2016/8/9.
 */
public class JHFragmentModelImpl implements JHFragmentModel {
    @Override
    public void bindData2View(Activity activity,RecyclerView view, List<String> list) {
        view.setAdapter(new JHFragmentAdapter(activity,list));
    }

    @Override
    public List<String> dataProduct() {
        List<String> strings = new ArrayList<>();
        strings.add("RecyclerView");
        strings.add("ListView");
        strings.add("NavigationView");
        strings.add("CardView");
        strings.add("AppBar");
        return strings;
    }
}
