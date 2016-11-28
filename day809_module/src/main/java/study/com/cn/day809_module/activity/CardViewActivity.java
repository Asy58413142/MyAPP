package study.com.cn.day809_module.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import study.com.cn.day809_module.R;

/**
 * Created by ann on 2016/8/12.
 */
public class CardViewActivity extends AppCompatActivity {
    @BindView(R.id.cardview_lv)
    ListView cardview_lv;
    @BindView(R.id.toolbar1)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView title;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview);
        ButterKnife.bind(this);
        initToolbar();
        String[] list = {"aaa","bbb","ccc","ddd","eee"};
        cardview_lv.setAdapter(new ArrayAdapter<String>(this,R.layout.cardview_item,R.id.cardview_item_tv,list));

    }

    private void initToolbar() {

        title.setText("CardView");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}
