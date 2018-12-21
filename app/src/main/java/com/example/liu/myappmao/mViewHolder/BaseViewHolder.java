package com.example.liu.myappmao.mViewHolder;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/12/20.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

//    public ViewPager vp;
    public ImageView iv;
    public TextView title;
    public TextView tmj;
    public TextView qhj;
    public BaseViewHolder(View itemView) {
        super(itemView);
    }
}
