package study.com.cn.day809_module.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import study.com.cn.day809_module.R;
import study.com.cn.day809_module.holder.RecyclerViewHolder;

/**
 * Created by ann on 2016/8/10.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    public Activity context;
    public List<String> list;
    private int width;
    private int height;

    public RecyclerViewAdapter(Activity context, List<String> list, int width, int height) {
        this.context = context;
        this.list = list;
        this.width = width;
        this.height = height;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = context.getLayoutInflater().inflate(R.layout.recyclerview_item, null);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Transformation  transformation=new Transformation() {
                @Override

                public Bitmap transform(Bitmap source) {
                    if (height == 3) {
                        height = width * source.getHeight() / source.getWidth();
                        Bitmap rBitmap = Bitmap.createScaledBitmap(source, width,height , false);
                        if (rBitmap != source) {
                            source.recycle();
                        }
                        return rBitmap;
                    } else {
                        return source;
                    }

                }
                @Override
                public String key() {
                    return "transformation" + " desiredWidth";
                }
            };
        Picasso.with(context)
                .load(list.get(position))
                .transform(transformation)
                .resize(width,height)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}
