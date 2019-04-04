package com.example.liu.myappmao.mAdapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.Toast;

import com.example.liu.myappmao.MyTimer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class ViewPagerAdapter extends PagerAdapter {


    int downY;
    int downX;

    int moveY;
    int moveX;
    private Context context;

    private MyTimer mt;
    private List<ImageView> iv;
    private ImageView image;

    private mScroller scroller;
    // DecelerateInterpolator decelerate = new DecelerateInterpolator();

    public ViewPagerAdapter(Context context, int[] bit, final MyTimer mt) {

        this.mt = mt;
        //   Log.i(getClass().getSimpleName(), "bit长度" + bit.length);
        iv = new ArrayList<ImageView>();
        this.context = context;
        // setViewPagerScrollSpeed(ViewPager.);
        for (int i = 0; i < bit.length; i++) {
            image = new ImageView(context);
            image.setImageResource(bit[i]);
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.add(image);
        }


    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        view.getY();
        //  Log.i(getClass().getSimpleName(), "isViewFromObject方法");
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);

        // Log.i(getClass().getSimpleName(), "destroyItem方法");

        View v = iv.get(position % iv.size());


//        v.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        Log.i("adapter", "viewPage方法,按下");
//                        downY = (int) v.getScaleY();
//                        downX = (int) v.getX();
//                        mt.cancelHandlerTimer();
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//
//
//                        moveY = (int) v.getY();
//                        moveX = (int) v.getX();
//
//                        int valueY = moveY - downY;
//                        int valueX = moveX - downX;
//
//
//                        int y = Math.abs(valueY);//Y绝对值
//                        int x = Math.abs(valueX);
//
//                        Log.i("adapter", "viewPage方法,移动downY="+downY+":moveY="+moveY);
//                        if (y > x) {
//                            Log.i("adapter", "viewPage方法,移动2");
//                            mt.startHandlerTimer(5000);
//                        }
//
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        Log.i("adapter", "viewPage方法,抬起");
//
//                        mt.startHandlerTimer(5000);
//
//                        break;
//
//                }
//
//
//                return false;
//            }
//        });

        container.removeView(v);


    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int d = position % iv.size();
        //  Log.i(getClass().getSimpleName(), "instantiateItem方法");

        View v = iv.get(d);

        v.setOnClickListener(new mClick());

        ViewGroup vg = (ViewGroup) v.getParent();


        if (vg != null) {
            vg.removeAllViews();
        }

        //g int width = container.getMeasuredWidth();
        //  v.animate().translationX(-width).setDuration(1000).setInterpolator(decelerate).start();
//         an = ObjectAnimator.ofFloat(v, "translationX", -width);
  //     an.setInterpolator(decelerate);
//        an.setDuration(1000);
        container.addView(v);
//        an.start();

        return iv.get(d);
    }

    public class mClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Toast.makeText(context, "viewPage点击了：" + v.getId(), Toast.LENGTH_SHORT).show();
            //  Log.i(getClass().getSimpleName(), "viewPager:点击：");

        }
    }

    //视图切换动画
    public static class mPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        @Override
        public void transformPage(View page, float position) {

            // Log.i(getClass().getSimpleName(), "transformPage方法、position" + position);
            int pageWidth = page.getWidth();


            if (position <= -1) {
            //    Log.i(getClass().getSimpleName(), "transformPage方法1、position" + position);

             page.setAlpha(0);

            } else if (position <= 0) {
             //   Log.i(getClass().getSimpleName(), "transformPage方法2、position" + position);

        //   page.animate().translationX(-pageWidth).setDuration(1000).setInterpolator(new AccelerateInterpolator()).start();

                  page.setAlpha(1);
                  page.setTranslationX(0);
                 page.setScaleX(1);
                  page.setScaleY(1);

            } else if (position <= 1) {
             //   Log.i(getClass().getSimpleName(), "transformPage方法3、position" + position);

         //  page.animate().translationX(-pageWidth).setDuration(1000).setInterpolator(new AccelerateInterpolator()).start();


            //     Fade the page out.
                  page.setAlpha(1 - position);

                // Counteract the default slide transition
                  page.setTranslationX(pageWidth * -position);

              //   Scale the page down (between MIN_SCALE and 1)
                  float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
                  page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);

            } else {

               page.setAlpha(0);
            }

        }


    }


    public void setViewPagerScrollSpeed(Object object, Context context, int duration) {


        Field field = null;
        try {
            field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            scroller = new mScroller(context, new AccelerateInterpolator());
            field.set(object, scroller);
            scroller.setmDuration(duration);


        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }
//
//    public void setDuration( Context context,int duration){
//        scroller = new mScroller(context, new AccelerateInterpolator());
//        field.set(context, scroller);
//        scroller.setmDuration(duration);
//
//    }


    //切换速度
    private class mScroller extends Scroller {

        private int duration;

        public mScroller(Context context) {
            super(context);
        }

        public mScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        public mScroller(Context context, Interpolator interpolator, boolean flywheel) {
            super(context, interpolator, flywheel);
        }


        public void setmDuration(int mDuration) {
            duration = mDuration;
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy, duration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, duration);
        }
    }


}
