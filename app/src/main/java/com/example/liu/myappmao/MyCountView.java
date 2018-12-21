package com.example.liu.myappmao;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Administrator on 2018/12/21.
 */

public class MyCountView extends ViewGroup {
    private Paint paint;
    private Path path;
    private Context context;
    private ImageView iv;

    public MyCountView(Context context) {
        super(context);
    }

    public MyCountView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCountView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;




    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        path = new Path();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(12);

       RectF rect = new RectF(10, 10, 0, 0);
        canvas.drawArc(rect, 0, 180, false, paint);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int count = getChildCount();
        int width;
        int height;
        int mWidth;
        int mHeight;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

if(MeasureSpec.EXACTLY == widthMode){


}
        Log.i(getClass().getSimpleName(), "测量的宽高：" + widthSize + ":" + heightSize + "子类数量：");

        for (int i = 0; i < count; i++) {

            View v = getChildAt(i);
            LayoutParams lp = v.getLayoutParams();
             width = lp.width;
             height = lp.height;

           mWidth =  v.getMeasuredWidth();
          mHeight =   v.getMeasuredHeight();
            v.measure(width, height);
            Log.i(getClass().getSimpleName(), "第"+i+"个子类的宽高：" + width + ":" + height +"测量宽高："+mWidth+":"+mHeight);
        }


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int h = 0;
        int width;
        int height;
        for (int i = 0; i < count; i++) {
            View v = getChildAt(i);
            width = v.getMeasuredWidth();
            height = v.getMeasuredHeight();
            Log.i(getClass().getSimpleName(), "h值："+h+"height值"+height);
            v.layout(l, h, width, height+h);
            h += height;
        }

    }


}
