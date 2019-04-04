package com.example.liu.myappmao.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.OverScroller;

import java.util.List;

public class CustomHorizontalOptionBar extends ViewGroup {
    String TAG = "CustomHorizontalOptionBar类";
    GestureDetector gestureDetector;

    private boolean mSmoothScrollingEnabled = true;

    private int mTouchSlop;
    private int mMinimumVelocity;
    private int mMaximumVelocity;

    private int mOverscrollDistance;
    private int mOverflingDistance;

    private float mVerticalScrollFactor;


    List<View> list;
    int rWidth;

    int width;
    int height;

    int dis;
    int distance;
    private int downY;
    private int downX;

    private int leftX;
    private int rightX;
    private int mActivePointerId = INVALID_POINTER;
    private static final int INVALID_POINTER = -1;
    private boolean mIsBeingDragged = false;
    private int mLastMotionY;
    VelocityTracker velocityTracker;
    ViewGroup view;
    OverScroller overScroller;
    int lw;
    int rw;
    int vWidth = 0;

    boolean isScroll;
    int sWidth = 0;
    int lScrollX = 0;
    int rScrollX = 0;
    int viewX = 0;
    int rX;
    int lX;
    int count;
    int l, t, r, b;
    private int mNestedYOffset;
    public CustomHorizontalOptionBar(Context context) {
        this(context, null);
    }

    public CustomHorizontalOptionBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomHorizontalOptionBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        velocityTracker = VelocityTracker.obtain();
        overScroller = new OverScroller(context);
        initViewData(context);
    }

    private void initViewData(Context context) {
        ViewConfiguration configuration = ViewConfiguration.get(context);

        mTouchSlop = configuration.getScaledTouchSlop();
        mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
        mMaximumVelocity = configuration.getScaledMaximumFlingVelocity(); //最大速度
        mOverscrollDistance = configuration.getScaledOverscrollDistance();//滚动距离
        mOverflingDistance = configuration.getScaledOverflingDistance();//抛动距离


        gestureDetector = new GestureDetector(context, new mGestureDetector());
        //  Log.i(TAG, "onScroll方法1 vWidth=" + vWidth+"--width="+width);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//
//        int sWidth;
//        int sHeight;
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//
//        int count = getChildCount();
//
//
//        if (widthMode == MeasureSpec.EXACTLY) {
//            sWidth = widthSize;
//        } else if (heightMode == MeasureSpec.EXACTLY) {
//            sHeight = heightSize;
//
//        }
//
//
//        View view = getChildAt(0);
//        int width = view.getMeasuredWidth();
//        int height = view.getMeasuredHeight();
//
//        view.measure(width, height);
//
//
//    }


            //  View v = getChildAt(1);
            int vCount = view.getChildCount();  @Override
            protected void onLayout(boolean changed, int l, int t, int r, int b) {


                if (isScroll == false) {

                    this.l = l;
                    this.t = t;
                    this.r = r;
                    this.b = b;

                    count = getChildCount();
                    view = (ViewGroup) getChildAt(0);
            for (int i = 0; i < vCount; i++) {

                View v = view.getChildAt(i);
                int width = v.getMeasuredWidth();
                int height = v.getMeasuredHeight();
                vWidth += width;
                //  Log.i(TAG, "onScroll方法1 vWidth=" + vWidth+"--width="+width);

            }


            rScrollX = viewX;
            lScrollX = viewX;


            width = view.getMeasuredWidth();
            height = view.getMeasuredHeight();
            rWidth = width + r;
            view.layout(0, 0, width, height);
        } else {
            viewLayout();

        }
        //   Log.i(TAG, "onScroll方法2 width="+view.getRight());
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.i(TAG, "onTouchEvent方法getScrollX()=" + getScrollX());
        switch (event.getAction()) {


            case MotionEvent.ACTION_MOVE:


                viewX = (int) view.getX();

                int moveY = (int) event.getY();
                int moveX = (int) event.getX();


                int y = moveY - downY;
                int x = moveX - downX;
                int valueY = Math.abs(y);
                int valueX = Math.abs(x);

                if (valueY < valueX) {

                    if (rightX > moveX) {

                        rightX = moveX;
                        leftX = rightX;
                        rX = moveX;
                        rScrollX = viewX;
                        leftScroll(moveX);

                    } else if (leftX < moveX) {

                        leftX = moveX;
                        rightX = leftX;
                        lX = moveX;
                        lScrollX = viewX;
                        rightScroll(moveX);
                    }
                }

                break;

            case MotionEvent.ACTION_UP:


                lScrollX = viewX;
                rScrollX = viewX;
                //velocityTracker.addMovement(event);
//                velocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
//                int initialVelocity = (int) velocityTracker.getXVelocity();
//
//              //  overScroller.startScroll(lw,0,200,0);
//
//
//
//               overScroller.fling(0, 0, 0, 40, 0, r, 0, 0, 0, height / 2);
//               postInvalidate();
//                //postInvalidateOnAnimation();
//                //Log.i(TAG, "onTouchEvent方法initialVelocity=" + initialVelocity);
//                velocityTracker.clear();
//                velocityTracker = null;
                break;
        }


        return false;
    }


    private class mGestureDetector implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {

            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            distance = (int) distanceX;
            dis = Math.abs(distance);
            viewX = (int) view.getX();

            int moveX = (int) e2.getX();
            Log.i(TAG, "onScroll方法e1=" + e1 + "--e2=" + e2);
            if (rightX > moveX) {

                rightX = moveX;
                leftX = rightX;
                rX = moveX;
                rScrollX = viewX;
                leftScroll(moveX);

            } else if (leftX < moveX) {

                leftX = moveX;
                rightX = leftX;
                lX = moveX;
                lScrollX = viewX;
                rightScroll(moveX);
            }


            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    }


    private void leftScroll(int moveY) {
        int lScroll = lX - moveY;
        lw = lScrollX - lScroll;
        rw = rWidth - lScroll;

        if (viewX <= -rWidth) {

            lw = -rWidth;
        }

        viewLayout();
        // Log.i("CustomParentLayout类", "onInterceptTouchEvent方法viewX=" + viewX);

    }

    private void rightScroll(int moveY) {
        int bScroll = moveY - rX;
        lw = rScrollX + bScroll;
        rw = rWidth + bScroll;
        if (viewX >= 0) {

            lw = 0;
        }
        viewLayout();

    }

    private void viewLayout() {


        view.layout(lw, t, width, b);

    }

}
