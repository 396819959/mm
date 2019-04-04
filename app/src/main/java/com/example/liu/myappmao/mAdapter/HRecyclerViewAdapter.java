package com.example.liu.myappmao.mAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liu.myappmao.R;
import com.example.liu.myappmao.itemData.HomePageRvItemDate;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HRecyclerViewAdapter extends RecyclerView.Adapter<HRecyclerViewAdapter.hViewHolser> {
    private Context context;
    private List<HomePageRvItemDate> list;

    public HRecyclerViewAdapter(Context context, List<HomePageRvItemDate> list) {
     //   Log.i(getClass().getSimpleName(),"横向滑动中的List长度"+list.size());
       // notifyDataSetChanged();
        this.context = context;
        this.list = list;

    }

    @Override
    public hViewHolser onCreateViewHolder(ViewGroup parent, int viewType) {
        View rvView = LayoutInflater.from(context).inflate(R.layout.h_homepage_rv_item, null);

        return new hViewHolser(rvView);
    }

    @Override
    public void onBindViewHolder(hViewHolser holder, int position) {
        Picasso.with(context).load(list.get(position).getPic()).placeholder(R.drawable.miaol).into(holder.iv);//transform(new MyTransformation(context)).into(holder.iv);
        holder.title.setText(String.valueOf(list.get(position).getTitle()));
        holder.tmj.setText(String.valueOf(list.get(position).getOrgPrice()));
        holder.qhj.setText(String.valueOf(list.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class hViewHolser extends RecyclerView.ViewHolder {
        public ImageView iv;
        public TextView title;
        public TextView tmj;
        public TextView qhj;

        public hViewHolser(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new mClick());
            iv = itemView.findViewById(R.id.h_homepage_rv_imageview_id);
            title = itemView.findViewById(R.id.h_homepage_rv_title_id);
            tmj = itemView.findViewById(R.id.h_homepage_rv_tmj_id);
            qhj = itemView.findViewById(R.id.h_homepage_rv_qhj_id);
        }
    }


    public class mClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Log.i(getClass().getSimpleName(),"横向滑动onClick（）方法");

        }
    }
}
