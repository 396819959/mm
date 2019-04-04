package com.example.liu.myappmao;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2018/12/24.
 */

public class TestRvAdapter extends RecyclerView.Adapter<TestRvAdapter.mViewHovlder> {
    private Context context;
    private List<String> list;
    public mViewHovlder vh;
    public TestRvAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;


    }


    @Override
    public mViewHovlder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new mViewHovlder(LayoutInflater.from(context).inflate(R.layout.pull_linearlayou_item, null));
    }

    @Override
    public void onBindViewHolder(mViewHovlder holder, int position) {
        holder.tv.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class mViewHovlder extends MyRcyclerView.ViewHolder {
        public TextView tv;

        public mViewHovlder(View itemView) {
            super(itemView);


            tv = itemView.findViewById(R.id.pull_linearlayout_item);
        }


    }
}
