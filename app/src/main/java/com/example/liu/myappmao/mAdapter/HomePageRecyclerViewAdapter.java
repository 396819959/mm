package com.example.liu.myappmao.mAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.liu.myappmao.MyTransformation;
import com.example.liu.myappmao.R;
import com.example.liu.myappmao.itemData.HomePageRvItemDate;
import com.example.liu.myappmao.mViewHolder.BViewHolder;
import com.example.liu.myappmao.mViewHolder.BaseViewHolder;
import com.example.liu.myappmao.ui.ClassesFragmentActivity.ClassesBoutiqueFragment;
import com.example.liu.myappmao.ui.CommodityDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/20.
 */

public class HomePageRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private Context context;
    // private List<HomePageRvItemDate> list = new ArrayList<HomePageRvItemDate>();
    private List<HomePageRvItemDate> list;

    private ViewPagerAdapter vpAdapter;
    private HRecyclerViewAdapter hAdapter;
    Intent intent = new Intent();

    private RadioGroup rg;
    // private ViewPager vp;
    private RecyclerView hRv;

    private BViewHolder bViewHolder;
    private MyTransformation trans;

    private ClassesBoutiqueFragment.CreateItemLayout creadteItem;


    public HomePageRecyclerViewAdapter(Context context, List<HomePageRvItemDate> list, ClassesBoutiqueFragment.CreateItemLayout creadteItem) {
        this.context = context;

        this.list = list;

        this.creadteItem = creadteItem;

        //  trans = new MyTransformation(context);

        trans = new MyTransformation(context);
    }

    public HomePageRecyclerViewAdapter(Context context, List<HomePageRvItemDate> list, ViewPagerAdapter vpAdapter, HRecyclerViewAdapter hAdapter, ClassesBoutiqueFragment.CreateItemLayout creadteItem) {

        //  notifyDataSetChanged();
        this.context = context;

        this.list = list;
        //  Log.i(getClass().getSimpleName(), "HomePageRecyclerViewAdapter()构造方法list长度："+list.size());

        this.creadteItem = creadteItem;
        //  trans = new MyTransformation(context);

        this.vpAdapter = vpAdapter;
        this.hAdapter = hAdapter;

        trans = new MyTransformation(context);
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder vh = null;


        if (viewType == 0) {

            // View vpView = LayoutInflater.from(context).inflate(R.layout.homepage_viewpage, null);
            return new vpViewHolder(creadteItem.viewPagerView);
            //  return new vpViewHolder(vpView);
        } else if (viewType == 1) {

            //View rgView = LayoutInflater.from(context).inflate(R.layout.homepage_radiogroup_item, null);
            return new radioViewHolder(creadteItem.radioGroupView);
            // return new radioViewHolder(rgView);

        } else if (viewType > 1 && viewType < 6) {


            View rvView = LayoutInflater.from(context).inflate(R.layout.homepage_rv_item, null);
            // return new mViewHolder(creadteItem.RecyclerItemView);
            return new mViewHolder(rvView);

        } else if (viewType == 6) {

            //标题
            return new btViewHolder(creadteItem.titleView);
        } else if (viewType == 7) {


            //View hView = LayoutInflater.from(context).inflate(R.layout.h_recyclerview_item, null);
            return new hRvViewHolder(creadteItem.h_recyclerView);
            //   return new hRvViewHolder(hView);


        } else if (viewType == 8) {


            //  View bttpView = LayoutInflater.from(context).inflate(R.layout.bttp_rv_item, null);
            return new bttpViewHolder(creadteItem.two_titleView);
            //  return new bttpViewHolder(bttpView);


        } else if (viewType > 8 && viewType < 13) {
            View rvView = LayoutInflater.from(context).inflate(R.layout.homepage_rv_item, null);
            //   return new mViewHolder(creadteItem.RecyclerItemView);
            return new mViewHolder(rvView);

        } else if (viewType == 13) {


            // View lqbtView = LayoutInflater.from(context).inflate(R.layout.lqbt_item, null);
            return new lqbtViewHolder(creadteItem.three_titleView);
            //   return new lqbtViewHolder(lqbtView);


        } else {

            View lView = LayoutInflater.from(context).inflate(R.layout.linear_view_item, null);
            return new linearViewHolder(lView);

            // return new linearViewHolder(creadteItem.h_recyclerItemView);
        }


    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        //   Log.i(getClass().getSimpleName(), "onBindViewHolder()方法");


        if (holder instanceof mViewHolder) {

            holder.itemView.setOnClickListener(new mClick(position));

            Picasso.with(context).load(list.get(position).getPic()).placeholder(R.drawable.miaol).into(holder.iv);
            holder.title.setText(String.valueOf(list.get(position).getTitle()));
            holder.tmj.setText(String.valueOf(list.get(position).getOrgPrice()));
            holder.qhj.setText(String.valueOf(list.get(position).getPrice()));


        } else if (holder instanceof vpViewHolder) {


            creadteItem.vp.setAdapter(vpAdapter);
            creadteItem.vp.setCurrentItem(Integer.MAX_VALUE / 2);


        } else if (holder instanceof hRvViewHolder) {
            creadteItem.hRv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            creadteItem.hRv.setAdapter(hAdapter);

        } else if (holder instanceof linearViewHolder) {


            holder.itemView.setOnClickListener(new mClick(position));

            Picasso.with(context).load(list.get(position).getPic()).placeholder(R.drawable.miaol).into(holder.hiv);
            holder.htitle.setText(String.valueOf(list.get(position).getTitle()));
            holder.htmj.setText(String.valueOf(list.get(position).getOrgPrice()));
            holder.hqhj.setText(String.valueOf(list.get(position).getPrice()));

        }


    }

    @Override
    public int getItemCount() {
        if (list == null) {
            list = new ArrayList<>(50);
        }
        if (list.size() == 0) {
            //  Log.i(getClass().getSimpleName(), "getItemCount()方法list小于0");
            return 50;
        } else {
            //   Log.i(getClass().getSimpleName(), "getItemCount()方法list大于0");
            return list.size();

        }
        // ? 50 : list.size()

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public class mViewHolder extends BaseViewHolder {


        public mViewHolder(View itemView) {
            super(itemView);
            //  int position = getLayoutPosition();
            // itemView.setOnClickListener(new mClick(position));
            iv = itemView.findViewById(R.id.homepage_rv_imageview_id);
            title = itemView.findViewById(R.id.homepage_rv_title_id);
            tmj = itemView.findViewById(R.id.homepage_rv_tmj_id);
            qhj = itemView.findViewById(R.id.homepage_rv_qhj_id);
        }


    }

    public class vpViewHolder extends BaseViewHolder {

        public vpViewHolder(View itemView) {
            super(itemView);
            //  vp = itemView.findViewById(R.id.homepage_vp_id);
            //  vp.addOnPageChangeListener(new vpClick());

            creadteItem.vp = itemView.findViewById(R.id.homepage_vp_id);
            creadteItem.vp.addOnPageChangeListener(new vpClick());

        }
    }


    public class radioViewHolder extends BaseViewHolder {

        public radioViewHolder(View itemView) {
            super(itemView);

            //rg = itemView.findViewById(R.id.homepage_rg_id);
            creadteItem.rg = itemView.findViewById(R.id.homepage_rg_id);
        }
    }

    public class hRvViewHolder extends BaseViewHolder {

        public hRvViewHolder(View itemView) {
            super(itemView);

            creadteItem.hRv = itemView.findViewById(R.id.h_rv_item_id);

//            creadteItem. hRv = itemView.findViewById(R.id.h_rv_item_id);

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

    public class lqbtViewHolder extends BaseViewHolder {

        public lqbtViewHolder(View itemView) {
            super(itemView);
            itemView.findViewById(R.id.lqbt_item_id);
        }
    }

    public class linearViewHolder extends BaseViewHolder {

        public linearViewHolder(View itemView) {
            super(itemView);
            int position = getLayoutPosition();
            // itemView.setOnClickListener(new mClick(position));
            //  Log.i(getClass().getSimpleName(), "linearViewHolder()方法");
            // itemView.findViewById(R.id.linearLayout_id);
            hiv = itemView.findViewById(R.id.linear_view_image_id);
            htitle = itemView.findViewById(R.id.linear_view_title_id);
            htmj = itemView.findViewById(R.id.linear_view_tmj_id);
            hqhj = itemView.findViewById(R.id.linear_view_qhj_id);
        }
    }


    public class mClick implements View.OnClickListener {
        private int position;

        public mClick(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
          //  Toast.makeText(context, "RecyclerView点击了：" + position + ":" + v.getId(), Toast.LENGTH_SHORT).show();
            intent.setClass(context, CommodityDetailsActivity.class);
            intent.putExtra("list", list.get(position));


            context.startActivity(intent);

            //      Log.i(getClass().getSimpleName(), "onClick()方法:" + hp.getTitle()+";;"+hp.getQuanId());

        }
    }


    public class vpClick implements ViewPager.OnPageChangeListener {


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


}
