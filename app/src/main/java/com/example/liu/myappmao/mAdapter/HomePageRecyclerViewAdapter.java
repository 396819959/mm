package com.example.liu.myappmao.mAdapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.liu.myappmao.R;
import com.example.liu.myappmao.itemData.HomePageRvItemDate;
import com.example.liu.myappmao.mViewHolder.BViewHolder;
import com.example.liu.myappmao.mViewHolder.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2018/12/20.
 */

public class HomePageRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private Context context;
    private List<HomePageRvItemDate> list;
    private ViewPagerAdapter vpAdapter;
    private ViewPager vp;
    private RadioGroup rg;
    private RecyclerView hRv;
    private HRecyclerViewAdapter hAdapter;
    private BViewHolder bViewHolder;

    public HomePageRecyclerViewAdapter(Context context, List<HomePageRvItemDate> list, ViewPagerAdapter vpAdapter, HRecyclerViewAdapter hAdapter) {
        //   Log.i(getClass().getSimpleName(), "list长度：" + list.size());
        this.context = context;
        this.list = list;
        this.vpAdapter = vpAdapter;
        this.hAdapter = hAdapter;
       // bViewHolder = new BViewHolder();
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder vh = null;


        if (viewType == 0) {

            View vpView = LayoutInflater.from(context).inflate(R.layout.homepage_viewpage, null);
            return new vpViewHolder(vpView);

        } else if (viewType == 1) {

            View rgView = LayoutInflater.from(context).inflate(R.layout.homepage_radiogroup_item, null);
            return new radioViewHolder(rgView);

        }
        else if(viewType == 7){


            return new btViewHolder(LayoutInflater.from(context).inflate(R.layout.bt_two_item,null));
        }

        else if (viewType == 8) {


            View hView = LayoutInflater.from(context).inflate(R.layout.h_recyclerview_item, null);
            return new hRvViewHolder(hView);


        }
        else if (viewType == 9) {


            View bttpView = LayoutInflater.from(context).inflate(R.layout.bttp_rv_item, null);
            return new bttpViewHolder(bttpView);


        }
        else {


            View rvView = LayoutInflater.from(context).inflate(R.layout.homepage_rv_item, null);
            return new mViewHolder(rvView);
        }


    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        if (holder instanceof mViewHolder) {
            Picasso.with(context).load(list.get(position).getPic()).placeholder(R.drawable.image_personal_head_gray).into(holder.iv);
            holder.title.setText(String.valueOf(list.get(position).getTitle()));
            holder.tmj.setText(String.valueOf(list.get(position).getOrgPrice()));
            holder.qhj.setText(String.valueOf(list.get(position).getPrice()));


        } else if (holder instanceof vpViewHolder) {


            vp.setAdapter(vpAdapter);
           vp.setCurrentItem(Integer.MAX_VALUE / 2);


        } else if (holder instanceof hRvViewHolder) {
            hRv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            hRv.setAdapter(hAdapter);

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public class bottomViweHolder extends BaseViewHolder{

        public bottomViweHolder(View itemView) {
            super(itemView);
        }
    }

    public class mViewHolder extends BaseViewHolder {


        public mViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.homepage_rv_imageview_id);
            title = itemView.findViewById(R.id.homepage_rv_title_id);
            tmj = itemView.findViewById(R.id.homepage_rv_tmj_id);
            qhj = itemView.findViewById(R.id.homepage_rv_qhj_id);
        }


    }

    public class vpViewHolder extends BaseViewHolder {

        public vpViewHolder(View itemView) {
            super(itemView);
            vp = itemView.findViewById(R.id.homepage_vp_id);


          // vp.setAdapter(adapter);
            //   vp.setAdapter();
        }
    }


    public class radioViewHolder extends BaseViewHolder {

        public radioViewHolder(View itemView) {
            super(itemView);
            rg = itemView.findViewById(R.id.homepage_rg_id);
        }
    }

    public class hRvViewHolder extends BaseViewHolder {

        public hRvViewHolder(View itemView) {
            super(itemView);
            hRv = itemView.findViewById(R.id.h_rv_item_id);
           // hAdapter = new HRecyclerViewAdapter(context, list);
            hRv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));


        }
    }

    public class btViewHolder extends BaseViewHolder {

        public btViewHolder(View itemView) {
            super(itemView);
            itemView.findViewById(R.id.bt_two_item_id);
        }
    }

    public class bttpViewHolder extends BaseViewHolder {

        public bttpViewHolder(View itemView) {
            super(itemView);
            itemView.findViewById(R.id.bttp_rv_item_id);
        }
    }
}
