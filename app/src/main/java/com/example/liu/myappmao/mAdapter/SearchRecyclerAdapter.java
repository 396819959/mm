package com.example.liu.myappmao.mAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liu.myappmao.R;
import com.example.liu.myappmao.itemData.SearchPageItemDate;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.mViewHolder> {
    private Context context;
    private List<SearchPageItemDate> slist;

    public SearchRecyclerAdapter(Context context, List<SearchPageItemDate> list, Boolean isSearch) {

        this.context = context;
        if (isSearch == false) {

        } else {




        }

        if(slist == null){
         //   Log.i("SearchRecyclerAdapter", "SearchRecyclerAdapter方法,list为空" );

        }else{
         //   Log.i("SearchRecyclerAdapter", "SearchRecyclerAdapter方法,list不为空" );

        }
       // notifyDataSetChanged();
        slist = list;


    }


    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.search_recyclerview_item, null);
        return new mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        //  Log.i("SearchRecyclerAdapter", "onBindViewHolder方法=" );
        Picasso.with(context).load(slist.get(position).getGoods_pic()).into(holder.iv);
        holder.title.setText(slist.get(position).getGoods_title());
        holder.tmPrice.setText("" + slist.get(position).getGoods_price());
        holder.qhj.setText("" + slist.get(position).getCoupon_amount());
        holder.sales.setText("" + slist.get(position).getGoods_sale_num());
    }

    @Override
    public int getItemCount() {
       // Log.i("SearchRecyclerAdapter", "getItemCount方法,list长度=" + slist.size());

        return slist.size() != 0 ? slist.size() : 0;
    }

    public class mViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView title, tmPrice, sales, qhj;

        public mViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.search_recyclerview_image_id);
            title = itemView.findViewById(R.id.search_recyclerview_title_id);
            tmPrice = itemView.findViewById(R.id.search_recyclerview_tmj_id);
            sales = itemView.findViewById(R.id.search_recyclerview_ys_id);
            qhj = itemView.findViewById(R.id.search_recyclerview_qhj_id);
        }
    }
}
