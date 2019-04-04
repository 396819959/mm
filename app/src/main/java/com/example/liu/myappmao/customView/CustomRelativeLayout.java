package com.example.liu.myappmao.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class CustomRelativeLayout extends RelativeLayout {
    public CustomRelativeLayout(Context context) {
        this(context,null);
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
