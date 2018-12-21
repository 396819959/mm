package com.example.liu.myappmao.mFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class AbFragment extends Fragment {
    public Context context;



    public AbFragment(Context context) {
        this.context = context;
    }

    protected AbFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //initData();
        return initView();
    }

    public abstract View initView();

    public void initData() {
    }
}
