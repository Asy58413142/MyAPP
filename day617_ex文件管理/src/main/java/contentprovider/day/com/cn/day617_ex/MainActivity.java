package contentprovider.day.com.cn.day617_ex;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    private OneFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragment = new OneFragment();
        transaction.add(R.id.fragment_container, fragment).commit();
    }

    @Override
    public void onBackPressed() {

        fragment.onBackPressed();
    }
}
