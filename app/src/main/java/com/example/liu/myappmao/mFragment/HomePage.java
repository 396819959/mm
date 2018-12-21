package com.example.liu.myappmao.mFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.liu.myappmao.MyDecoration;
import com.example.liu.myappmao.R;
import com.example.liu.myappmao.itemData.HomePageRvItemDate;
import com.example.liu.myappmao.mAdapter.HRecyclerViewAdapter;
import com.example.liu.myappmao.mAdapter.HomePageRecyclerViewAdapter;
import com.example.liu.myappmao.mAdapter.ViewPagerAdapter;
import com.example.liu.myappmao.net.JsonFactory;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("ValidFragment")
public class HomePage extends AbFragment {
    private ViewPagerAdapter vpAdapter;
    private RecyclerView recycler;
    private HomePageRecyclerViewAdapter adapter;
    private int[] bit = {R.drawable.no1, R.drawable.no2, R.drawable.no3, R.drawable.no4};
    private List<HomePageRvItemDate> itemDataList;
    private JsonFactory jsonf;

    private HomePageRvItemDate itemData;

    private HRecyclerViewAdapter hAdapter;

    private final String path = "http://api.dataoke.com/index.php?r=Port/index&type=total&appkey=0ff1670373&v=2&page=1";



    private ImageView[] iv;

    @SuppressLint("ValidFragment")
    public HomePage(Context context) {
        super(context);
    }


    @Override
    public View initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_home_page, null);
        recycler = view.findViewById(R.id.homePage_recyclerView_id);
        initData();
        return view;
    }


    @Override
    public void initData() {
     //   Log.i(getClass().getSimpleName(), "initData()方法1");
        itemData = new HomePageRvItemDate();


        vpAdapter = new ViewPagerAdapter(context, bit);


        itemDataList = new ArrayList<HomePageRvItemDate>();


        new JsonFactory<HomePageRvItemDate>(context, itemDataList).getJsonData(path);

        hAdapter = new HRecyclerViewAdapter(context, itemDataList);


        adapter = new HomePageRecyclerViewAdapter(context, itemDataList, vpAdapter,hAdapter);

        GridLayoutManager manager = new GridLayoutManager(context, 6, GridLayoutManager.VERTICAL, false);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 6;
                } else if (position == 1) {

                    return 6;
                }
                else if(position == 7){
                    return 6;
                }
                else if(position == 8){
                    return 6;
                }
                else if(position == 9){
                    return 6;
                }

                else {
                    return 3;
                }
            }
        });
        recycler.setLayoutManager(manager);
        recycler.addItemDecoration(new MyDecoration());
        recycler.setAdapter(adapter);


    }


}
