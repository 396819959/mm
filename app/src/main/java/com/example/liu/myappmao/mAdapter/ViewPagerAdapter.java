package com.example.liu.myappmao.mAdapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


public class ViewPagerAdapter extends PagerAdapter {
    private Context context;

    private HomePageRecyclerViewAdapter adapter;
    private List<ImageView> iv;
    private ImageView image;

    public ViewPagerAdapter(Context context, int[] bit) {
        Log.i(getClass().getSimpleName(), "bit长度" + bit.length);
        iv = new ArrayList<ImageView>();
        this.context = context;

        for (int i = 0; i < bit.length; i++) {
            image = new ImageView(context);
            image.setImageResource(bit[i]);
            iv.add(image);
        }

    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);


        container.removeView(iv.get(position % iv.size()));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int d = position % iv.size();

        View v = iv.get(d);

        ViewGroup vg = (ViewGroup) v.getParent();


        if (vg != null) {
            vg.removeAllViews();
        }
        container.addView(v);

        return iv.get(d);
    }
}
