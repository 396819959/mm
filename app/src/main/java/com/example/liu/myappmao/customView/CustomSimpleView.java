package com.example.liu.myappmao.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.example.liu.myappmao.R;

public class CustomSimpleView extends LinearLayout {

    boolean fillHeight = false;
    boolean fillWidth = false;

    public CustomSimpleView(Context context) {
        this(context, null);
    }

    public CustomSimpleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSimpleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context, attrs);
    }

    private void initData(Context context, AttributeSet attrs) {

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomSimpleView);
        int count = array.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = array.getIndex(i);
            switch (attr) {

                case R.styleable.CustomSimpleView_fill_height:
                    fillHeight = true;
                    break;
                case R.styleable.CustomSimpleView_fill_width:

                    fillWidth = true;
                    break;
            }
        }

        array.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = widthMeasureSpec;
        int height = heightMeasureSpec;

        if (fillWidth == true) {
            width = widthMeasureSpec;
            height = widthMeasureSpec;
        } else if (fillHeight == true) {
            width = heightMeasureSpec;
            height = heightMeasureSpec;

        }

        super.onMeasure(width, height);
    }
}
