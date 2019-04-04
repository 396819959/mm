package com.example.liu.myappmao.customView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.example.liu.myappmao.R;

import java.util.List;


@SuppressLint("AppCompatCustomView")
public class MyRadioButton extends RadioButton {

    private int mWidth;
    private Drawable[] drawables = new Drawable[4];

    private int mHeight;
    private Context context;
    private AttributeSet attrs;

    List<Drawable> list;

    private OnCheckedChangeListener mOnCheckedChangeWidgetListener;


    public MyRadioButton(Context context) {
        this(context, null);
    }

    public MyRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.attrs = attrs;
        initData(context, attrs);


    }


    //在自定义控件中使用自定义属性时，经常需要使用java代码获取在xml中定义的尺寸，相关有以下三个函数
    //getDimension()
    //getDimensionPixelOffset()
    //getDimensionPixelSize()
    //（在类TypedArray和类Resources中都有这三个函数，功能类似，TypedArray中的函数是获取自定义属性的，Resources中的函数是获取android预置属性的）

    //getDimension()是基于当前DisplayMetrics进行转换，获取指定资源id对应的尺寸。文档里并没说这里返回的就是像素，要注意这个函数的返回值是float，像素肯定是int。
    //getDimensionPixelSize()与getDimension()功能类似，不同的是将结果转换为int，并且小数部分四舍五入。
    //getDimensionPixelOffset()与getDimension()功能类似，不同的是将结果转换为int，并且偏移转换（offset conversion，函数命名中的offset是这个意思）是直接截断小数位，即取整（其实就是把float强制转化为int，注意不是四舍五入哦）。
    //由此可见，这三个函数返回的都是绝对尺寸，而不是相对尺寸（dp\sp等）。如果getDimension()返回结果是20.5f，那么getDimensionPixelSize()返回结果就是21，getDimensionPixelOffset()返回结果就是20
    private void initData(Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyRadioButton);


        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);


            switch (attr) {
                case R.styleable.MyRadioButton_leftDrawable:
                    drawables[0] = a.getDrawable(attr);
                    break;
                case R.styleable.MyRadioButton_topDrawable:
                    drawables[1] = a.getDrawable(attr);
                    break;

                case R.styleable.MyRadioButton_rightDrawable:
                    drawables[2] = a.getDrawable(attr);
                    break;
                case R.styleable.MyRadioButton_belowDrawable:
                    drawables[3] = a.getDrawable(attr);
                    break;
                case R.styleable.MyRadioButton_topWidth:
                    mWidth = (int) a.getDimension(R.styleable.MyRadioButton_topWidth, 0);
                    break;
                case R.styleable.MyRadioButton_topHeight:
                    mHeight = (int) a.getDimension(R.styleable.MyRadioButton_topHeight, 0);
                    break;
            }

        }
        for (Drawable d : drawables) {
            if (d != null) {
                d.setBounds(0, 0, mWidth, mHeight);
            }
        }

        setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
        a.recycle();

    }


    private void setMDrawable() {

        for (int i = 0; i < drawables.length; i++) {
            Drawable drawable = drawables[i];
            if (drawable != null) {

                drawable.setBounds(0, 0, mWidth, mHeight);
                switch (i) {

                    case 0:

                        setCompoundDrawables(drawable, null, null, null);

                        break;
                    case 1:

                        setCompoundDrawables(null, drawable, null, null);

                        break;
                    case 2:

                        setCompoundDrawables(null, null, drawable, null);

                        break;
                    case 3:

                        setCompoundDrawables(null, null, null, drawable);

                        break;
                }
            }


        }

    }
}
