package contentprovider.day.com.cn.day707_;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ann on 2016/7/7.
 */
public class LabelView extends TextView {

    private String text;
    private Bitmap bitmap;
    private int width=0;
    private int height=0;
    private Paint paint ;
    private int textHeight;


    public LabelView(Context context) {
        super(context);
    }

    public LabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        text = "sssssasdasfa";
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        paint.setTextSize(15*getResources().getDisplayMetrics().density);

    }

    /**
     * UNSPECIFIED  比较少见 adapterView
     * <p/>
     * AT_MOST   以最大的方式展示 包裹内容（wrap_content）
     * <p/>
     * EXACTLY   固定值  ndp  match_parent
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            if (!TextUtils.isEmpty(text)) {
                float textSize = paint.measureText(text);
                int imageWidth;
                if (bitmap != null) {
                    imageWidth = bitmap.getWidth();
                } else {
                    imageWidth = 0;
                }
                width = (int) (textSize + imageWidth + getPaddingLeft() + getPaddingRight() + 0.5);
            }
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            textHeight = 0;
            if (!TextUtils.isEmpty(text)) {
                textHeight = (int) (paint.descent() - paint.ascent() + getPaddingBottom() + getPaddingTop() + 0.5);
            }
            int bitmapH = 0;
            if (bitmap != null) {
                bitmapH = (int) (bitmap.getHeight() + getPaddingBottom() + getPaddingTop() + 0.5);
            }
            height = Math.max(textHeight, bitmapH);
        }

        setMeasuredDimension(width,height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap, getPaddingLeft(), getPaddingTop(), paint);
        int x = getPaddingLeft() + bitmap.getWidth();
        int h = (int) (paint.descent() - paint.ascent() + getPaddingBottom() + getPaddingTop() + 0.5);
        int y = (bitmap.getHeight() + h) / 2 - (int) paint.descent();
        canvas.drawText(text,x,y,paint);

    }
}
