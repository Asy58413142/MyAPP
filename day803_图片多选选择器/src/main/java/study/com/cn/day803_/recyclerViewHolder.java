package study.com.cn.day803_;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ann on 2016/8/3.
 */
public class recyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView pic;
    public ImageButton check;
    private onImageClickListener clickListener;
    List<String> select = new ArrayList<>();
    public recyclerViewHolder(View itemView,onImageClickListener clickListener) {
        super(itemView);
        this.clickListener = clickListener;
        check = (ImageButton) itemView.findViewById(R.id.item_check);
        pic = (ImageView) itemView.findViewById(R.id.item_pic);

        pic.setOnClickListener(this);
        check.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_check:
                if (select.equals(getLayoutPosition() + "")) {
                    select.remove(getAdapterPosition() + "");
                    clickListener.onClickListener(getLayoutPosition(), check, pic);
                } else {
                    select.add(getAdapterPosition() + "");
                    clickListener.onClickListener(getLayoutPosition(), check, pic);
                }
                break;
        }
    }


}
