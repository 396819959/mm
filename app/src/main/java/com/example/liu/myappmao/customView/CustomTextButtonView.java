package com.example.liu.myappmao.customView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

@SuppressLint("AppCompatCustomView")
public class CustomTextButtonView extends Button {
    public CustomTextButtonView(Context context) {
        this(context,null);
    }

    public CustomTextButtonView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomTextButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



}
