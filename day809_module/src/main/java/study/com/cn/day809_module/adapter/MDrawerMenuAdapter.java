package study.com.cn.day809_module.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import study.com.cn.day809_module.bean.MDDrawerMenu;
import study.com.cn.day809_module.holder.MDDrawerMenuHolder;
import study.com.cn.day809_module.R;

/**
 * Created by ann on 2016/8/9.
 */
public class MDrawerMenuAdapter extends BaseAdapter {
    private List<MDDrawerMenu> menus;
    private Context context;

    public MDrawerMenuAdapter(List<MDDrawerMenu> menus, Context context) {
        this.menus = menus;
        this.context = context;
    }

    @Override
    public int getCount() {
        return menus.size();
    }

    @Override
    public Object getItem(int position) {
        return menus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MDDrawerMenuHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.main_drawer_item, null);
            holder = new MDDrawerMenuHolder();
            holder.menuName = (TextView) convertView.findViewById(R.id.main_drawer_item_tv);
            holder.menuImg = (ImageView) convertView.findViewById(R.id.main_drawer_item_img);
            convertView.setTag(holder);
        } else {
            holder= (MDDrawerMenuHolder) convertView.getTag();
        }
        holder.menuName.setText(menus.get(position).getName());
        holder.menuImg.setImageResource(menus.get(position).getImg());

        return convertView;
    }
}
