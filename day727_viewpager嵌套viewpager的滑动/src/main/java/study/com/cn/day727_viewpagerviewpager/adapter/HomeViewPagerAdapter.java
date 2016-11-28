package study.com.cn.day727_viewpagerviewpager.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ann on 2016/7/28.
 */
public class HomeViewPagerAdapter extends PagerAdapter implements View.OnClickListener {
    List<View> viewList;
    onViewPagerListener mOnViewPagerListener;


    public HomeViewPagerAdapter(List<View> viewList, onViewPagerListener mOnViewPagerListener) {
        this.viewList = viewList;
        this.mOnViewPagerListener = mOnViewPagerListener;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    /**
     * 初始化
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        viewList.get(position).setOnClickListener(this);
        return viewList.get(position);
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void onClick(View v) {
        if (mOnViewPagerListener != null) {
            mOnViewPagerListener.onViewPagerClickListener();
        }
    }


    public interface onViewPagerListener{
        void onViewPagerClickListener();
    }
}
