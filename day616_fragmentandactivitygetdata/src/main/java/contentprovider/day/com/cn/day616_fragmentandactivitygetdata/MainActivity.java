package contentprovider.day.com.cn.day616_fragmentandactivitygetdata;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;
    private FragmentManager manager;
    private Bundle bundle = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment = new AFragment();
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton0:
                        fragment = new AFragment();
                        //将数据放在Bundle中传给Fragment
                        Bundle args = new Bundle();
                        args.putString("a","这是从activity中向Fragment传入数据");
                        fragment.setArguments(args);
                        break;
                    case R.id.radioButton1:
                        fragment = new BFragment();
                        break;
                    case R.id.radioButton2:
                        fragment = new CFragment();
                        if (bundle != null) {
                            fragment.setArguments(bundle);
                        }
                        break;
                }
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
            }
        });
    }
    public void setData(Bundle bundle){
        this.bundle = bundle;
    }
}
