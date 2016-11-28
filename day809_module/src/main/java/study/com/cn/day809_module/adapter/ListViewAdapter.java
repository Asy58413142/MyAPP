package study.com.cn.day809_module.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import study.com.cn.day809_module.R;

/**
 * Created by ann on 2016/8/10.
 */
public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;

    public ListViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.listview_item, null);
            holder = new ListViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder= (ListViewHolder) convertView.getTag();
        }

        holder.tv.setText(list.get(position));
        return convertView;
    }

    class ListViewHolder {
        @BindView(R.id.listview_item_tv)
        public TextView tv;

        public ListViewHolder(View view) {
            ButterKnife.bind(this, view);
        }


    }
}
