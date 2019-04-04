package com.example.liu.myappmao.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;

import com.example.liu.myappmao.R;
import com.example.liu.myappmao.customView.MyRadioButton;
import com.example.liu.myappmao.ui.mFragment.ClassesFragment;
import com.example.liu.myappmao.ui.mFragment.HomePage;
import com.example.liu.myappmao.ui.mFragment.MiaoFragment;
import com.example.liu.myappmao.ui.mFragment.My;

public class MainActivity extends AppCompatActivity {
    private Fragment F;
    private RadioGroup radioGroup;
    private FragmentManager manager;
    private String tags;
    private MyRadioButton my;
    private MyRadioButton homePage, classes;
    private MyRadioButton miao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }


    private void initData() {

        radioGroup = findViewById(R.id.radioGroup_id);
        homePage = findViewById(R.id.homepage_id);
        classes = findViewById(R.id.resourse_id);
        miao = findViewById(R.id.miao_id);
        my = findViewById(R.id.my_id);

        homePage.setChecked(true);

        manager = getSupportFragmentManager();

        setFragmentPage("homepage");
        // radioGroup.setOnCheckedChangeListener(new mChecked());

        homePage.setOnClickListener(new mClick());
        classes.setOnClickListener(new mClick());
        miao.setOnClickListener(new mClick());
        my.setOnClickListener(new mClick());


        //  ((RadioButton) radioGroup.findViewById(R.id.homepage_id)).setChecked(true);

    }


    private class mClick implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            // Log.i("MainActivity类", "onCheckedChanged方法获取新页面，数量="+ v.getId());

            switch (v.getId()) {

                case R.id.homepage_id:
                    setFragmentPage("homepage");

                    break;
                case R.id.resourse_id:
                    setFragmentPage("resourse");

                    break;

                case R.id.miao_id:
                    //     Log.i(getClass().getSimpleName(), "获取新页面resourse");

                    setFragmentPage("miao");
                    break;
                case R.id.my_id:

                    setFragmentPage("my");
                    break;

            }
        }
    }

    //private void


    private class mChecked implements RadioGroup.OnCheckedChangeListener {


        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

            switch (checkedId) {

                case R.id.homepage_id:
                    setFragmentPage("homepage");

                    break;
                case R.id.resourse_id:
                    setFragmentPage("resourse");

                    break;

                case R.id.miao_id:

                    setFragmentPage("miao");
                    break;
                case R.id.my_id:

                    setFragmentPage("my");
                    break;

            }
        }
    }

    private void setFragmentPage(String tag) {

        if (F != null) {

            manager.beginTransaction().hide(F).commit();

        }

        F = manager.findFragmentByTag(tag);
        if (F == null) {
            switch (tag) {

                case "homepage":
                    F = new HomePage(this);
                    break;
                case "resourse":
                    F = new ClassesFragment(this);
                    break;

                case "miao":
                    F = new MiaoFragment(this);
                    break;
                case "my":
                    F = new My(this);
                    break;

            }
            manager.beginTransaction().add(R.id.bottomBar_fragme_id, F, tag).commit();

        } else {

            manager.beginTransaction().show(F).commit();
        }

        //  Log.i(getClass().getSimpleName(), "切换页面tag =" + tag);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
