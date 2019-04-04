package com.example.liu.myappmao.customView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class CustomComplexView extends LinearLayout {

    int viewCount;

    public CustomComplexView(Context context) {
        this(context, null);
    }

    public CustomComplexView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomComplexView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int width;
        int height;
        int topHeight = 0;
        int belHeight = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {

            View view = getChildAt(i);


            width = view.getWidth();
            height = view.getHeight();
            belHeight = belHeight + height;
            Log.i(getClass().getSimpleName(), "onLayout（）方法,topHeight=" + topHeight + ";;belHeight=" + belHeight);

            view.layout(l, t + topHeight, r, belHeight);
            topHeight = height;


        }

    }
}
