package com.example.liu.myappmao.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CustomTimeButton extends ViewGroup {

    private Thread thread;
    int mWidth;
    int mHeight;
    private TextView textView;
    private Paint paint;
    private Path path;
    int progress = 0;
    private int oneViewWidth;
    private int oneViewHeight;
    private mHandler handler;
    private onStopTiming timing;


    public CustomTimeButton(Context context) {

        this(context, null);
    }

    public CustomTimeButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTimeButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        handler = new mHandler();
        textView = new TextView(context);
        textView.setText("5");
        textView.setBackgroundColor(Color.parseColor("#00000000"));
        textView.setTextColor(Color.parseColor("#96FFFFFF"));
        textView.setTextSize(26);
        textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));


        path = new Path();
        paint = new Paint();
        thread = new Thread();
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(Color.parseColor("#95CDC8B1"));
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(12);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawArc(8, 8, mWidth - 8, mHeight - 8, 0, progress, true, paint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        int count = getChildCount();


        View view = getChildAt(0);

        if (getChildAt(0) == null) {
            //Log.i(getClass().getSimpleName(), "方法2:为空");
            addView(textView, 0);
        }

        for (int i = 0; i < count; i++) {

            LayoutParams lp = view.getLayoutParams();


            lp.width = mWidth / 2;
            lp.height = mHeight / 2;
            view.setLayoutParams(lp);
            measureChild(view, mWidth, mHeight);
            oneViewWidth = view.getMeasuredWidth();
            oneViewHeight = view.getMeasuredHeight();
            view.measure(oneViewWidth, oneViewHeight);

        }
        //  Log.i(getClass().getSimpleName(), "onMeasu()方法子类数量" + count + "宽高：" + mWidth + "::" + mHeight + "text宽高：" + oneViewWidth + "::" + oneViewHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();

        View v = getChildAt(0);
        int w = v.getMeasuredWidth();
        int h = v.getMeasuredHeight();
        // LayoutParams lp = v.getLayoutParams();

        int pw = r - l;//父类宽度
        int ph = b - t;//父类高度

        int par = pw / 2;
        int car = w / 2;

        int pat = ph / 2;
        int cat = h / 2;


        int cLeft = par - car;
        int cRight = par + car;

        int cTop = pat - cat;
        int cBottom = pat + cat;


        //   Log.i(getClass().getSimpleName(), "方法2：" + l + "::" + t + "::" + r + "::" + b + "视图尺寸：" + w + "::" + h);
        //  Log.i(getClass().getSimpleName(), "方法2:" + mleft + "::" + mtop + "::" + mRight + "::" + bottom);


        v.layout(cLeft, cTop, cRight, cBottom);
        // Log.i(getClass().getSimpleName(), "onLayout()方法2,子类宽高：" + w + "::" + h + "位置左：" + mleft + "上：" + mtop + "右" + mRight + "下：" + bottom);


    }


    /**
     * 设定动画时长
     *
     * @param time 动画时长（毫秒数）,最少为1mm
     */
    public void setProgressBar(int time) {
        final int textData;
        final int t = time / 360;


        // Log.i(getClass().getSimpleName(), "方法2位置" + textView.getMeasuredHeight() + "::" + textView.getMeasuredWidth());
        //     Log.i(getClass().getSimpleName(),"方法2位置"+v.getMeasuredHeight()+"::"+v.getMeasuredWidth());


        if (time < 1000) {
            textData = 1000;
        } else {
            textData = time / 1000;
        }


        textView.setText("" + textData);


        new Thread() {

            @Override
            public void run() {
                new Thread() {
                    @Override
                    public void run() {
                        setText(textData);
                    }
                }.start();

                while (progress <= 360) {
                    try {

                        Thread.sleep(t);
                        postInvalidate();
                        progress++;


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
                // Log.i(getClass().getSimpleName(), "setProgressBar方法1:");
                //  timing.stopT();

            }
        }.start();


    }

    private void setText(final int times) {
        int i = 0;
        int t = times;


        while (i <= times) {
            try {

                if (i == times) {
                    if (timing == null) {

                    } else {
                        timing.stopT();
                    }
                }

                handler.sendEmptyMessage(t);
                t--;
                i++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
//        if (timing != null) {
//            timing.stopT();
//        }
        // Log.i(getClass().getSimpleName(), "setText方法1:");


    }

    public void stopTiming(onStopTiming timing) {
        this.timing = timing;

    }



    private class mHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int t = msg.what;
            textView.setText("" + t);
        }
    }


    public interface onStopTiming {

        void stopT();
    }
}
