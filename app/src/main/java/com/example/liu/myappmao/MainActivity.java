package com.example.liu.myappmao;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.liu.myappmao.mFragment.HomePage;
import com.example.liu.myappmao.mFragment.My;
import com.example.liu.myappmao.mFragment.Resourse;

public class MainActivity extends AppCompatActivity {
    private Fragment F;
    private RadioGroup radioGroup;
    private FragmentManager manager;
    private String tags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initData();
    }


    private void initData() {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup_id);

        manager = getSupportFragmentManager();
        radioGroup.setOnCheckedChangeListener(new mChecked());
        ((RadioButton) radioGroup.findViewById(R.id.homepage_id)).setChecked(true);



        Log.i(getClass().getSimpleName(), "initData（）方法");
    }


    private class mChecked implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {


            switch (checkedId) {

                case R.id.homepage_id:
                    Log.i(getClass().getSimpleName(), "获取新页面homepage");
                    setFragmentPage("homepage");

                    break;
                case R.id.resourse_id:
                    Log.i(getClass().getSimpleName(), "获取新页面resourse");
                    setFragmentPage("resourse");

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
        Log.i(getClass().getSimpleName(), "获取新页面");
        if (F == null) {
            switch (tag) {

                case "homepage":
                    F = new HomePage(this);
                    break;
                case "resourse":
                    F = new Resourse(this);
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
        Log.i(getClass().getSimpleName(),"退出页面");
        super.onDestroy();
    }
}
