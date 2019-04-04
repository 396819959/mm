package com.example.liu.myappmao.customView;

import android.content.Context;
import android.graphics.Path;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/12/24.
 */

public class CustomLinearLayout extends LinearLayout implements CustomViewCallBack {

    private Context context;
    private int l;
    private int t;
    private int r;
    private int b;

    private int lWidth;
    private int rWidth;


    onPullDownRefresh call;
    private boolean isShow;
    private boolean isUp;
    //  ImageView image;
    private AnimationDrawable ad;
    private View v;
    WindowManager wm;
    private int downY = 0;
    private int downX = 0;
    Display disp;
    private final int TOPVALUE = 0;

    int valueY = 0;


    private ImageView image;
    private View rv;

    private int rvWidth;
    private int rvHeight;

    private int imWidth;
    private int imHeight;

    private int sWidth;
    private int sHeight;
    private int offY;

    int rvY;
    int imY;
    private int tvWidth;
    private int tvHeight;

    private int pullHeight;

    int h;
    private int tMove;

    int y = 0;
    ViewGroup vp;
    View relative;

    int ddy = 0;
    TextView tv;

    View imtop;

    int itHeight;
    int itWidth;

    int rtHeight;
    int rtWidth;

    int viewY;
    int rw;
    int rh;

    int imw;
    int imh;

    ViewGroup vg;
    View view;
    int iby;
    Path impath;
    Path rvpath;
    boolean a = false;
    int pullDest;
    DecelerateInterpolator decelerate;

    RecyclerView.LayoutManager lm;

    public CustomLinearLayout(@NonNull Context context) {
        this(context, null);
    }

    public CustomLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        decelerate = new DecelerateInterpolator();


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        //父类宽高
        sWidth = MeasureSpec.getSize(widthMeasureSpec);
        sHeight = MeasureSpec.getSize(heightMeasureSpec);

        //  Log.i(getClass().getSimpleName(), "onMeasure方法1 widthMeasureSpec=" + sWidth+";;heightMeasureSpec"+sHeight);

    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //  Log.i(getClass().getSimpleName(), "onLayout方法1 rvY=" + rvY);


        this.l = left;
        this.t = top;
        this.r = right;
        this.b = bottom;

        int count = getChildCount();
        if (changed) {
            // Log.i(getClass().getSimpleName(), "onLayout方法,改变了视图");
//            for (int i = 0; i < count; i++) {
//
//                getChildAt(i).forceLayout();
//
//
//            }

        }


        image = (ImageView) getChildAt(0);
        rv =  getChildAt(1);

        //  relative = getChildAt(2);
        //  imtop = getChildAt(3);

        // imtop = (ImageView) getChildAt(2);
        //   imtop.bringToFront();
        //  relative.bringToFront();


        // tv = (TextView) getChildAt(2);

        //  View v = getChildAt(2);

        // image.setBackgroundResource(R.drawable.pull_down_animation);
        ad = (AnimationDrawable) image.getBackground();

        imWidth = image.getMeasuredWidth();
        imHeight = image.getMeasuredHeight();

        //  rvWidth = rv.getMeasuredWidth();
        //  rvHeight = rv.getMeasuredHeight();

        //  Log.i(getClass().getSimpleName(), "onLayout方法iamge="+image.getTop()+"--y="+image.getY()+" --rv="+rv.getTop()+"--Y="+rv.getY());
        // Log.i(getClass().getSimpleName(), "onLayout方法imWidth="+imWidth+" --imHeight="+imHeight);
        rvWidth = rv.getMeasuredWidth();
        rvHeight = rv.getMeasuredHeight();

        rw = sWidth - (sWidth / 4);//imtop的左X轴位置
        rh = sHeight - (sHeight / 4);//imtop的上Y轴位置

        imw = rw + itWidth; //imtop的右X轴位置
        imh = rh + itHeight;//imtop的下Y轴位置

        int halfParentRight = right / 2;//父类宽度1/2
        int halfImageWidth = imWidth / 2;//imageView宽度1/2

        lWidth = halfParentRight - halfImageWidth;
        rWidth = halfParentRight + halfImageWidth;

        pullHeight = imHeight * 2;


