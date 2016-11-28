package study.com.cn.day803_;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ann on 2016/8/3.
 */

class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewHolder> {
    private static Set<String> mSelectImg = new HashSet<String>();
    private Activity context;
    private List<String> mImgs;
    private String mPath;
    private onSelectNumChangeListener numChangeListener;
    private int maxNum;
    public recyclerViewAdapter(Activity context, List<String> mImgs, String mPath,int maxNum, onSelectNumChangeListener numChangeListener) {
        this.context = context;
        this.mImgs = mImgs;
        this.mPath = mPath;
        this.numChangeListener = numChangeListener;
        this.maxNum = maxNum;
    }

    @Override
    public recyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = context.getLayoutInflater().inflate(R.layout.item, null);

        return new recyclerViewHolder(view, new onImageClickListener() {
            @Override
            public void onClickListener(int position, ImageButton check, ImageView pic) {
                //点击图片的操作
                String filePath = mPath + "/" + mImgs.get(position);
                if (mSelectImg.contains(filePath)) {
                    //已经选择过

                    mSelectImg.remove(filePath);
                    pic.setColorFilter(null);
                    check.setImageResource(R.drawable.address_default);
                    if (numChangeListener != null) {
                        numChangeListener.onNumDown();
                    }
                } else {
                    //没有选中
                    if (mSelectImg.size() < maxNum) {
                        mSelectImg.add(filePath);
                        pic.setColorFilter(Color.parseColor("#77000000"));
                        check.setImageResource(R.drawable.address_selected);
                        if (numChangeListener != null) {
                            numChangeListener.onNumAdd();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onBindViewHolder(recyclerViewHolder holder, int position) {

        if (position == 0 && mImgs.get(position).equals("first")) {
            holder.pic.setImageResource(R.drawable.ic_action_camera);
            holder.check.setVisibility(View.GONE);
        } else {
            ImageLoader2.getInstance(3, ImageLoader2.Type.LIFO).loadImage(mPath + "/" + mImgs.get(position), holder.pic);
            holder.check.setVisibility(View.VISIBLE);
        }
        String filePath = mPath + "/" + mImgs.get(position);
        if (mSelectImg.contains(filePath)) {
            //已经选择过
            holder.pic.setColorFilter(Color.parseColor("#77000000"));
            holder.check.setImageResource(R.drawable.address_selected);
        } else {
            holder.pic.setColorFilter(null);
            holder.check.setImageResource(R.drawable.address_default);
        }


    }

    @Override
    public int getItemCount() {
        return mImgs.size();
    }

    /**
     * 返回最终选择的图片信息
     *
     * @return
     */
    public Set<String> getSelectImg() {
        return mSelectImg;
    }

    interface onSelectNumChangeListener {
        void onNumAdd();

        void onNumDown();
    }

}


