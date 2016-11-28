package study.com.cn.day727_listview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by ann on 2016/7/27.
 */
public class MyListViewMeasure extends ListView {
    public MyListViewMeasure(Context context) {
        super(context);
    }

    public MyListViewMeasure(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int h = MeasureSpec.makeMeasureSpec(heightMeasureSpec >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, h);
    }
}
