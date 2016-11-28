package contentprovider.day.com.cn.day713_switcherviewfillperanim;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ann on 2016/7/13.
 */
public class ImageSwitcherActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.image_switcher);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.downtoup_in,R.anim.righttoleft_in);
    }
}
