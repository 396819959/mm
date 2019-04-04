package com.example.liu.myappmao.customView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;

@SuppressLint("AppCompatCustomView")
public class CustomButtonView extends Button {
    Drawable[] drawable;

    public CustomButtonView(Context context) {
        this(context, null);
    }

    public CustomButtonView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context, attrs);
    }

    private void initData(Context context,AttributeSet attrs) {

        //                Log.i("CustomButtonView", "iniData()方法,i=" + i + "drawable为空");


        drawable = getCompoundDrawables();

        Drawable d = drawable[2];

        if(d != null) {

            d.setBounds(0, 0, 40, 40);
            setCompoundDrawables(null, null, d, null);

        }



    }

    public void setSelectDrawable(Drawable d) {


        d.setBounds(0, 0, 40, 40);
        setCompoundDrawables(null, null, d, null);

    }


}
