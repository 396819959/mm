package com.example.liu.myappmao.customView;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.liu.myappmao.R;
import com.example.liu.myappmao.ui.AbFragment;

/**
 * Created by Administrator on 2018/12/24.
 */

public class CustomFragment extends AbFragment {
    private FrameLayout fl;
    private ImageView iv;
    private WindowManager wm;
    private int width;
    private int height;

    public CustomFragment(Context context) {
        super(context);
    }

    @Override
    public View initView(LayoutInflater inflater) {

        fl = new FrameLayout(context);

        iv = new ImageView(context);

        initData();
        return fl;
    }

    @Override
    public void initData() {
        super.initData();

        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) fl.getLayoutParams();

        Display dis = wm.getDefaultDisplay();
        Point point = new Point();


        width = dis.getWidth();
        height = dis.getHeight() / 5;
        Log.i(getClass().getSimpleName(), "获得的视图宽高：" + width + ":" + height);
        // lp.width = width;
        // lp.height = height;
        //fl.setLayoutParams(lp);

        fl.measure(width,height);
        iv.setBackgroundResource(R.drawable.pull_down_animation);



        AnimationDrawable ad = (AnimationDrawable) iv.getBackground();

        fl.addView(iv);

        ad.start();
    }



}
