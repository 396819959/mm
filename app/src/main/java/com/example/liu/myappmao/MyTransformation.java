package com.example.liu.myappmao;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.WindowManager;

import com.squareup.picasso.Transformation;

public class MyTransformation implements Transformation {
    private Canvas canvas;
    private Paint paint;
    private WindowManager wm;
    private Context context;
    private int width;
    private final int VALUE = 2;
    RectF rect;

    public MyTransformation(Context context) {
        this.context = context;
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();


        paint = new Paint();
        canvas = new Canvas();
        rect = new RectF(0, 0, width, width);

    }

    @Override
    public Bitmap transform(Bitmap source) {


        int width = source.getWidth();
        int height = source.getHeight();

        int w = Math.max(width, height);
        Matrix mat = new Matrix();
        mat.postScale(1.5f, 1.5f);

        Bitmap map = source.copy(Bitmap.Config.ARGB_8888, true);
        map.setWidth(width);
        map.setHeight(width);
        Bitmap output = Bitmap.createBitmap(map, 0, 0, w, w, mat, true);


        //  Log.i(getClass().getSimpleName(), "transform()方法：图片宽=：" + width + "--高=" + height);
        map.recycle();
        source.recycle();
        return output;


    }

    @Override
    public String key() {
        return "1";
    }
}
