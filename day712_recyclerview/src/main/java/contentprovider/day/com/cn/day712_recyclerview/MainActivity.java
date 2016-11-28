package contentprovider.day.com.cn.day712_recyclerview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private String[] images;
    private int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            images = getAssets().list("images");
        } catch (IOException e) {
            e.printStackTrace();
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        DisplayMetrics meteric=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(meteric);
        width = meteric.widthPixels/3;
        //线性布局管理器  相当于 垂直和水平的listView
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        //网格管理器  和网格布局一样
        GridLayoutManager glm = new GridLayoutManager(this,3);
        glm.setOrientation(GridLayoutManager.HORIZONTAL);
        //瀑布流管理器
        StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(sglm);
        recyclerView.setAdapter(new recyclerViewAdapter());
    }

    class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewHolder> {
        //创建 ItemView
        @Override
        public recyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, null);
            return new recyclerViewHolder(view, new clickListener() {
                @Override
                public void onViewClickListener(int position) {
                    Toast.makeText(MainActivity.this, position+"", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onImageClickListener(int position) {
                    Toast.makeText(MainActivity.this,images[position], Toast.LENGTH_SHORT).show();
                }
            });
        }
        //获取itemview 放置数据
        @Override
        public void onBindViewHolder(recyclerViewHolder holder, int position) {
            try {
                InputStream in = MainActivity.this.getAssets().open("images/" + images[position]);
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.imageView.getLayoutParams();
                params.width = width;
                params.height = params.width*bitmap.getHeight() / bitmap.getWidth();
                holder.imageView.setImageBitmap(bitmap);
                in.close();
                holder.textView.setText(images[position]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return images.length;
        }
    }

    class recyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private final TextView textView;
        private final ImageView imageView;
        private clickListener listener;
        private View itemView;
        public recyclerViewHolder(View itemView,clickListener listener) {
            super(itemView);
            this.listener = listener;
            this.itemView = itemView;
            textView = (TextView) itemView.findViewById(R.id.text);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
            imageView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

            if (v == imageView) {
                listener.onImageClickListener(getLayoutPosition());
            } else if (v != imageView) {
                listener.onViewClickListener(getLayoutPosition());
            }

        }
    }

    interface clickListener{
        void onViewClickListener(int position);
        void onImageClickListener(int position);
    }


}
