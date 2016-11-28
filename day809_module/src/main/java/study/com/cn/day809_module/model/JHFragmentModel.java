package study.com.cn.day809_module.model;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by ann on 2016/8/9.
 */
public interface JHFragmentModel {
    void bindData2View(Activity activity,RecyclerView view, List<String> list);

    List<String> dataProduct();
}
