package com.example.liu.myappmao.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.liu.myappmao.R;
import com.example.liu.myappmao.customView.CustomTimeButton;

public class AdvertisingActivity extends Activity {
    private CustomTimeButton cButton;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dvertising_activity);
        cButton = findViewById(R.id.dvertising_custtimevutton_id);
       // cButton.bringToFront();

         intent = new Intent();
        intent.setClass(AdvertisingActivity.this, MainActivity.class);


        cButton.stopTiming(new CustomTimeButton.onStopTiming() {
            @Override
            public void stopT() {
              //  Log.i(getClass().getSimpleName(), "stopTiming方法1:" );


                startActivity(intent);
                finish();
            }
        });


        cButton.setProgressBar(3000);

      //  initData(5000);

    }

    private void initData(int time) {

        CountDownTimer count = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long paramLong) {
                Toast.makeText(AdvertisingActivity.this, "倒计时" + (int) paramLong / 1000, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent();
                intent.setClass(AdvertisingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}
