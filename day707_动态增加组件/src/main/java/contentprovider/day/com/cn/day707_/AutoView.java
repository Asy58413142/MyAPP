package contentprovider.day.com.cn.day707_;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ann on 2016/7/7.
 */
public class AutoView extends View {

    private float x=0;
    private float y=0;

    /**
     * 代码初始化组件
     * @param context
     */
    public AutoView(Context context) {
        super(context);
    }

    /**
     * XML中使用的构造
     * @param context
     * @param attrs
     */
    public AutoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 测量 组件的宽高  wrap_content 时  计算子类的宽高
     * 当为match_parent 是不调用
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 设置布局显示的位置
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            x = event.getX();
            y = event.getY();

            invalidate();
        }
        return true;
    }

    /**
     * 绘制组件的显示效果
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Paint paint=new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap,100,100,paint);
        canvas.drawCircle(x,y,50,paint);
        paint.setColor(Color.DKGRAY);
        canvas.drawLine(0,0,100,200,paint);
    }
}
