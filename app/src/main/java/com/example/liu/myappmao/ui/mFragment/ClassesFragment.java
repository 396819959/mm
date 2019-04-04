package com.example.liu.myappmao.ui.mFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.liu.myappmao.ClassesRvDecoration;
import com.example.liu.myappmao.R;
import com.example.liu.myappmao.itemData.ClassesLayoutItemData;
import com.example.liu.myappmao.mAdapter.ClassesLaoutRvAdapter;
import com.example.liu.myappmao.ui.AbFragment;
import com.example.liu.myappmao.ui.SearchActivity;


public class ClassesFragment extends AbFragment implements View.OnClickListener {

    private TextView tv;
    private Intent intent;
    private RadioGroup rg;
    private RecyclerView rv;
    private ClassesLaoutRvAdapter adapter;

    @SuppressLint("ValidFragment")
    public ClassesFragment(Context context) {
        super(context);
    }

    public ClassesFragment() {
    }

    @Override
    public View initView(LayoutInflater inflater) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_resourse, null);
        tv = view.findViewById(R.id.classes_search_title_id);
        rg = view.findViewById(R.id.classes_layout_radiogroup_id);
        rv = view.findViewById(R.id.classes_layout_recyclerview_id);
        initData();
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        adapter = new ClassesLaoutRvAdapter(context);
        rv.setLayoutManager(new GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false));
        rv.addItemDecoration(new ClassesRvDecoration());
        tv.setOnClickListener(this);
        rg.setOnCheckedChangeListener(new mChecked());
        ((RadioButton)rg.findViewById(R.id.classes_layout_nz_id)).setChecked(true);
    }


    @Override
    public void onClick(View v) {
        intent = new Intent(context, SearchActivity.class);
        startActivity(intent);
    }

    private class mChecked implements RadioGroup.OnCheckedChangeListener {


        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {


                case R.id.classes_layout_nz_id:
                    adapter.setRefreshItem(ClassesLayoutItemData.ITEM_NZ);
                    rv.setAdapter(adapter);
                    break;
                case R.id.classes_layout_ms_id:
                    adapter.setRefreshItem(ClassesLayoutItemData.ITEM_MS);
                    rv.setAdapter(adapter);
                    break;
                case R.id.classes_layout_mz_id:
                    adapter.setRefreshItem(ClassesLayoutItemData.ITEM_MZ);
                    rv.setAdapter(adapter);
                    break;
                case R.id.classes_layout_jjry_id:
                    adapter.setRefreshItem(ClassesLayoutItemData.ITEM_JJRY);
                    rv.setAdapter(adapter);
                    break;
                case R.id.classes_layout_nanz_id:
                    adapter.setRefreshItem(ClassesLayoutItemData.ITEM_NANZ);
                    rv.setAdapter(adapter);
                    break;
                case R.id.classes_layout_xp_id:
                    adapter.setRefreshItem(ClassesLayoutItemData.ITEM_XP);
                    rv.setAdapter(adapter);
                    break;
                case R.id.classes_layout_smjd_id:
                    adapter.setRefreshItem(ClassesLayoutItemData.ITEM_SMJD);
                    rv.setAdapter(adapter);
                    break;
                case R.id.classes_layout_my_id:
                    adapter.setRefreshItem(ClassesLayoutItemData.ITEM_MY);
                    rv.setAdapter(adapter);
                    break;
                case R.id.classes_layout_xb_id:
                    adapter.setRefreshItem(ClassesLayoutItemData.ITEM_XB);
                    rv.setAdapter(adapter);
                    break;
                case R.id.classes_layout_wycp_id:
                    adapter.setRefreshItem(ClassesLayoutItemData.ITEM_WYCP);
                    rv.setAdapter(adapter);
                    break;
                case R.id.classes_layout_ps_id:
                    adapter.setRefreshItem(ClassesLayoutItemData.ITEM_PS);
                    rv.setAdapter(adapter);
                    break;
                case R.id.classes_layout_ny_id:
                    adapter.setRefreshItem(ClassesLayoutItemData.ITEM_NY);
                    rv.setAdapter(adapter);
                    break;
                case R.id.classes_layout_jzjf_id:
                    adapter.setRefreshItem(ClassesLayoutItemData.ITEM_JZJF);
                    rv.setAdapter(adapter);
                    break;
                case R.id.classes_layout_hwyd_id:
                    adapter.setRefreshItem(ClassesLayoutItemData.ITEM_HWYD);
                    rv.setAdapter(adapter);
                    break;


            }
        }
    }
}
