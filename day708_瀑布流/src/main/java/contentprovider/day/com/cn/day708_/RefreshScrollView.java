package contentprovider.day.com.cn.day708_;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by ann on 2016/7/8.
 */
public class RefreshScrollView extends ScrollView {
    /**
     * 1.getHeight()  得到组件的高度 在屏幕内部展示的高度（不可以滚动）  如果已经超过了屏幕的高度（可以滚动），那么组件高度就是屏幕的高度
     * <p/>
     * 2.getMeasuredHeight()  组件真正的高度
     * <p/>
     * 3.getScrollY()    在Y轴上滚动的距离
     */


    interface onLoadListener {
        void load();
    }

    private onLoadListener onLoadListener;

    public void setOnLoadListener(RefreshScrollView.onLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }

    public void init() {

        final View child = getChildAt(0);
        if (child == null) {
            return;
        }
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //这里不能用ScrollView的真是高度 他会重新的倍计算 导致滑动就会刷新数据
                //要用子类的真是高度
                if (child.getMeasuredHeight() <= getHeight() + getScrollY()) {
                    //滑动到ScrollView底部
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        //上拉加载数据
                        if (onLoadListener != null) {
                            onLoadListener.load();
                        }
                    }
                }
                return false;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    public RefreshScrollView(Context context) {
        super(context);

    }

    public RefreshScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
