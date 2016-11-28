package contentprovider.day.com.cn.day708_;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RefreshScrollView.onLoadListener {
    private int NO_DATA = 0;
    private static final int COLUMN_COUNT = 3;
    private static final int ADD_COLUMN_ITEM = 5;//分页，一次加载数据的数量
    private LinearLayout columnContainer;
    private int columnWidth;
    private ArrayList<LinearLayout> columnList;
    private String[] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //放置瀑布的孩子
        columnContainer = (LinearLayout) findViewById(R.id.columnContainer);
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        columnWidth = metrics.widthPixels / COLUMN_COUNT;

        columnList = new ArrayList<LinearLayout>();

        for (int i = 0; i < COLUMN_COUNT; i++) {
            LinearLayout linearLayout = new LinearLayout(MainActivity.this);
            LinearLayout.LayoutParams params
                    = new LinearLayout.LayoutParams(columnWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            linearLayout.setPadding(5,0,5,0);
            linearLayout.setLayoutParams(params);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            columnContainer.addView(linearLayout);
            columnList.add(linearLayout);
        }




        try {
            images = getAssets().list("images");
        } catch (IOException e) {
            e.printStackTrace();
        }
        addColumnItem();
        RefreshScrollView refresh_load = (RefreshScrollView) findViewById(R.id.refresh_load);
        refresh_load.init();
        refresh_load.setOnLoadListener(this);

    }

    private int position=0;
    private void addColumnItem() {

        for (int i = 0; i < ADD_COLUMN_ITEM; i++) {
            //利用取余的方法得到在那个LinearLayout中添加ImageView
            LinearLayout layout = columnList.get(i % COLUMN_COUNT);
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageResource(R.mipmap.ic_launcher);
            LinearLayout.LayoutParams params
                    = new LinearLayout.LayoutParams(columnWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //加载图片
            if (position < images.length - 1) {
                ImageLoader.loadImage(this,imageView,images[position++]);

                layout.addView(imageView);
            }

        }

    }

    @Override
    public void load() {
        addColumnItem();
    }
}
