package com.example.liu.myappmao.mAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2018/12/20.
 */

public abstract class BaseRecyclerViewAdapter extends RecyclerView.Adapter {
    public Context context;
    public List list;

    public BaseRecyclerViewAdapter() {
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public abstract void onBindViewHolder(LinearRecyclerViewAdapter.linearViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return 0;
    }

    public class BaseViewHolser extends RecyclerView.ViewHolder {

        public BaseViewHolser(View itemView) {
            super(itemView);
        }
    }
}
