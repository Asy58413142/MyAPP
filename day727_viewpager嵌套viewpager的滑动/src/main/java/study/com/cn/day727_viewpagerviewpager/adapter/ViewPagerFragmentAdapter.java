package study.com.cn.day727_viewpagerviewpager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by ann on 2016/7/27.
 */
public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;

    public ViewPagerFragmentAdapter(FragmentManager fm ,List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
