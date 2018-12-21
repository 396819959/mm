package com.example.liu.myappmao;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2018/12/21.
 */

public class MyRcyclerView extends RecyclerView {


    public MyRcyclerView(Context context) {
        this(context,null);
    }

    public MyRcyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyRcyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {


        super(context, attrs, defStyle);



    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}
