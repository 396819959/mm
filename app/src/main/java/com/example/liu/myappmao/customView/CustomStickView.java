package com.example.liu.myappmao.customView;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;



@SuppressLint("AppCompatCustomView")
public class CustomStickView extends ImageView{
    private Context context;
    private WindowManager wm;
    private final int VALUE = 2;

    private Path path;
    private Paint paint;

    private int mWidth;
    private int mHeight;

    private OnClickListener click;

    public CustomStickView(Context context) {
        this(context, null);
    }

    public CustomStickView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomStickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        path = new Path();
        paint = new Paint();
        bringToFront();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setARGB(0, 0, 0, 0);
        RectF rectf = new RectF(4, 4, mWidth - 4, mHeight - 4);


        canvas.drawArc(rectf, 0, 360, false, paint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();


    }


    public void setOnClickListener(OnClickListener click) {
        this.click = click;
    }

    public interface OnClickListener {

        void click(View v);
    }


}
