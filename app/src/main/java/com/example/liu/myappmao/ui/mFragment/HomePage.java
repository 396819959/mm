package com.example.liu.myappmao.ui.mFragment;

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
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liu.myappmao.MyDecoration;
import com.example.liu.myappmao.MyTimer;
import com.example.liu.myappmao.R;
import com.example.liu.myappmao.TimerCallBack;
import com.example.liu.myappmao.customView.CustomLinearLayout;
import com.example.liu.myappmao.customView.CustomStickView;
import com.example.liu.myappmao.customView.CustomViewCallBack;
import com.example.liu.myappmao.customView.CustomViewPage;
import com.example.liu.myappmao.itemData.HomePageRvItemDate;
import com.example.liu.myappmao.mAdapter.HRecyclerViewAdapter;
import com.example.liu.myappmao.mAdapter.HomePageRecyclerViewAdapter;
import com.example.liu.myappmao.mAdapter.HpViewPageAdapter;
import com.example.liu.myappmao.mAdapter.MyFragmentPageAdapter;
import com.example.liu.myappmao.mAdapter.ViewPagerAdapter;
import com.example.liu.myappmao.net.DataTransmission;
import com.example.liu.myappmao.net.HttpUtilCallBack;
import com.example.liu.myappmao.net.JsonFactory;
import com.example.liu.myappmao.net.MyHttpManager;
import com.example.liu.myappmao.ui.AbFragment;
import com.example.liu.myappmao.ui.ClassesFragmentActivity.ClassesBeautyFragment;
import com.example.liu.myappmao.ui.ClassesFragmentActivity.ClassesBoutiqueFragment;
import com.example.liu.myappmao.ui.ClassesFragmentActivity.ClassesCateFragment;
import com.example.liu.myappmao.ui.ClassesFragmentActivity.ClassesLivingFragment;
import com.example.liu.myappmao.ui.ClassesFragmentActivity.ClassesWomenFragment;
import com.example.liu.myappmao.ui.SearchActivity;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("ValidFragment")
public class HomePage extends AbFragment {

    private ConnectivityManager conn;
    private NetworkInfo network;
    private ViewPagerAdapter vpAdapter;
    private RecyclerView recycler;

    private LinearLayout ll;

    private MyTimer mTimer;
    private TextView searchTitle;

    private List<Fragment> list;
    private ClassesBoutiqueFragment boutiqueFragment;
    private ClassesBeautyFragment beautyFragment;
    private ClassesCateFragment cateFragment;
    private ClassesLivingFragment livingFragment;
    private ClassesWomenFragment womenFragment;


    private MyFragmentPageAdapter fragmentPageAdapter;


    private RadioButton nz, ms, mz, jjry, nanz, xp, smjd, my, xb, ny, wycp, ps, jzjf, hwyd;
    private RadioGroup classesRadioGroup;
    private ProgressBar dialog;
    private Intent intent;
    private GestureDetector gesture;
    private View view;
    private CustomStickView cimage;
    private CustomLinearLayout customLayout;
    private RelativeLayout rl;
    private CustomViewCallBack call;
    private CreateItemLayout creadteItem;
    private ImageButton history;
    private RadioButton jpButton;
    private MyHttpManager myHttpManager;
    private FragmentManager fragmentManager;
    private HomePageRecyclerViewAdapter adapter;
    private int[] bit = {R.drawable.t2, R.drawable.t3, R.drawable.t5, R.drawable.t4};
    private List<HomePageRvItemDate> itemDataList;
    private JsonFactory jsonf;
    private CustomViewPage vp;
    private int pages = 1;
    private HomePageRvItemDate itemData;
    // LinearRecyclerViewAdapter linearAdapter;
    private HRecyclerViewAdapter hAdapter;
    private HpViewPageAdapter hpAdapter;
    private boolean isStartTimer = false;

    int downY;
    int downX;

    private final String path1 = "http://api.dataoke.com/index.php?r=Port/index&type=total&appkey=0ff1670373&v=2&page=" + pages;

    // private final String path = "https://api.taokezhushou.com/api/v1/top_hour?app_key=60e0a00f450b367b&page=" + pages;


    private ImageView[] iv;

