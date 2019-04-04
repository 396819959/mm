package com.example.liu.myappmao.customView;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;

public class CustomViewPage extends ViewPager {
    int height;
    int width;
    int mHeight;
    int mWidth;
    int l;
    int t;
    int r;
    int b;
    int vHeight;

    public CustomViewPage(Context context) {
        this(context, null);
    }

    public CustomViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        height = wm.getDefaultDisplay().getHeight();

    }




    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        this.l = l;
        this.t = t;
        this.r = r;
        this.b = b;

        int count = getChildCount();
        ViewParent vp = getParent();
        if (vp instanceof CustomParentLayout) {
            ViewGroup vg = (ViewGroup) vp;
            View parentView = vg.getChildAt(0);
            vHeight = parentView.getHeight();
        }

        //  Log.i("CustomViewPage类", "onLayout方法,vHeight=" + vHeight);


        for (int i = 0; i < count; i++) {
            View v = getChildAt(i);

            if (v instanceof RecyclerView) {
                int hh = b - vHeight;
              v.layout(l, 0, r, hh);
            }
        }


        // Log.i("CustomViewPage类", "onLayout方法height=" +b+ ";;t=" +t);


    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

//        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
//            //  getChildAt(1).layout(l, t, r, b);
//          Log.i("CustomViewPage类", "onTouchEvent方法，滑动");
//
//        }

        return super.onTouchEvent(ev);
    }
}
