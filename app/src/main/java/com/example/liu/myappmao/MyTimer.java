package com.example.liu.myappmao;

import android.os.Handler;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;


public class MyTimer implements TimerCallBack {

    private TimerTask tTask;
    private Timer timer;
    private mHandler handler;
    private setRefreshView ref;
    private int mTime;
    private static final int TIMER_WHAT = 1;
    private static final int HANDLER_WHAT = 0;

    public MyTimer() {

        timer = new Timer();
        handler = new mHandler();
    }


    /**
     * handler计时器
     *
     * @param time 睡眠时间（毫秒）
     */
    public void startHandlerTimer(int time) {
        mTime = time;
     //  Log.i("MyTimer类。", "startHandlerTimer方法");
        handler.sendEmptyMessageDelayed(HANDLER_WHAT, time);
    }


    /**
     * TimerTask计时器
     *
     * @param delay  首次加载延时时长（毫秒）
     * @param period 延时周期，间隔多长时间执行一次（毫秒）
     */
    public boolean startTimer(int delay, int period) {

        tTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(TIMER_WHAT);


            }
        };

        timer.schedule(tTask, delay, period);


        return true;
    }


    /**
     * TimerTask取消计时器
     */
    public void cancelTimer() {
        timer.cancel();

    }

    /**
     * handler计时器取消
     */
    public void cancelHandlerTimer() {
    //  Log.i("MyTimer类。", "cancelHandlerTimer方法");

        handler.removeMessages(HANDLER_WHAT);
    }


    private class mHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {

                case TIMER_WHAT:

                    ref.onRefreshView();
                    break;
                case HANDLER_WHAT:
                   // Log.i("MyTimer类。", "handleMessage---HANDLER_WHAT方法1");

                    ref.onRefreshView();
                   // Log.i("MyTimer类。", "handleMessage---HANDLER_WHAT方法2");
                    handler.removeMessages(HANDLER_WHAT);
                    handler.sendEmptyMessageDelayed(HANDLER_WHAT, mTime);

                    break;
            }


        }
    }


    @Override
    public void refreshView(setRefreshView ref) {
        this.ref = ref;
    }


}
