package com.example.liu.myappmao.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.liu.myappmao.DatabaseFactory;
import com.example.liu.myappmao.R;
import com.example.liu.myappmao.customView.CustomButtonView;
import com.example.liu.myappmao.itemData.SearchPageItemDate;
import com.example.liu.myappmao.mAdapter.SearchRecyclerAdapter;
import com.example.liu.myappmao.net.HttpUtilCallBack;
import com.example.liu.myappmao.net.JsonFactory;
import com.example.liu.myappmao.net.MyHttpManager;

import java.util.ArrayList;
import java.util.List;

public class SearchContentPage extends Activity implements View.OnClickListener {
    private EditText searchEdit;
    private Button rq, zx, xl, sx;

    private boolean isSelect;
    private Button button;
    private CustomButtonView jg;
    private ImageView returnButton;
    private MyHttpManager httpManager;
    private SearchRecyclerAdapter sAdapter;
    private RecyclerView rv;
    //   private String path;
    private List<SearchPageItemDate> list;
    private SearchPageItemDate sItem;
    private Context context = SearchContentPage.this;
    private JsonFactory jf;
    private ProgressBar progressBar;
    String str;
   DatabaseFactory database;
    private boolean isSearch = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_content_page);
        searchEdit = findViewById(R.id.searchContentPage_searchBar_id);
        button = findViewById(R.id.searchContentPage_button_id);
        returnButton = findViewById(R.id.searchContentPage_return_id);
        rv = findViewById(R.id.searchContentPage_recycler_id);
        progressBar = findViewById(R.id.search_content_page_bar_id);

        rq = findViewById(R.id.search_content_page_rq);
        zx = findViewById(R.id.search_content_page_zx);
        xl = findViewById(R.id.search_content_page_xl);
        jg = findViewById(R.id.search_content_page_jg);
        sx = findViewById(R.id.search_content_page_sx);

        initData();

    }

    private void initData() {

        str = getIntent().getStringExtra("content");

        searchEdit.setText(str);

        httpManager = new MyHttpManager(context);
        httpManager.setRestonse(new mReaponse());
        rv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        loadNetwork(str, 0, "one_day_sale_num");
        list = new ArrayList<SearchPageItemDate>();
        jf = new JsonFactory<SearchPageItemDate>(context, list);

        rq.setTextColor(Color.parseColor("#F08080"));
        database = new DatabaseFactory(SearchContentPage.this);

        returnButton.setOnClickListener(this);
        button.setOnClickListener(this);
        searchEdit.setOnClickListener(this);

        rq.setOnClickListener(this);
        zx.setOnClickListener(this);
        xl.setOnClickListener(this);
        jg.setOnClickListener(this);
        sx.setOnClickListener(this);
    }


    private void loadNetwork(String searchContent, int page, String sort) {
        progressBar.setVisibility(View.VISIBLE);
        String path = null;
        if (sort == null) {
            path = "https://api.taokezhushou.com/api/v1/search?app_key=60e0a00f450b367b&q=" + searchContent + "&page=" + page;

        } else {
            path = "https://api.taokezhushou.com/api/v1/search?app_key=60e0a00f450b367b&q=" + searchContent + "&sort=" + sort + "&page=" + page;

        }
        httpManager.setHttpGet(path);

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {


            case R.id.searchContentPage_button_id:

                str = searchEdit.getText().toString().trim();
                database .insertData(str);
                //  Log.i("SearchContentPage", "方法，点击搜索=" + path+"str=" + str);
                isSearch = true;
                loadNetwork(str, 0, null);

                break;
            case R.id.searchContentPage_return_id:
                finish();
                break;

            case R.id.search_content_page_rq://人气

                setSelectStat(rq);
                loadNetwork(str, 0, "one_day_sale_num");
                break;
            case R.id.search_content_page_zx://最新

                setSelectStat(zx);
                loadNetwork(str, 0, "new");
                break;
            case R.id.search_content_page_xl://销量

                setSelectStat(xl);
                loadNetwork(str, 0, "sale_num");
                break;
            case R.id.search_content_page_jg://价格

                //  Log.i("SearchContentPage", "方法，点击价格，isSelect=" + isSelect);

                setSelectStat(jg);
                setSelectDrawable(isSelect);

                break;
            case R.id.search_content_page_sx://筛选

                setSelectStat(sx);

                break;

        }
    }

    private void setSelectStat(TextView v) {

        rq.setTextColor(Color.parseColor("#80000000"));
        zx.setTextColor(Color.parseColor("#80000000"));
        xl.setTextColor(Color.parseColor("#80000000"));
        jg.setTextColor(Color.parseColor("#80000000"));
        sx.setTextColor(Color.parseColor("#80000000"));

        Drawable drawable = getResources().getDrawable(R.drawable.icon_order_tab_normal);
        jg.setSelectDrawable(drawable);

        v.setTextColor(Color.parseColor("#F08080"));

    }

    private void setSelectDrawable(boolean isSelect) {

        if (isSelect == false) {

            Drawable drawable = getResources().getDrawable(R.drawable.icon_order_tab_up);
            jg.setSelectDrawable(drawable);
            loadNetwork(str, 0, "price_desc");
            this.isSelect = true;
            //   Log.i("SearchContentPage", "方法，点击升序");

        } else {


            Drawable drawable = getResources().getDrawable(R.drawable.icon_order_tab_down);
            jg.setSelectDrawable(drawable);
            loadNetwork(str, 0, "price_asc");
            this.isSelect = false;
            //    Log.i("SearchContentPage", "方法，点击降序");

        }


    }



    private class mReaponse implements HttpUtilCallBack.onResponseCallBack {


        @Override
        public void getResponse(String str) {


            list = jf.setSearchJson(str);
            sAdapter = new SearchRecyclerAdapter(context, list, isSearch);


            rv.setAdapter(sAdapter);
            isSearch = false;
            progressBar.setVisibility(View.GONE);
        }
    }
}