        if (a == false) {
            // Log.i(getClass().getSimpleName(), "onLayout方法2");
            image.layout(lWidth, imY, rWidth, +imY);
            rv.layout(left, rvY, right, b);
            // Log.i(getClass().getSimpleName(), "onLayout方法rvHeight="+rvHeight+" --b="+b);

        } else {
            // Log.i(getClass().getSimpleName(), "onLayout方法2a=" + a);
            //image.layout(lWidth, TOPVALUE, rWidth, imHeight);
            //rv.layout(l, imHeight, r, bottom);

            //  a = false;
        }
        //  imtop.layout(rw, rh, imw, imh);
        //  relative.layout(rw, rh, imw, imh);

        // imtop.layout( w, h, w + itWidth, h + itHeight);
        //  int rt = sHeight + tvHeight;
        // v.layout(left, sHeight, right, rt);
        // Log.i(getClass().getSimpleName(), "onLayout()方法，topView宽高：" + itWidth + "::" + itHeight + "视图image宽高：" + imWidth + ":" + imHeight + "imY:" + imY + "recycler宽高" + rvWidth + ":" + rvHeight + "父类宽高：" + sWidth + ":" + sHeight + "位置：" + left + ":" + top + ":" + right + ":" + bottom);
        // Log.i(getClass().getSimpleName(), "构造方法,height=" + getTop() + ";;b=" +getMeasuredHeight()+";;b="+b+";;rvId="+rv.getId()+";;changed="+changed+"llID="+getId());

    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int event = ev.getAction();
        //   Log.i(getClass().getSimpleName(), "onInterceptTouchEvent方法，="+getY());

        if (!isEnabled()) {
            // Log.i(getClass().getSimpleName(), "onInterceptTouchEvent方法，不启用视图");
        }
        switch (event) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) ev.getY();//按下的Y值
                downX = (int) ev.getX();
                //    Log.i(getClass().getSimpleName(), "onInterceptTouchEvent方法，按下");

                break;
            case MotionEvent.ACTION_MOVE:


                int moveY = (int) ev.getY();
                int moveX = (int) ev.getX();

                int valueY = moveY - downY;
                int valueX = moveX - downX;

                int tY = downY - moveY;

                int y = Math.abs(valueY);//Y绝对值
                int x = Math.abs(valueX);

              //  int cY = rv.computeVerticalScrollOffset();
                int cY = rv.getTop();

                // Log.i(getClass().getSimpleName(), "onInterceptTouchEvent()方法,cy=" + cY + "::" + vg.getRotationY() + "::" + vg.getScaleY() + "::" + vg.getScrollY() + "::" + vg.getTranslationY() + "::" + vg.getPivotY() + "::" + vg.getY()+"::s="+s);
                // Log.i(getClass().getSimpleName(), "onInterceptTouchEvent()方法,cY="+cY);


                if (cY == TOPVALUE && downY < moveY && y > x) {
                    ad.start();
                    //  Log.i(getClass().getSimpleName(), "onInterceptTouchEvent()方法,cy==0");
                    return true;
                } else if (downY > moveY && tY <= imh) {
                    iby = tY;
                    // Log.i(getClass().getSimpleName(), "onInterceptTouchEvent()方法3箭头，滑动:tY"+tY+"::iby:"+iby);
                    // onTouchEvent(ev);
                    // imtop.layout(rw, rh, imw, iby);


                } else if (cY == TOPVALUE && x > y) {


                    return false;
                }
                break;
        }


        return super.onInterceptTouchEvent(ev);
    }


