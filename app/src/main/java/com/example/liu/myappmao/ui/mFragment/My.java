package com.example.liu.myappmao.ui.mFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.liu.myappmao.R;
import com.example.liu.myappmao.ui.AbFragment;


@SuppressLint("ValidFragment")
public class My extends AbFragment {


    public My(Context context) {
        super(context);
    }

    @Override
    public View initView(LayoutInflater inflater) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_my, null);

        return view;
    }
}
