package com.example.liu.myappmao;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CustomFootTopView extends ImageView {


    public CustomFootTopView(Context context) {
        this(context,null);
    }

    public CustomFootTopView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomFootTopView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



}