    @SuppressLint("ValidFragment")
    public HomePage(Context context) {
        super(context);
    }

    public HomePage() {

    }

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_home_page, null);
        ll = view.findViewById(R.id.homePager_customParentLayout_id);

        searchTitle = view.findViewById(R.id.homePage_search_title_id);
        vp = view.findViewById(R.id.homepage_ViewPage_id);
        dialog = view.findViewById(R.id.homePage_prodress_bar_id);
        jpButton = view.findViewById(R.id.horizontal_option_item_jp_id);
        classesRadioGroup = view.findViewById(R.id.horizontal_option_item_radioGroup_id);
        history = view.findViewById(R.id.homepage_history_id);
        jpButton.setChecked(true);
        jpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(0);
            }
        });
        initData();

        return view;
    }


    @Override
    public void initData() {


        boutiqueFragment = new ClassesBoutiqueFragment(context);
        beautyFragment = new ClassesBeautyFragment();
        cateFragment = new ClassesCateFragment();
        livingFragment = new ClassesLivingFragment();
        womenFragment = new ClassesWomenFragment();

        list = new ArrayList<Fragment>();
        list.add(boutiqueFragment);
        list.add(beautyFragment);
        list.add(cateFragment);
        list.add(livingFragment);
        list.add(womenFragment);

        classesRadioGroup.setOnCheckedChangeListener(new mOnChecked());
        fragmentPageAdapter = new MyFragmentPageAdapter(getFragmentManager(), list);


        //  myHttpManager = new MyHttpManager(context);
        //  myHttpManager.setRestonse(new mReaponse());
        //  jsonf = new JsonFactory<SearchPageItemDate>(context, list);

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(getClass().getSimpleName(), "点击历史记录");
                Toast.makeText(context, "点击历史记录", Toast.LENGTH_SHORT).show();
            }
        });

        searchTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, SearchActivity.class);
                startActivity(intent);

            }
        });

        networkState();

    }


    private void initObject() {
        creadteItem = new CreateItemLayout();
        resetTimer();

        itemData = new HomePageRvItemDate();

        itemDataList = new ArrayList<HomePageRvItemDate>();
        //  myHttpManager.setHttpGet(path1);
        jsonf = new JsonFactory<HomePageRvItemDate>(context, itemDataList, path1);
        gesture = new GestureDetector(context, new mGesture());
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
                // call.setUpDataCompleted();
                vpAdapter = new ViewPagerAdapter(context, bit, mTimer);
                hAdapter = new HRecyclerViewAdapter(context, itemDataList);
                //        adapter = new HomePageRecyclerViewAdapter(context, list, vpAdapter, hAdapter, creadteItem);
                //设置viewPage切换页面时长
                vpAdapter.setViewPagerScrollSpeed(creadteItem.vp, context, 2000);
                hAdapter.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
                recycler.setAdapter(adapter);
                setTimer();
                mTimer.startHandlerTimer(3000);
                dialog.setVisibility(View.GONE);
            }
        });


        //刷新视图
        call.pullDownRefresh(new CustomViewCallBack.onPullDownRefresh() {
                                 @Override
                                 public void setPullDownRefresh() {
                                     //   Log.i(getClass().getSimpleName(), "pullDownRefresh方法");

                                     networkState();


                                 }

                                 @Override
                                 public void upDataCompleted() {

                                 }


                             }

        );


    }

    private void setTimer() {
        mTimer.refreshView(new TimerCallBack.setRefreshView() {
            @Override
            public void onRefreshView() {
                int item = creadteItem.vp.getCurrentItem() + 1;

                //将获取到的视图设置到当前页面
                creadteItem.vp.setPageTransformer(true, new ViewPagerAdapter.mPageTransformer());

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


    //网络连接状态
    private void networkState() {
        dialog.setVisibility(View.VISIBLE);
        // network = NetworkInfo
        conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);


        network = conn.getActiveNetworkInfo();


        //：指示是否可以进行网络连接。当持久或半持久条件阻止连接到该网络时，该网络不可用。
        //       network.isAvailable();

        //指示是否存在网络连接，以及是否可以建立连接和传递数据。
        //  boolean b =  network.isConnected();

        //判断网络连接是否存在或正在建立中。这对于那些需要执行与网络相关的任何操作而不是读取或写入数据的应用程序非常有用。
        // 对于后者，则调用isConnected()，这可以保证
        // network.isConnectedOrConnecting();

        //指示当前连接到网络的尝试是否由于ConnectivityManager试图在断开与另一个网络的连接后故障转移到该网络。
        // network.isFailover();

        //指示设备当前是否在此网络上漫游。如果这是真的，就意味着在这个网络上使用数据可能会产生额外的成本。
        //  network.isRoaming();


        //网络不为空，有网络连接并且可以连接网络
        if (network != null && network.isAvailable() && network.isConnected()) {

            // Log.i(getClass().getSimpleName(), "networkState方法，有网络连接");
            // initObject();
            vp.setAdapter(fragmentPageAdapter);
            dialog.setVisibility(View.GONE);

        } else {

            View v = LayoutInflater.from(context).inflate(R.layout.network_off_item, null);


            //  View ll =  v.findViewById(R.id.network_off_ll_id);
            // WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

            //   ll.getLayoutParams();
            // ll.setGravity(Gravity.CENTER);

            ll.addView(v, 1);

//            Log.i(getClass().getSimpleName(), "宽高：" + rl.getWidth() + "::" + rl.getHeight());

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


    private class mOnChecked implements RadioGroup.OnCheckedChangeListener {


        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {


            switch (checkedId) {

                case R.id.horizontal_option_item_nz_id:
                    //   Log.i("homePage类","方法，女装");

                    setCurrentItemId(1);
                    break;
                case R.id.horizontal_option_item_ms_id:
                    setCurrentItemId(2);
                    break;
                case R.id.horizontal_option_item_mz_id:
                    setCurrentItemId(3);
                    break;
                case R.id.horizontal_option_item_jjry_id:
                    setCurrentItemId(4);
                    break;
                case R.id.horizontal_option_item_nanz_id:
                    setCurrentItemId(5);
                    break;
                case R.id.horizontal_option_item_xp_id:
                    setCurrentItemId(6);
                    break;
                case R.id.horizontal_option_item_smjd_id:
                    setCurrentItemId(7);
                    break;
                case R.id.horizontal_option_item_my_id:
                    setCurrentItemId(8);
                    break;
                case R.id.horizontal_option_item_xb_id:
                    setCurrentItemId(9);
                    break;
                case R.id.horizontal_option_item_ny_id:
                    setCurrentItemId(10);
                    break;
                case R.id.horizontal_option_item_wycp_id:
                    setCurrentItemId(11);
                    break;
                case R.id.horizontal_option_item_ps_id:
                    setCurrentItemId(12);
                    break;
                case R.id.horizontal_option_item_jzjf_id:
                    setCurrentItemId(13);
                    break;
                case R.id.horizontal_option_item_hwyd_id:
                  //  Log.i("homePage类", "方法，户外运动");
                    setCurrentItemId(14);
                    break;
            }

        }
    }

    private void setCurrentItemId(int itemId) {

        vp.setCurrentItem(itemId);
    }

    ;

    private class mGesture implements GestureDetector.OnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            Log.i("homePage", "onTouch方法,按下");

            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.i("homePage", "onTouch方法,按下2");

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    }

    private class mReaponse implements HttpUtilCallBack.onResponseCallBack {

        @Override
        public void getResponse(String json) {

            //  jsonf.setSearchJson(json);


//            call.setUpDataCompleted();
//            vpAdapter = new ViewPagerAdapter(context, bit, mTimer);
//            hAdapter = new HRecyclerViewAdapter(context, itemDataList);
//            adapter = new HomePageRecyclerViewAdapter(context, list, vpAdapter, hAdapter, creadteItem);
//            //设置viewPage切换页面时长
//            vpAdapter.setViewPagerScrollSpeed(creadteItem.vp, context, 2000);
//            hAdapter.notifyDataSetChanged();
//            adapter.notifyDataSetChanged();
//            recycler.setAdapter(adapter);
//            setTimer();
//            mTimer.startHandlerTimer(3000);
//            dialog.setVisibility(View.GONE);
        }
    }

}
