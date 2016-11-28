package study.com.cn.day728_;

import android.content.Intent;

/**
 * Created by ann on 2016/7/28.
 */
public class SecondActivity extends BaseActivity{
    @Override
    protected void initUI() {

        setContentView(R.layout.activity_second);

    }

    @Override
    public void startActivityAnimLeft() {

    }

    @Override
    public void startActivityAnimRight() {
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.out_1,R.anim.in_1);
    }
}
