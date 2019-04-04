package com.example.liu.myappmao.customView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Display;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class CustomLayoutView extends LinearLayout {
    private Context context;
    private WindowManager wm;
    private final int VALUE = 2;
    Display disp;

    public CustomLayoutView(Context context) {
        this(context, null);
    }

    public CustomLayoutView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomLayoutView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        disp = wm.getDefaultDisplay();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);



//        int hWidth = disp.getWidth();
//
//        int width = hWidth / VALUE;
//        int height = width;
//        int count = getChildCount();
//        for (int i = 0; i < count; i++) {
//            View v;
//            switch (i) {
//
//
//                case 0:
//                    v = getChildAt(0);
//
//                    Log.i(context.getClass().getSimpleName(), "屏幕宽：" + hWidth + "自定义视图宽高：" + width + ":" + height);
//                    v.measure(width, height);
//
//                    break;
//
//            }
//
//
 //       }


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

    }
}
