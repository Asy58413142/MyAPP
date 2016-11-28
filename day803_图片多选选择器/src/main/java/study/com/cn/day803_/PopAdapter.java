package study.com.cn.day803_;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ann on 2016/8/4.
 */
public class PopAdapter extends BaseAdapter {

    private List<ImageFloder> list;
    private Context context;
    private onSelectChangeListener listener;
    private static int current=0;

    public PopAdapter(List<ImageFloder> list, Context context, onSelectChangeListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        if (list != null) {

            return list.size();
        } else {
            return 0;
        }
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
    public View getView(final int position, View convertView, ViewGroup parent) {
       final PopViewHolder holder ;
        if (convertView == null) {
            holder = new PopViewHolder();
            convertView = View.inflate(context, R.layout.pop_item, null);
            holder.icon = (ImageView) convertView.findViewById(R.id.pop_image);
            holder.check = (ImageView) convertView.findViewById(R.id.check);
            holder.path = (TextView) convertView.findViewById(R.id.pop_path);
            holder.picNum = (TextView) convertView.findViewById(R.id.pop_picNum);
            holder.onClick = (RelativeLayout) convertView.findViewById(R.id.onClick);
            convertView.setTag(holder);
        } else {
            holder = (PopViewHolder) convertView.getTag();
        }

        ImageLoader2.getInstance().loadImage(list.get(position).getFirstImagePath(),holder.icon);
        holder.picNum.setText(list.get(position).getCount()+"");
        holder.path.setText(list.get(position).getName());
        if (current == position) {
            holder.check.setImageResource(R.drawable.address_selected);
        } else {
            holder.check.setImageResource(R.drawable.address_default);
        }
        holder.onClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (current != position) {
                    holder.check.setImageResource(R.drawable.address_selected);
                    current = position;
                    listener.onChangeListener(list.get(position));
                } else {
                    listener.onChangeListener(null);
                }

            }
        });
        return convertView;
    }


    class PopViewHolder {
        public ImageView icon;
        public ImageView check;
        public TextView path;
        public TextView picNum;
        public RelativeLayout onClick;
    }

    interface onSelectChangeListener{
        void onChangeListener(ImageFloder imageFloder);
    }
}
