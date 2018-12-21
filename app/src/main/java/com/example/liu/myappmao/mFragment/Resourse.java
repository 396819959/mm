package com.example.liu.myappmao.mFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.liu.myappmao.R;


public class Resourse extends AbFragment {
    public volatile Resourse resourse;

    public Resourse(Context context) {
        super(context);
    }

    private Resourse() {
    }

    public Resourse instanca() {
        if (resourse == null) {
            synchronized (Resourse.class) {
                if (resourse == null) {
                    resourse = new Resourse();
                }

            }

        }

        return resourse;
    }


    @Override
    public View initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_resourse, null);
       // layout = view.findViewById(R.id.mLayout_id);
        initData();
        return view;
    }

    @Override
    public void initData() {
        super.initData();

       // Collections.sort();
    }
}
