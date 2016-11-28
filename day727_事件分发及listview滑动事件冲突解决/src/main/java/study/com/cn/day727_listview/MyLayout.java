package study.com.cn.day727_listview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by ann on 2016/7/27.
 */
public class MyLayout extends LinearLayout{
    public MyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLayout(Context context) {
        super(context);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.e("=========", "MyLayout=======onTouchEvent========== " );
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("=========", "MyLayout=======dispatchTouchEvent========== " );
        return super.dispatchTouchEvent(ev);
    }

    /**
     * ViewGroup 特有的事件
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("=========", "MyLayout=======onInterceptTouchEvent========== " );
        return super.onInterceptTouchEvent(ev);
    }
}
