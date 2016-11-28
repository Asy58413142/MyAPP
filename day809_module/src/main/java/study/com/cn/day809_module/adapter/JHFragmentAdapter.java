package study.com.cn.day809_module.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import study.com.cn.day809_module.Contants;
import study.com.cn.day809_module.R;
import study.com.cn.day809_module.holder.JHFragmentHolder;

/**
 * Created by ann on 2016/8/9.
 */
public class JHFragmentAdapter extends RecyclerView.Adapter<JHFragmentHolder> {
    private Activity context;
    private List<String> list=new ArrayList<>();

    public JHFragmentAdapter(Activity context, List<String> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public JHFragmentHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = context.getLayoutInflater().inflate(R.layout.recycler_jh_item, parent, false);
        return new JHFragmentHolder(view, new JHFragmentHolder.setOnClickListener() {
            @Override
            public void onItemClick(final int position) {
                try {
                    Intent intent = new Intent(context, Class.forName(Contants.PACKAGE_NAME + list.get(position)+"Activity"));
                    context.startActivity(intent);
                } catch (ClassNotFoundException e) {
                    Snackbar.make(parent, R.string.string_on_found, Snackbar.LENGTH_SHORT).setAction(R.string.string_close, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, list.get(position), Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                }
            }
        });
    }

    @Override
    public void onBindViewHolder(JHFragmentHolder holder, int position) {
            holder.jhName.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();

    }
}
