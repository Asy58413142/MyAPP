package contentprovider.day.com.cn.day614_fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //得到fragment管理者对象
        FragmentManager fragmentManager = getFragmentManager();
        //通过管理者对象，找到fragment对象

        MyFragment fragment = (MyFragment) fragmentManager.findFragmentById(R.id.fragment);
        //调用fragment里的方法返回button对象
        Button button2 = fragment.getButton2();
        button2.setText("通过找到fragment对象找到button对象");


        Button bu = (Button) findViewById(R.id.button2);
        bu.setText("直接调用");

    }
}
