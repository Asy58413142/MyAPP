package study.com.cn.day809_module.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import study.com.cn.day809_module.R;

/**
 * Created by ann on 2016/8/10.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.recyclerview_item_img)
    public ImageView img;
    public RecyclerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
