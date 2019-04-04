package com.example.liu.myappmao.ui.ClassesFragmentActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.liu.myappmao.MyDecoration;
import com.example.liu.myappmao.MyTimer;
import com.example.liu.myappmao.R;
import com.example.liu.myappmao.TimerCallBack;
import com.example.liu.myappmao.customView.CustomLinearLayout;
import com.example.liu.myappmao.customView.CustomStickView;
import com.example.liu.myappmao.customView.CustomViewCallBack;
import com.example.liu.myappmao.itemData.HomePageRvItemDate;
import com.example.liu.myappmao.mAdapter.HRecyclerViewAdapter;
import com.example.liu.myappmao.mAdapter.HomePageRecyclerViewAdapter;
import com.example.liu.myappmao.mAdapter.MyFragmentPageAdapter;
import com.example.liu.myappmao.mAdapter.ViewPagerAdapter;
import com.example.liu.myappmao.net.DataTransmission;
import com.example.liu.myappmao.net.JsonFactory;
import com.example.liu.myappmao.ui.AbFragment;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class ClassesBoutiqueFragment extends AbFragment {

    ImageView iv;
    RecyclerView rv;


    private ConnectivityManager conn;
    private NetworkInfo network;
    private ViewPagerAdapter vpAdapter;
    private RecyclerView recycler;

    LinearLayout ll;

    private MyTimer mTimer;
    TextView searchTitle;

    List<Fragment> list;
    ClassesBoutiqueFragment boutiqueFragment;
    ClassesBeautyFragment beautyFragment;
    ClassesCateFragment cateFragment;
    ClassesLivingFragment livingFragment;
    ClassesWomenFragment womenFragment;


    MyFragmentPageAdapter fragmentPageAdapter;

    ViewPager homePageViewPage;
    // private ViewPager vp;
    // private RecyclerView hrv;

    RadioGroup radioGroup;
    ProgressBar dialog;
    private Intent intent;
    private GestureDetector gesture;
    private View view;
    private CustomStickView cimage;
    private CustomLinearLayout customLayout;
    private RelativeLayout rl;
    private CustomViewCallBack call;
    private ClassesBoutiqueFragment.CreateItemLayout creadteItem;

    private FragmentManager fragmentManager;
    private HomePageRecyclerViewAdapter adapter;
    private int[] bit = {R.drawable.t2, R.drawable.t3, R.drawable.t5, R.drawable.t4};
    private List<HomePageRvItemDate> itemDataList;
    private JsonFactory jsonf;
    private Context cContext;
    private int pages = 1;
    private HomePageRvItemDate itemData;
    // LinearRecyclerViewAdapter linearAdapter;
    private HRecyclerViewAdapter hAdapter;

    private boolean isStartTimer = false;

    int downY;
    int downX;
    int wWidth;
    int wHeight;
    private final String path = "http://api.dataoke.com/index.php?r=Port/index&type=total&appkey=0ff1670373&v=2&page=" + pages;

    @SuppressLint("ValidFragment")
    public ClassesBoutiqueFragment(Context context) {
        super(context);

    }

    @Override
    public View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.classes_boutique_fragment, null);

        //    iv = view.findViewById(R.id.classes_boutique_fragment_image_id);
        recycler = view.findViewById(R.id.classes_boutique_fragment_recyclerView_id);
        //  customLayout = view.findViewById(R.id.classes_boutique_custom_linearlayout_id);
        initData();
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        initObject();
    }


    @SuppressLint("LongLogTag")
    private void initObject() {
        //  Log.i("ClassesBoutiqueFragment类", "方法，context=" + context);

        creadteItem = new CreateItemLayout();
        resetTimer();
        itemData = new HomePageRvItemDate();

        itemDataList = new ArrayList<HomePageRvItemDate>();

        jsonf = new JsonFactory<HomePageRvItemDate>(context, itemDataList, path);
        callBack();
        GridLayoutManager manager = new GridLayoutManager(context, 6, GridLayoutManager.VERTICAL, false);


        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                if (position > 1 && position < 6) {
                    return 3;
                } else if (position > 8 && position < 13) {
                    return 3;
                } else {
                    return 6;
                }


            }
        });
        //  Log.i(getClass().getSimpleName(), "initData()方法1");
        recycler.addItemDecoration(new MyDecoration());

        recycler.setLayoutManager(manager);


    }


    private void callBack() {

        jsonf.getData(new DataTransmission.getJson() {

            @Override
            public void setData(List list) {
                //   Log.i(getClass().getSimpleName(), "jsonCallBack（）方法");
                //   customLayout.setUpDataCompleted();
                // customLayout.requestLayout();
                vpAdapter = new ViewPagerAdapter(context, bit, mTimer);
                hAdapter = new HRecyclerViewAdapter(context, itemDataList);
                adapter = new HomePageRecyclerViewAdapter(context, list, vpAdapter, hAdapter, creadteItem);
                //设置viewPage切换页面时长
                vpAdapter.setViewPagerScrollSpeed(creadteItem.vp, context, 2000);
                hAdapter.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
                recycler.setAdapter(adapter);
                setTimer();
                mTimer.startHandlerTimer(3000);
                // dialog.setVisibility(View.GONE);
            }
        });

//        //刷新视图
//        customLayout.pullDownRefresh(new CustomViewCallBack.onPullDownRefresh() {
//                                         @Override
//                                         public void setPullDownRefresh() {
//                                             //   Log.i(getClass().getSimpleName(), "pullDownRefresh方法");
//
//                                             initObject();
//
//
//                                         }
//
//                                         @Override
//                                         public void upDataCompleted() {
//
//                                         }
//
//
//                                     }
//
//        );


    }

    private void setTimer() {
        mTimer.refreshView(new TimerCallBack.setRefreshView() {
            @Override
            public void onRefreshView() {
                int item = creadteItem.vp.getCurrentItem() + 1;

                //将获取到的视图设置到当前页面
                creadteItem.vp.setPageTransformer(true, new ViewPagerAdapter.mPageTransformer());

                //viewPage中有时会出现空指针情况，问题出现在源码setCurrentItem方法中，等待解决
                creadteItem.vp.setCurrentItem(item);
            }
        });

    }

    //重置计时器
    private void resetTimer() {

        if (mTimer != null && isStartTimer == true) {
            //  Log.i(getClass().getSimpleName(), "resetTimer方法1");
            mTimer.cancelTimer();
            mTimer = null;

        }
        if (mTimer == null) {
            //  Log.i(getClass().getSimpleName(), "resetTimer方法2");
            mTimer = new MyTimer();
        }
    }


    /**
     * 初始化recyclerView布局
     */
    public class CreateItemLayout {
        public View viewPagerView;
        public View radioGroupView;
        public View RecyclerItemView;
        public View titleView;
        public View h_recyclerView;
        public View two_titleView;
        public View three_titleView;
        public View h_recyclerItemView;


        public RadioGroup rg;
        public ViewPager vp;
        public RecyclerView hRv;


        public CreateItemLayout() {


            initLayout();
            getLayoutItem();
        }

        //设置要添加到recyclerview中的layout

        private void initLayout() {


            viewPagerView = LayoutInflater.from(context).inflate(R.layout.homepage_viewpage, null);

            radioGroupView = LayoutInflater.from(context).inflate(R.layout.homepage_radiogroup_item, null);

            RecyclerItemView = LayoutInflater.from(context).inflate(R.layout.homepage_rv_item, null);

            titleView = (LayoutInflater.from(context).inflate(R.layout.bt_two_item, null));

            h_recyclerView = LayoutInflater.from(context).inflate(R.layout.h_recyclerview_item, null);

            two_titleView = LayoutInflater.from(context).inflate(R.layout.bttp_rv_item, null);

            three_titleView = LayoutInflater.from(context).inflate(R.layout.lqbt_item, null);

            h_recyclerItemView = LayoutInflater.from(context).inflate(R.layout.linear_view_item, null);


        }


        private void getLayoutItem() {
            vp = viewPagerView.findViewById(R.id.homepage_vp_id);

            rg = radioGroupView.findViewById(R.id.homepage_rg_id);

            hRv = h_recyclerView.findViewById(R.id.h_rv_item_id);


        }


    }

}
