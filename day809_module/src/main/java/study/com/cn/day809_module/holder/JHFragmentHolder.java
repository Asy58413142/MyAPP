package study.com.cn.day809_module.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import study.com.cn.day809_module.R;

/**
 * Created by ann on 2016/8/9.
 */
public class JHFragmentHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView jhName;
    private setOnClickListener onClickListener;
    public JHFragmentHolder(View itemView,setOnClickListener onClickListener) {
        super(itemView);
        this.onClickListener = onClickListener;
        jhName= (TextView) itemView.findViewById(R.id.recycler_jh_item_tv);
        jhName.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.recycler_jh_item_tv) {
            if (onClickListener != null) {
                onClickListener.onItemClick(getLayoutPosition());
            }
        }
    }


    public interface setOnClickListener{
        void onItemClick(int position);
    }
}
