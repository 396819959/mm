package com.example.liu.myappmao.mFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.liu.myappmao.MyCountView;
import com.example.liu.myappmao.R;


public class My extends AbFragment {
    MyCountView mv;

    public My(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_my, null);
        mv = view.findViewById(R.id.mv);
        return view;
    }
}
