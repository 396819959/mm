package com.example.liu.myappmao.customView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

/**
 * 支持上拉和下拉，可使用代码和布局文件设定上下视图，如果未设置则使用默认上下视图。
 * 使用“isUseDefaultPullView(boolean b) ”方法设置是否使用默认上下视图样式，默认为使用。
 */
public class CustomPullLayout extends LinearLayout {


    private View topPullView;
    private View bottomPullView;

    private int pullBoxWidth;
    private int pullBoxHeight;

    private int topBoxWidth;
    private int topBoxHeight;
    private View view;
    private boolean isPullView = true;
    private Context context;
    ImageView iv;
    ProgressBar tPullProgressBar;
    ProgressBar bPullProgressBar;
    LinearLayout.LayoutParams lp;
    private boolean isDefault = true;


    View TestView;
    int awidth;
    int aheight;

    public CustomPullLayout(Context context) {
        this(context, null);
    }

    public CustomPullLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomPullLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        initView();
    }


    @SuppressLint("WrongConstant")
    private void initView() {

        setOrientation(1);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int index = getChildCount();
        // Log.i("CustomPullLayout类", "onMeasure方法,index=" + index);

        setPullView(index);
        TestView = getChildAt(0);
        awidth = TestView.getMeasuredWidth();
        aheight = TestView.getMeasuredHeight();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // Log.i("CustomPullLayout类", "onLayout方法");
        int w;
        int h;
        int ww = 0;
        int hh = 0;

        int index = getChildCount();

//        Log.i("CustomPullLayout类", "onLayout方法, bottomPullView="+bottomPullView.getWidth()+"-;;-"+bottomPullView.getHeight());
        for (int i = 0; i < index; i++) {

            view = getChildAt(i);

            w = view.getMeasuredWidth();
            h = view.getMeasuredHeight();

            // Log.i("CustomPullLayout类", "onLayout方法,index=" + index + "--i=" + i + ":w=" + w + ";;h=" + h);
            view.layout(0, hh, w, hh + h);
            hh = hh + h;
        }

        super.onLayout(changed, l, t, r, b);
    }

    private void setPullView(int index) {


        if (isPullView) {


            if (bottomPullView == null && isDefault == true) {

                addView(setDefaultBottomPullView(), 0);

            }
            if (topPullView == null && isDefault == true) {

                addView(setDefaultTopPullView(), index);

            }
        }
        isPullView = false;
    }


    /**
     * 设置默认下拉栏视图
     *
     * @return
     */
    private ProgressBar setDefaultBottomPullView() {


        bPullProgressBar = new ProgressBar(context);
        bPullProgressBar.setLayoutParams(setLp());

        return bPullProgressBar;
    }


    /**
     * 设置默认上拉视图
     *
     * @return
     */
    private ProgressBar setDefaultTopPullView() {


        tPullProgressBar = new ProgressBar(context);
        tPullProgressBar.setLayoutParams(setLp());

        return tPullProgressBar;

    }

    private LayoutParams setLp() {
        if (lp == null) {
            lp = new LinearLayout.LayoutParams(100, 100);
            lp.gravity = Gravity.CENTER;
        }
        return lp;
    }


    /**
     * 设置下拉视图
     *
     * @param view
     */
    public void setBottomPullView(View view) {
        bottomPullView = view;
        addView(bottomPullView, 0);

    }

    /**
     * 设置上拉视图
     *
     * @param view
     */
    public void setTopPullView(View view) {
        topPullView = view;
        int i = getChildCount();
        addView(topPullView, i);
        // Log.i("CustomPullLayout类", "setTopPullView方法i=" + i);
    }

    /**
     * 是否使用默认上下视图，默认为使用
     *
     * @param b
     */
    public void isUseDefaultPullView(boolean b) {
        isDefault = b;
    }


//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//       // getParent().requestDisallowInterceptTouchEvent(true);
//     //
//
//        int event = ev.getAction();
//
//        Log.i("CustomPullLayout类", "dispatchTouchEvent方法="+event);
//        switch (event) {
//
//            case MotionEvent.ACTION_DOWN:
//
//                break;
//            case MotionEvent.ACTION_MOVE:
//                Log.i("CustomPullLayout类", "dispatchTouchEvent方法i=滑动");
//                //  Log.i("CustomPullLayout类", "dispatchGenericMotionEvent方法..." +getScaleY() +"..."+getY()+"..."+getPivotY()+"..."+getRotationY()+"..."+getScrollY()+"..."+getTranslationY());
//                //  break;
//
//                TestView.setY(ev.getY());
//                TestView.setX(ev.getX());
//              //  return true;
//            break;
//            case MotionEvent.ACTION_UP:
//
//                break;
//
//
//        }
//
//        return false;
//    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int event = ev.getAction();
        Log.i("CustomPullLayout类", "onInterceptTouchEvent方法i=" + event);

        final MotionEvent vtev = MotionEvent.obtain(ev);
        vtev.setLocation(100,100);
        switch (event) {

            case MotionEvent.ACTION_DOWN:

                Log.i("CustomPullLayout类", "onInterceptTouchEvent方法..按下");
                break;
            case MotionEvent.ACTION_MOVE:


                Log.i("CustomPullLayout类", "onInterceptTouchEvent方法..滑动");

                //  Log.i("CustomPullLayout类", "onInterceptTouchEvent方法..." +getScaleY() +"..."+getY()+"..."+getPivotY()+"..."+getRotationY()+"..."+getScrollY()+"..."+getTranslationY());
               return true;
            case MotionEvent.ACTION_UP:
                Log.i("CustomPullLayout类", "onInterceptTouchEvent方法...抬起");
                break;


        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:

                Log.i("CustomPullLayout类", "onTouchEvent方法.按下");

                break;
            case MotionEvent.ACTION_MOVE:

                Log.i("CustomPullLayout类", "onTouchEvent方法.滑动");

                //  Log.i("CustomPullLayout类", "onInterceptTouchEvent方法..." +getScaleY() +"..."+getY()+"..."+getPivotY()+"..."+getRotationY()+"..."+getScrollY()+"..."+getTranslationY());

                break;
            case MotionEvent.ACTION_UP:

                Log.i("CustomPullLayout类", "onTouchEvent方法.抬起");

                break;


        }

        return super.onTouchEvent(ev);
    }


}
