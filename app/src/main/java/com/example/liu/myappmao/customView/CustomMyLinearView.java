package com.example.liu.myappmao.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CustomMyLinearView extends View {
    private Paint paint;
    private Path path;
    private int left;
    private int right;
    private int top;
    private int bottom;
    private int width;
    private int height;
    private final float VALUE = 2f;
    private final int LVALUE = 0;

    public CustomMyLinearView(Context context) {
        this(context, null);
    }

    public CustomMyLinearView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomMyLinearView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        path = new Path();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rect = new RectF(LVALUE, LVALUE, width, height);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        float v = height / VALUE;
        // path.addOval(rect,Path.Direction.CCW);
        // path.addArc(rect, LVALUE, -180);
        path.arcTo(rect, 0, -180, true);
        path.addRect(LVALUE, v, width, height, Path.Direction.CCW);
        //  canvas.drawArc(rect,0,-200,true,paint);
        canvas.drawPath(path, paint);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = getMeasuredWidth();
        height = getMeasuredHeight();

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        left = l;
        right = r;
        top = t;
        bottom = b;
    }
}
