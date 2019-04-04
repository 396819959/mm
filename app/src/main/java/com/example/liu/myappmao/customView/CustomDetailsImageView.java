package com.example.liu.myappmao.customView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class CustomDetailsImageView extends ImageView {


    public CustomDetailsImageView(Context context) {
        this(context, null);
    }

    public CustomDetailsImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomDetailsImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int h = width;
        switch (widthMode) {

            case MeasureSpec.EXACTLY://设定了固定数值

                // Log.i("CustomDetailsImageView类", "onMeasure()方法，EXACTLY");

                break;
            case MeasureSpec.AT_MOST://wrap_content
                //  Log.i("CustomDetailsImageView类", "onMeasure()方法，AT_MOST");

                break;
            case MeasureSpec.UNSPECIFIED://match_parent
                //  Log.i("CustomDetailsImageView类", "onMeasure()方法，UNSPECIFIED");

                break;
        }


       // Log.i("CustomDetailsImageView类", "onMeasure()方法，width=" + width + "::height" + height + "::" + h);
        super.onMeasure(width, h);
    }
}
