package com.example.liu.myappmao.ui.mFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.liu.myappmao.MyRadioGroup;
import com.example.liu.myappmao.R;
import com.example.liu.myappmao.customView.CustomPullLayout;
import com.example.liu.myappmao.customView.MyRadioButton;
import com.example.liu.myappmao.ui.AbFragment;

import java.util.List;

public class MiaoFragment extends AbFragment {

    RecyclerView rv;
    MyRadioButton button;
    MyRadioGroup rg;

    WebView wv;
    TextView tv;
    Intent intent;
    ViewPager vp;

    List<Fragment> list;
    CustomPullLayout pullLayout;

    @SuppressLint("ValidFragment")
    public MiaoFragment(Context context) {
        super(context);
    }

    public MiaoFragment() {
    }

    @Override
    public View initView(LayoutInflater inflater) {

        View view = LayoutInflater.from(context).inflate(R.layout.fragment_miao, null);
     //wv = view.findViewById(R.id.miao_webView_id);
        //   vp = view.findViewById(R.id.miao_viewpage_id);
//        wv.loadUrl("file:///android_asset/test.html");
//        WebSettings webSettings = wv.getSettings();
//
//        webSettings.setJavaScriptEnabled(true);
//        webSettings.setSupportZoom(false);
//        wv.setWebChromeClient(new mWebChromeClient());
//        Log.i("MiaoFragment类", "initView方法"+pullLayout.getMeasuredWidth()+"--"+pullLayout.getMeasuredHeight());

        //   initData();
        return view;
    }

    @Override
    public void initData() {
        ImageView iv = new ImageView(context);
        iv.setImageResource(R.drawable.wode_icon_update);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(100, 100);
        iv.setLayoutParams(lp);

        ImageView ivv = new ImageView(context);
        ivv.setImageResource(R.drawable.wode_icon_like);
        ivv.setLayoutParams(lp);

        // pullLayout.setBottomPullView(iv);
        // pullLayout.setTopPullView(ivv);
//        pullLayout.isUseDefaultPullView(true);


    }

    private class mWebChromeClient extends WebChromeClient {


    }

}



