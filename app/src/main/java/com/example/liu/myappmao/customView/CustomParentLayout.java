package com.example.liu.myappmao.customView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class CustomParentLayout extends LinearLayout {
    private View oneView;
    private View twoView;
    private View threeView;
    private ViewGroup fourView;
    ViewGroup fourChild;
    private View goneView;

    private View cView;
    private int oneViewWidth;
    private int oneViewHeight;

    private int twoViewWidth;
    private int twoViewHeight;

    private int threeViewWidth;
    private int threeViewHeight;

    private int fourViewWidth;
    private int fourViewHeight;

    VelocityTracker vt;
    private int downY;
    private int downX;

    private int topY;
    private int belowY;

    private int mActivePointerId = INVALID_POINTER;


    private static final int INVALID_POINTER = -1;

    private static final int INVALID_SIZE = -1;
    private final int TOP_POSITION = 0;
    private int oneTopScrollY;
    private int oneBottomScrollY = 0;

    int twoTopScrollY;
    int twoBottomScrollY;

    int threeTopScrollY;
    int threeBottomScrollY;
    int hh;
    private boolean mIsBeingDragged = false;
    /**
     * 向上滑动时记录的Y轴起始位置
     */
    int tY;
    /**
     * 向下滑动时记录的Y轴起始位置
     */
    int bY;

    int viewY;
    int tViewY;
    int thViewY;

    int scroo;
    /**
     * 视图threeView的顶部位置
     */
    int tBHeight;
    int bHeig;
    int oneTopHeight = 0;
    int oneBottomHeight;

    int twoBottomHeight;
    int thBHeight;

    int l, t, r, b;
    boolean canScrollVertically = true;
    Context context;
    WindowManager wm;
    GestureDetector mGestureDetector;
    int height;
    boolean a = false;
    boolean isUp = false;
    boolean isUpState = false;

    boolean isPullY = true;
    boolean isPullX = true;
    boolean isFirstPull = true;

    public CustomParentLayout(Context context) {
        this(context, null);
    }

    public CustomParentLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomParentLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        // mGestureDetector = new GestureDetector(context, new mGestureListener());
      //  wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
     //   height = wm.getDefaultDisplay().getHeight();

    }

    /**
     * 在布局期间，当此视图的大小发生更改时，将调用此函数。如果您刚刚被添加到视图层次结构中，您将使用旧值0被调用。
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        getChildAt(2).setVisibility(View.GONE);

        for(int i = 0;i < getChildCount();i++){
           View v = getChildAt(i);
            if(v instanceof ViewPager){

                fourChild = (ViewGroup) v;
            }


        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        this.l = l;
        this.t = t;
        this.r = r;
        this.b = b;
        int topLocation = 0;
        int bottomLocation = 0;
        int childCount = getChildCount();


        for (int i = 0; i < childCount; i++) {


            View childView = getChildAt(i);


            int mWidth = childView.getMeasuredWidth();
            int mHeight = childView.getMeasuredHeight();

            if (childView.getVisibility() != View.GONE) {

                bottomLocation = bottomLocation + mHeight;
                childView.layout(l, topLocation, r, bottomLocation);
                topLocation = topLocation + mHeight;
            }
        }

        oneView = getChildAt(0);
        twoView = getChildAt(1);
        threeView = getChildAt(2);
        fourView = (ViewGroup) getChildAt(3);


        oneViewWidth = oneView.getMeasuredWidth();
        oneViewHeight = oneView.getMeasuredHeight();


        twoViewWidth = twoView.getMeasuredWidth();
        twoViewHeight = twoView.getMeasuredHeight();

        threeViewWidth = threeView.getMeasuredWidth();
        threeViewHeight = threeView.getMeasuredHeight();

        fourViewWidth = fourView.getMeasuredWidth();
        fourViewHeight = fourView.getMeasuredHeight();
        //    Log.i("CustomParentLayout类", "onLayout方法,fourViewWidth=" + fourViewWidth +  "::fourViewHeight" + fourViewHeight);


        if (a == false) {
            bHeig = b + oneViewHeight;
            tBHeight = oneViewHeight + twoViewHeight; //视图twoView的底边位置
            //  twoBottomScrollY = oneViewHeight;
            //   threeBottomScrollY = twoViewHeight;
            hh = threeViewHeight + oneViewHeight + oneViewHeight + twoViewHeight;

            oneBottomScrollY = (int) oneView.getY();
            twoBottomScrollY = (int) twoView.getY();
            threeBottomScrollY = (int) fourView.getY();


            //     Log.i("CustomParentLayout类", "layout方法threeViewHeight=" + thBHeight + "--b=" + bHeig + "threeViewId=" + threeView.getId() + ";;cViewiD=" + cView.getId());


        } else {
            //   Log.i("CustomParentLayout类", "layout方法");
            viewLayout();

        }


    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        int moveY = (int) event.getY();
        int moveX = (int) event.getX();

        int y = moveY - downY;
        int x = moveX - downX;

        int valueY = Math.abs(y);
        int valueX = Math.abs(x);


        onInterceptTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        // requestDisallowInterceptTouchEvent(true);//为false表示拦截touch事件不继续传递
        int motion = event.getAction();
        viewY = (int) oneView.getY();
        tViewY = (int) twoView.getY();
        thViewY = (int) fourView.getY();
        //    Log.i("CustomParentLayout类", "onInterceptTouchEvent方法=");

        //  requestDisallowInterceptTouchEvent(!canScrollVertically);
        switch (motion) {
            case MotionEvent.ACTION_DOWN:
                //  Log.i("onInterceptTouchEvent类", "按下方法");
                downY = (int) event.getY();
                downX = (int) event.getX();
                belowY = downY;
                topY = downY;

                tY = downY;
                bY = downY;

                break;
            case MotionEvent.ACTION_MOVE:
                // Log.i("onInterceptTouchEvent类", "滑动方法");
                int moveY = (int) event.getY();
                int moveX = (int) event.getX();

                int y = moveY - downY;
                int x = moveX - downX;

                int valueY = Math.abs(y);
                int valueX = Math.abs(x);


                if (valueY > valueX && isPullY) {
                    //  Log.i("onInterceptTouchEvent类", "滑动方法1");

                    isPullX = false;


                    if (viewY <= -oneViewHeight) {
                        //  Log.i("onInterceptTouchEvent类", "滑动方法1");
                        // this.requestDisallowInterceptTouchEvent(true);

                        isFirstPull = false;
                        //   return false;

                    } else {
                        //   Log.i("onInterceptTouchEvent类", "滑动方法2");
                        //  onTouchEvent(event);
                        // return true;
                    }


                    //  if(isFirstPull == true){

                    //     onTouchEvent(event);
                    //  }
                    onTouchEvent(event);
                    //  return true;
                    //     requestDisallowInterceptTouchEvent(false);

                } else if (valueY < valueX && isPullX) {
                    //  Log.i("onInterceptTouchEvent类", "滑动方法2");
                    // requestDisallowInterceptTouchEvent(true);
                    isPullY = false;
                } else if (valueY < valueX && isPullY) {
                    // Log.i("onInterceptTouchEvent类", "滑动方法3");

                    //  requestDisallowInterceptTouchEvent(false);

                }


                break;
            case MotionEvent.ACTION_UP:

                isUpState = false;

                int h = oneViewHeight / 2;

                if (viewY < -h) {
                    oneTopHeight = -oneViewHeight;
                    oneBottomHeight = TOP_POSITION;
                    twoBottomHeight = twoViewHeight;
                    viewLayout();

                } else if (viewY > -h) {

                    oneTopHeight = TOP_POSITION;
                    oneBottomHeight = oneViewHeight;
                    twoBottomHeight = tBHeight;
                    viewLayout();

                }
                oneTopScrollY = viewY;
                twoTopScrollY = tViewY;
                threeTopScrollY = thViewY;

                oneBottomScrollY = viewY;
                twoBottomScrollY = tViewY;
                threeBottomScrollY = thViewY;
                isPullX = true;
                isPullY = true;
                //   requestDisallowInterceptTouchEvent(true);

                break;

        }


        return super.onInterceptTouchEvent(event);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        viewY = (int) oneView.getY();
        //   testOneY = oneView.getTop();
        tViewY = (int) twoView.getY();
        thViewY = (int) fourView.getY();
        int moveY = (int) event.getY();

        mActivePointerId = event.getPointerId(0);
        int y = (int) event.getY(0);
        //  Log.i("CustomParentLayout类", "onTouchEvent方法testOneY=" + viewY + ";;y=" + y);


        switch (event.getAction()) {

            case MotionEvent.ACTION_MOVE:
                //  Log.i("CustomParentLayout类", "onTouchEvent方法");
                if (belowY < moveY) {

                    isCanScroll();
                    isFirstPull = true;
                    //    Log.i("CustomParentLayout类", "onTouchEvent方法，下滑");
                    OutSide();
                    belowY = moveY;
                    topY = belowY;
                    bY = moveY;
                    oneBottomScrollY = viewY;
                    twoBottomScrollY = tViewY;
                    threeBottomScrollY = thViewY;
                    belowScroll(moveY, event);
                    // return true;
                } else if (topY > moveY) {
                    isFirstPull = true;
                    //   Log.i("CustomParentLayout类", "onTouchEvent方法,上滑");
                    OutSide();
                    topY = moveY;
                    belowY = topY;
                    tY = moveY;
                    oneTopScrollY = viewY;
                    twoTopScrollY = tViewY;
                    threeTopScrollY = thViewY;

                    topScroll(moveY, event);
                    // return true;

                }
                //    Log.i("CustomParentLayout类", "onTouchEvent方法testOneY=" + testOneY);
                break;
            case MotionEvent.ACTION_UP:

                isUpState = false;

                int h = oneViewHeight / 2;

                if (viewY < -h) {
                    oneTopHeight = -oneViewHeight;
                    oneBottomHeight = TOP_POSITION;
                    twoBottomHeight = twoViewHeight;
                    viewLayout();

                } else if (viewY > -h) {

                    oneTopHeight = TOP_POSITION;
                    oneBottomHeight = oneViewHeight;
                    twoBottomHeight = tBHeight;
                    viewLayout();

                }
                oneTopScrollY = viewY;
                twoTopScrollY = tViewY;
                threeTopScrollY = thViewY;

                oneBottomScrollY = viewY;
                twoBottomScrollY = tViewY;
                threeBottomScrollY = thViewY;
                //  Log.i("CustomParentLayout类", "onTouchEvent方法,抬起");

                break;
        }
        //  Log.i("onTouchEvent类", "方法");
        //   onInterceptTouchEvent(event);
        return false;
    }


    private void topScroll(int moveY, MotionEvent event) {

        int tScroll = bY - moveY;
        //   Log.i("CustomParentLayout类", "上滑方法false=" + isUpState);

        if (viewY > -oneViewHeight) {

            a = true;
            isUpState = false;


            oneTopHeight = oneBottomScrollY - tScroll;
            oneBottomHeight = twoBottomScrollY - tScroll;
            twoBottomHeight = threeBottomScrollY - tScroll;
            // OutSide();

          //     Log.i("CustomParentLayout类", "上滑  方法，oneTopHeight=" + oneTopHeight);

            viewLayout1(oneTopHeight);

            viewLayout();

        } else {
            //    Log.i("CustomParentLayout类", "上滑方法false=" + isUpState);
            isUpState = true;
            mIsBeingDragged = true;


        }

    }

    private void belowScroll(int moveY, MotionEvent event) {
        int bScroll = moveY - tY;

      //  Log.i("CustomParentLayout类", "下滑方法viewY=" + viewY);

        if (viewY < TOP_POSITION) {

            a = true;
            isUpState = false;


            oneTopHeight = oneTopScrollY + bScroll;
            oneBottomHeight = twoTopScrollY + bScroll;
            twoBottomHeight = threeTopScrollY + bScroll;
            // OutSide();
          //  Log.i("CustomParentLayout类", "下滑  方法，oneTopHeight=" + oneTopHeight);

            viewLayout1(-oneTopHeight);

            viewLayout();

        } else {
            //  Log.i("CustomParentLayout类", "下滑方法false=" + isUpState);
            isUpState = true;
            mIsBeingDragged = true;
            //  threeView.dispatchTouchEvent(event);
            // dispatchTouchEvent(event);
            // onInterceptTouchEvent(event);
            // parent.requestDisallowInterceptTouchEvent(true);
        }

    }

    private void OutSide() {


        if (viewY < -oneViewHeight) {
            //  Log.i("CustomParentLayout类", "OutSide方法1viewY=" + viewY);
            oneTopHeight = -oneViewHeight;
            oneBottomHeight = TOP_POSITION;
            twoBottomHeight = twoViewHeight;

            viewLayout();

        } else if (viewY > TOP_POSITION) {
            //  Log.i("CustomParentLayout类", "OutSide方法2viewY=" + viewY);
            oneTopHeight = TOP_POSITION;
            oneBottomHeight = oneViewHeight;
            twoBottomHeight = tBHeight;

            viewLayout();

        }


    }

    private void viewLayout1(int scro) {
//

        scroo = scro;
        if (scroo > -oneViewHeight && scroo <= 0) {

            // oneView.scrollTo(0,-scro);
            //  twoView.scrollTo(0, -scro);
            //   fourView.scrollTo(0,-scro);
            // this.scrollTo(0,-scro);
        } else if (scroo < oneViewHeight) {
            //    Log.i("CustomParentLayout类", "viewLayout方法scro=" + scro+"::top="+oneView.getY());
            // this.scrollTo(0,scro);
        }
        //  layout(l, scro, r, b);
        //oneView.scrollTo(0,scro);
        // twoView.scrollTo(0, scro);
        //  threeView.scrollTo(0, scro);

    }

    private void viewLayout() {

        //  layout(l, oneTopHeight, r, b);
        oneView.layout(l, oneTopHeight, r, oneBottomHeight);
        twoView.layout(l, oneBottomHeight, r, twoBottomHeight);
        fourView.layout(l, twoBottomHeight, r, b);


        //  Log.i("CustomParentLayout类", "viewLayout方法b=" + b+"--getBottom="+fourView.getBottom() +"--getTop="+fourView.getTop());
     //   Log.i("CustomParentLayout类", "viewLayout方法getScrollY()=" + getScrollY()+";;getScaleY="+fourChild.getScaleY()+";;getY="+fourChild.getY()+";;getPivotY="+fourChild.getPivotY()+";;getRotationY="+fourChild.getRotationY()+";;getTranslationY="+fourChild.getTranslationY());
    }

    //判断receiverView是否滑动到顶端
    private boolean isCanScroll() {



           // int childScrollY = ;

            RecyclerView childView = (RecyclerView) fourChild.getChildAt(0);
            int offset = childView.computeVerticalScrollOffset();
           Log.i(context.getClass().getSimpleName(),"方法，offset="+offset);

        return true;

    }
}
