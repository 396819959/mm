package com.example.liu.myappmao.mAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liu.myappmao.R;
import com.example.liu.myappmao.itemData.HomePageRvItemDate;
import com.example.liu.myappmao.mViewHolder.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2018/12/22.
 */

public class LinearRecyclerViewAdapter extends BaseRecyclerViewAdapter{
    private Context context;
    private List<HomePageRvItemDate> list;

    public LinearRecyclerViewAdapter(Context context, List<HomePageRvItemDate> list) {
        super();

        this.context = context;
        this.list = list;
    }

    @Override
    public linearViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rvView = LayoutInflater.from(context).inflate(R.layout.linear_view_item, null);

        return new linearViewHolder(rvView);
    }

    @Override
    public void onBindViewHolder(linearViewHolder holder, int position) {
        Picasso.with(context).load(list.get(position).getPic()).placeholder(R.drawable.image_personal_head_gray).into(holder.hiv);
        holder.htitle.setText(String.valueOf(list.get(position).getTitle()));
        holder.htmj.setText(String.valueOf(list.get(position).getOrgPrice()));
        holder.hqhj.setText(String.valueOf(list.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class linearViewHolder extends BaseViewHolder {
        public ImageView hiv;
        public TextView htitle;
        public TextView htmj;
        public TextView hqhj;
        public linearViewHolder(View itemView) {
            super(itemView);
            //itemView.findViewById(R.id.linearLayout_id);
            hiv = itemView.findViewById(R.id.linear_view_image_id);
            htitle = itemView.findViewById(R.id.linear_view_title_id);
            htmj = itemView.findViewById(R.id.linear_view_tmj_id);
            hqhj = itemView.findViewById(R.id.linear_view_qhj_id);
        }
    }
}
