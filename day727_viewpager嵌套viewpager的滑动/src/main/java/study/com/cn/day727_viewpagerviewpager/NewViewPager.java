package study.com.cn.day727_viewpagerviewpager;

import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by ann on 2016/7/28.
 */
public class NewViewPager extends ViewPager {
    onViewPagerClick1 mOnClick;
    PointF downF = new PointF();
    PointF upF = new PointF();

    public void setmOnClick(onViewPagerClick1 mOnClick) {
        this.mOnClick = mOnClick;
    }

    public NewViewPager(Context context) {
        super(context);
    }

    public NewViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;//拦截时间交给onTouchEvent处理
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        upF.x = ev.getX();
        upF.y = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:

                if (upF.x == downF.x && upF.y==downF.y) {
                    if (mOnClick!=null) {
                        mOnClick.onViewPagerClickListener1();
                    }
                }
                break;
            case MotionEvent.ACTION_DOWN:
                downF.x = ev.getX();
                downF.y = ev.getY();
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
        }
        return super.onTouchEvent(ev);
    }

   public interface onViewPagerClick1{
        void onViewPagerClickListener1();
    }
}
