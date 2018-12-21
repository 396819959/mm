package com.example.liu.myappmao.mViewHolder;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioGroup;

import com.example.liu.myappmao.R;
import com.example.liu.myappmao.mAdapter.HRecyclerViewAdapter;
import com.example.liu.myappmao.mAdapter.ViewPagerAdapter;

/**
 * Created by Administrator on 2018/12/21.
 */

public class BViewHolder {

    public static ViewPagerAdapter adapter;
    public static ViewPager vp;
    public static RadioGroup rg;
    public static RecyclerView hRv;
    public static HRecyclerViewAdapter hAdapter;

    public BViewHolder() {

    }


    public class bottomViweHolder extends BaseViewHolder{

        public bottomViweHolder(View itemView) {
            super(itemView);
        }
    }

    public static class mViewHolder extends BaseViewHolder {


        public mViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.homepage_rv_imageview_id);
            title = itemView.findViewById(R.id.homepage_rv_title_id);
            tmj = itemView.findViewById(R.id.homepage_rv_tmj_id);
            qhj = itemView.findViewById(R.id.homepage_rv_qhj_id);
        }


    }

    public static class vpViewHolder extends BaseViewHolder {

        public vpViewHolder(View itemView,ViewPager vp) {
            super(itemView);
            vp = itemView.findViewById(R.id.homepage_vp_id);


            // vp.setAdapter(adapter);
            //   vp.setAdapter();
        }
    }


    public static class radioViewHolder extends BaseViewHolder {

        public radioViewHolder(View itemView,RadioGroup rg) {
            super(itemView);
            rg = itemView.findViewById(R.id.homepage_rg_id);
        }
    }

    public static class hRvViewHolder extends BaseViewHolder {

        public hRvViewHolder(View itemView, RecyclerView hRv) {
            super(itemView);
            hRv = itemView.findViewById(R.id.h_rv_item_id);
            // hAdapter = new HRecyclerViewAdapter(context, list);
           // hRv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));


        }
    }


}
