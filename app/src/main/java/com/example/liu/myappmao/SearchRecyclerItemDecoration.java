package com.example.liu.myappmao;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SearchRecyclerItemDecoration extends RecyclerView.ItemDecoration {


    public SearchRecyclerItemDecoration() {
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        //if(position != 0){
        outRect.right = 20;
    //}
    }
}