//    public void mOnTouchEvent(MotionEvent ev) {
//
//        switch (ev.getAction()) {
//
//            case MotionEvent.ACTION_DOWN:
//                Log.i(getClass().getSimpleName(), "mOnTouchEvent()方法1，按下");
//                downY = (int) ev.getY();//按下的Y值
//                downX = (int) ev.getX();
//
//                break;
//            case MotionEvent.ACTION_MOVE:
//
//                int moveY = (int) ev.getY();
//                int moveX = (int) ev.getX();
//
//               valueY = moveY - downY;
//                int valueX = moveX - downX;
//
//                int tY = downY - moveY;
//
//                int y = Math.abs(valueY);//Y绝对值
//                int x = Math.abs(valueX);
//
//                int cY = rv.computeVerticalScrollOffset();
//
//
//                if (cY == TOPVALUE && downY < moveY && y > x) {
//
//                    if (rvY >= 0) {
//
//                        if (a == true) {
//                            a = false;
//
//                            image.layout(lWidth, TOPVALUE + tMove, rWidth, imHeight + tMove);
//                            rv.layout(l, imHeight + tMove, r, b);
//
//                        } else {
//                            image.layout(lWidth, -imHeight + tMove, rWidth, TOPVALUE + tMove);
//                            rv.layout(l, tMove, r, b);
//                        }
//                    }
//
//
//                }
//
//                break;
//            case MotionEvent.ACTION_UP:
//
//                Log.i(getClass().getSimpleName(), "mOnTouchEvent()方法1，抬起");
//                if (offY == 0) {
//
//
//                    if (valueY >= imHeight && imY > 0) {
//                        a = true;
//                        call.setPullDownRefresh();
//                        image.layout(lWidth, TOPVALUE, rWidth, imHeight);
//                        rv.layout(l, imHeight, r, b);
//
//                    } else {
//
//
//                        image.layout(lWidth, -imHeight, rWidth, TOPVALUE);
//                        rv.layout(l, TOPVALUE, r, b);
//                    }
//                }
//                break;
//
//
//        }
//
//
//    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int ev = event.getAction();
        // Log.i(getClass().getSimpleName(), "onTouchEvent()方法,="+ev);
        //   Log.i(getClass().getSimpleName(), "onTouchEvent方法,"+getRotationY()+"::"+getScaleY()+"::"+getScrollY()+"::"+getTranslationY()+"::"+getPivotY()+";;"+getY());

        if (!isEnabled()) {
            //  Log.i(getClass().getSimpleName(), "onTouchEvent()方法，不启用视图");
            return isClickable() || isLongClickable();
        }
        switch (ev) {

            case MotionEvent.ACTION_DOWN:
//                downY = (int) event.getY();//按下的Y值
//                downX = (int) event.getX();
                //               Log.i(getClass().getSimpleName(), "onTouchEvent按下方法");


            case MotionEvent.ACTION_MOVE:

                int size = event.getHistorySize();//获取历史事件大小

                int moveY = (int) event.getY();//当前手指Y值
                int moveX = (int) event.getX();


                valueY = moveY - downY;
                int valueX = moveX - downX;

                y = Math.abs(valueY);//绝对值,滑动的距离
                int x = Math.abs(valueX);

              //  offY = rv.computeVerticalScrollOffset();
               offY = rv.getTop();
                tMove = TOPVALUE + valueY;


                // rvY = (int) rv.getY();
                rvY = rv.getTop(); //recyclerView顶部坐标
                imY = image.getTop();//image顶部坐标

                pullDest = (int) event.getY();

                //    Log.i(getClass().getSimpleName(), "onTouchEvent方法2,滑动offY="+offY);
                if (offY == 0) {


                    //  ad.start();
                    // Log.i(getClass().getSimpleName(), "方法2，rv="+rv.getTop());


                    // image.layout(lWidth, TOPVALUE + tMove, rWidth, imHeight + tMove);
                    //  rv.layout(l, imHeight + tMove, r, b);


                    if (a == true) {
                        a = false;

                        //  Log.i(getClass().getSimpleName(), "onTouchEvent方法2,滑动rvY=true");
                        image.layout(lWidth, TOPVALUE + tMove, rWidth, imHeight + tMove);
                        rv.layout(l, imHeight + tMove, r, b);

                    } else {
                        // int yy = (int) getY();
                        // Log.i("customLinearLayout类", "onTouchEvent下拉="+rv.getTop());

                        // Log.i(getClass().getSimpleName(), "onTouchEvent方法2,滑动rvY=false");

                        image.layout(lWidth, -imHeight + tMove / 3, rWidth, TOPVALUE + tMove / 3);
                        rv.layout(l, tMove / 3, r, b);
                    }


//                    if (a == true) {
//                        a = false;
//
//                        image.layout(lWidth, TOPVALUE + tMove, rWidth, imHeight + tMove);
//                        rv.layout(l, imHeight + tMove, r, b);
//
//                    } else {
//                        image.layout(lWidth, -imHeight + tMove, rWidth, TOPVALUE + tMove);
//                        rv.layout(l, tMove, r, b);
//                    }
                    return true;
                }


                // Log.i(getClass().getSimpleName(), "方法2，rvY:" + rvY + "iamgeY:" + imY + "-----" + rv.getScrollY() + "----" + rv.getPivotY() + "----" + rv.getRotationY() + "----" + rv.getScaleY() + "----" + rv.getTranslationY());


                //             Log.i(getClass().getSimpleName(), "方法2，recycler高度" + tMove + "y值：" + y + "pullDest:" + pullDest + "历史事件：" + size);

                //               }


                //  return true;


                //   }
                break;

            case MotionEvent.ACTION_UP:

                // Log.i(getClass().getSimpleName(), "onTouchEvent()方法1，抬起");
                if (rvY > 0) {

                    // int rvY = (int) rv.getY();
                    //  Log.i(getClass().getSimpleName(), "onTouchEvent()方法，抬起y值" + y + ":rv当前高度" + rvY);
                    //  Log.i(getClass().getSimpleName(), "onTouchEvent()方法1，抬起imHeight=" + imHeight);

                    if (valueY >= imHeight && imY > 0) {

                        a = true;
                        if (call != null) {
                            call.setPullDownRefresh();
                        }
                        // Log.i(getClass().getSimpleName(), "onTouchEvent()方法1，抬起");
                        image.layout(lWidth, TOPVALUE, rWidth, imHeight);
                        rv.layout(l, imHeight, r, b);

                    } else {
                        //    Log.i(getClass().getSimpleName(), "onTouchEvent()方法2，抬起imHeight=" + imHeight);


                        image.layout(lWidth, -imHeight, rWidth, TOPVALUE);
                        rv.layout(l, TOPVALUE, r, b);
                    }


                    // impath.addRect(lWidth, -imHeight, rWidth, TOPVALUE, Path.Direction.CCW);
                    // rvpath.addRect(l, TOPVALUE, r, b, Path.Direction.CCW);

                    // image.animate().setDuration(1000).setInterpolator(decelerate).translationY(-y).start();
                    //   rv.animate().setDuration(1000).setInterpolator(decelerate).translationY(-y).start();


                    // ObjectAnimator rvanim = ObjectAnimator.ofMultiFloat(rv, "translationY", rvpath);
                    //    anim.setDuration(1000);
                    //    rvanim.setDuration(1000);

                    //   Log.i(getClass().getSimpleName(), "onTouchEvent()方法，抬起，tMove值" + tMove + ":rv当前高度" + imHeight);


                    // anim.start();
                    //  rvanim.start();
                    //     image.setVisibility(View.GONE);
                    rvY = TOPVALUE;
                    imY = imHeight;
                    iby = imh;


                } else {
                    //  Log.i(getClass().getSimpleName(), "onTouchEvent()方法，imtop抬起");
                    // imtop.layout(rw, rh, imw, imh);
                }

                return false;

        }
        return false;
    }


    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_SCROLL:
                Log.i(getClass().getSimpleName(), "onGenericMotionEvent()方法，ACTION_SCROLL");
                break;
            case MotionEvent.ACTION_BUTTON_PRESS:
                Log.i(getClass().getSimpleName(), "onGenericMotionEvent()方法，ACTION_BUTTON_PRESS");

                break;
        }


        return super.onGenericMotionEvent(event);
    }

    @Override
    public void pullDownRefresh(onPullDownRefresh call) {
        this.call = call;
    }

    @Override
    public void setUpDataCompleted() {
        //   Log.i(getClass().getSimpleName(), "setUpDataCompleted()方法，刷新完成");
        a = false;
        //  image.layout(lWidth, -imHeight, rWidth, TOPVALUE);
        // rv.layout(l, TOPVALUE, r, b);
    }

    /**
     * 是否可以垂直滑动
     *
     * @return
     */
    public boolean canScrollVertically() {
        return false;
    }
}
