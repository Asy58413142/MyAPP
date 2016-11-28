package contentprovider.day.com.cn.day629_viewpagerfragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewPagerIsFragment.OnFragmentInteractionListener,ActionBar.TabListener, ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1.找到viewPager控件
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        fragments = new ArrayList<Fragment>();
        for (int i = 0; i < 5; i++) {
            //添加导航按钮
            actionBar.addTab(actionBar.newTab().setText("item"+i).setTag(i).setTabListener(this));
            //创建Fragment放进集合里
            ViewPagerIsFragment pagerIsFragment = ViewPagerIsFragment.newInstance("Item" + i);
            fragments.add(pagerIsFragment);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new ViewPagerIsFragmentAdapter(fragmentManager));
        viewPager.setOnPageChangeListener(this);

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        getSupportActionBar().setSelectedNavigationItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab,FragmentTransaction ft) {
        int position = (int) tab.getTag();
        //第几个页面，是否有动画
        viewPager.setCurrentItem(position, false);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    class ViewPagerIsFragmentAdapter extends FragmentPagerAdapter {

        public ViewPagerIsFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        //显示第几个Fragment
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }
        //一共有几页
        @Override
        public int getCount() {
            return fragments.size();
        }

    }
}

