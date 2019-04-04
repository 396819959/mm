package com.example.liu.myappmao.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liu.myappmao.MyTransformation;
import com.example.liu.myappmao.R;
import com.example.liu.myappmao.customView.CustomDetailsImageView;
import com.example.liu.myappmao.customView.MyRadioButton;
import com.example.liu.myappmao.itemData.HomePageRvItemDate;
import com.squareup.picasso.Picasso;

public class CommodityDetailsActivity extends Activity {
    MyRadioButton collect, normal;
    CustomDetailsImageView imageView;
    TextView price, hTitle, tmPrice, Introduce;
    ImageView returnButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commodity_details_layout);

        initView();
    }

    private void initView() {


        collect = findViewById(R.id.commodity_collect_id);
        normal = findViewById(R.id.commodity_normal_id);
        imageView = findViewById(R.id.commodity_imageView_id);
        hTitle = findViewById(R.id.commodity_title_id);
        price = findViewById(R.id.commodity_qhj_id);
        tmPrice = findViewById(R.id.commodity_tmj_id);
        Introduce = findViewById(R.id.commodity_introduce_id);
        returnButton = findViewById(R.id.commodity_details_return_id);
        collect.setOnClickListener(new mClick());
        normal.setOnClickListener(new mClick());
        returnButton.setOnClickListener(new mClick());
        initData();
    }


    private void initData() {

        HomePageRvItemDate hpr = (HomePageRvItemDate) getIntent().getSerializableExtra("list");

        if (hpr != null) {
            String imagePath = hpr.getPic();
            String title = hpr.getDtitle();
            float qhj = hpr.getPrice();
            float tmj = hpr.getOrgPrice();
            String introduce = hpr.getIntroduce();
            hTitle.setText(title);
            price.setText("" + qhj);
            tmPrice.setText("" + tmj);
            Introduce.setText(introduce);
            if (imagePath != null) {
                Picasso.with(this).load(imagePath).transform(new MyTransformation(this)).into(imageView);
            }
        }
    }

    public class mClick implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.commodity_collect_id:
                    Toast.makeText(CommodityDetailsActivity.this, "点击收藏", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.commodity_normal_id:
                    Toast.makeText(CommodityDetailsActivity.this, "正常价", Toast.LENGTH_SHORT).show();

                    break;

                case R.id.commodity_details_return_id:
                    // Toast.makeText(CommodityDetailsActivity.this, "返回", Toast.LENGTH_SHORT).show();

                    finish();
                    break;
            }
        }
    }
}
