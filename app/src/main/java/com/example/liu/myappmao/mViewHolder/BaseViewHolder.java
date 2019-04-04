package com.example.liu.myappmao.mViewHolder;

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

    public ImageView hiv;
    public TextView htitle;
    public TextView htmj;
    public TextView hqhj;
    public BaseViewHolder(View itemView) {


        super(itemView);
    }

//    public class mClick implements View.OnClickListener{
//
//        @Override
//        public void onClick(View v) {
//
//        }
//    }
}
