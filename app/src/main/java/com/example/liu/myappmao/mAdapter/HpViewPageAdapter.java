package com.example.liu.myappmao.mAdapter;

import android.app.Fragment;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class HpViewPageAdapter extends PagerAdapter {
    private List<Fragment> list;
    private Context context;

    public HpViewPageAdapter(Context context, List<Fragment> list) {
        this.context = context;
        this.list = list;


    }




    @Override
    public int getCount() {

        return list.size() != 0 ? list.size():0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);


    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}
