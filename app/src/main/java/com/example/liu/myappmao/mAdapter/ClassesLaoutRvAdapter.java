package com.example.liu.myappmao.mAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liu.myappmao.R;

public class ClassesLaoutRvAdapter extends RecyclerView.Adapter<ClassesLaoutRvAdapter.mViewHolder> {

    private String[] itemData;
    private Context context;

    public ClassesLaoutRvAdapter(Context context) {
        this.context = context;

    }

    public void setRefreshItem(String[] itemData) {
        this.itemData = null;
        this.itemData = itemData;
        notifyDataSetChanged();
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new mViewHolder(View.inflate(context, R.layout.classes_layout_classesdetails_item, null));
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, final int position) {
        holder.tv.setText(itemData[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"点击了"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (itemData == null || itemData.length == 0) {

            return 0;
        } else {
            return itemData.length;
        }

    }

    public class mViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView iv;

        public mViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.classeslayout_item_title_id);
            iv = itemView.findViewById(R.id.classeslayout_item_imageview_id);
        }
    }
}
