package com.example.liu.myappmao;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ClassesRvDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.top = 56;

    }
}
