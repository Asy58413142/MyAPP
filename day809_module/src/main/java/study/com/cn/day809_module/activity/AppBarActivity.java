package study.com.cn.day809_module.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import study.com.cn.day809_module.R;

/**
 * Created by ann on 2016/8/12.
 */
public class AppBarActivity extends AppCompatActivity{

    @BindView(R.id.appbar_lv)
    ListView listView;
    @BindView(R.id.appbar_toolbar)
    Toolbar toolbar;
    @BindView(R.id.coolapsing)
    CollapsingToolbarLayout collapsing;
    @BindView(R.id.img)
    ImageView img;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar);
        ButterKnife.bind(this);
        initToolBar();
        initList();
    }

    private void initList() {
        String[] string={"aaa","bbb","ccc","ddd","eee","fff","ggg","hhh","iii","jjj","kkk"};

        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,string));
    }

    private void initToolBar() {
//        DisplayMetrics metrics=new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeResource(getResources(), R.drawable.background3);
//        options.inSampleSize = options.outWidth / metrics.widthPixels;
//        options.inJustDecodeBounds = false;
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background3, options);
//        img.setImageBitmap(bitmap);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        collapsing.setTitle("AppBar");
    }
}
