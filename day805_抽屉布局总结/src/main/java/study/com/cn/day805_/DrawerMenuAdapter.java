package study.com.cn.day805_;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by ann on 2016/8/5.
 */
public class DrawerMenuAdapter extends ArrayAdapter<String> {

    public DrawerMenuAdapter(Context context, int resource, int textViewResourceId, List<String> objects) {
        super(context, resource, textViewResourceId, objects);
    }
}
