package study.com.cn.day727_viewpagerviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import study.com.cn.day727_viewpagerviewpager.adapter.ViewPagerFragmentAdapter;
import study.com.cn.day727_viewpagerviewpager.fragment.HomeFragment;
import study.com.cn.day727_viewpagerviewpager.fragment.MineFragment;
import study.com.cn.day727_viewpagerviewpager.fragment.ZoneFragment;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.gp)
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new HomeFragment());
        fragments.add(new ZoneFragment());
        fragments.add(new MineFragment());
        radioGroup.setOnCheckedChangeListener(this);
        viewPager.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager(), fragments));

        viewPager.setOnPageChangeListener(this);
    }




    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        
    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton childAt = (RadioButton) radioGroup.getChildAt(i);
            if (position == i) {
                childAt.setChecked(true);
            } else {
                childAt.setChecked(false);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.home:
                viewPager.setCurrentItem(0);
                break;
            case R.id.zone:
                viewPager.setCurrentItem(1);
                break;
            case R.id.mine:
                viewPager.setCurrentItem(2);
                break;
        }
    }
}
