package contentprovider.day.com.cn.day707_;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by ann on 2016/7/7.
 */
public class NoteView extends EditText {

    private int line_color;
    private int line_width;

    public void setLine_color(int line_color) {
        this.line_color = line_color;
    }

    public void setLine_width(int line_width) {
        this.line_width = line_width;
    }

    public NoteView(Context context) {
        super(context);
    }

    public NoteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取自己定义的属性组
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NoteView);
        line_color = typedArray.getColor(R.styleable.NoteView_line_color, Color.BLACK);
        line_width = typedArray.getInt(R.styleable.NoteView_line_width, 1);

        //释放掉数组资源，节省内存
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //组件高度
        int height = getHeight();
        //每一行的高度
        int lineHeight = getLineHeight();
        //一共几行
        int count = height / lineHeight;
        int baseline = getBaseline();
        for (int i = 0; i < count; i++) {
            int countY = lineHeight * i + baseline;
            Paint paint=new Paint();
            paint.setColor(line_color);
            paint.setStrokeWidth(line_width);
            canvas.drawLine(0,countY,getWidth(),countY,paint);
        }
    }
}
