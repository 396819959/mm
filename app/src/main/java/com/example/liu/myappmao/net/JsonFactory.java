package com.example.liu.myappmao.net;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.liu.myappmao.itemData.HomePageRvItemDate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/12/20.
 */

public class JsonFactory<T> {

    private Context context;
    // private MyHttpManager manager;
    private JSONObject jsonOb;
    private JSONArray jsonArray;
    private HomePageRvItemDate itemDate;
    private List<T> list;
    private OkHttpClient Okclient;
    private Request request;
    private OkHttpClient.Builder okBuilder;


    public JsonFactory(Context context, List<T> list) {
        this.context = context;
        this.list = list;
        Okclient = new OkHttpClient();
        okBuilder = new OkHttpClient.Builder();
    }

    public void getJsonData(String url) {
        request = new Request.Builder().url(url).get().build();
        Okclient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(context, "获取连接失败...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(getClass().getSimpleName(), "response()数据");
                getResult(response.body().string());
            }
        });
    }

    public void getResult(String jsonData) {
        try {
            jsonOb = new JSONObject(jsonData);
            jsonArray = jsonOb.getJSONArray("result");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject str = jsonArray.getJSONObject(i);
                itemDate = new HomePageRvItemDate();
                itemDate.setDtitle(str.getString("D_title"));
                itemDate.setTitle(str.getString("Title"));
                itemDate.setDsr(str.getInt("Dsr"));
                itemDate.setCommissjonQueqiao(str.getLong("Commission_queqiao"));
                itemDate.setQuanReceive(str.getInt("Quan_receive"));
                itemDate.setQuanPrice(str.getInt("Quan_price"));
                itemDate.setQuanTime(str.getString("Quan_time"));
                itemDate.setJihuaLink(str.getString("Jihua_link"));
                itemDate.setPrice(str.getLong("Price"));
                itemDate.setJihuaShenhe(str.getInt("Jihua_shenhe"));
                itemDate.setIntroduce(str.getString("Introduce"));
                itemDate.setCid(str.getInt("Cid"));
                itemDate.setSalesNum(str.getInt("Sales_num"));
                itemDate.setIsTmall(str.getInt("IsTmall"));
                itemDate.setQuanLink(str.getString("Quan_link"));
                itemDate.setGoodsID(str.getString("GoodsID"));
                itemDate.setCommissjonJihua(str.getLong("Commission_jihua"));
                itemDate.setID(str.getInt("ID"));
                itemDate.setPic(str.getString("Pic"));
                itemDate.setOrgPrice(str.getLong("Org_Price"));
                itemDate.setQuanMLink(str.getString("Quan_m_link"));
                itemDate.setQuanId(str.getString("Quan_id"));
                itemDate.setQuanCondition(str.getString("Quan_condition"));
                itemDate.setQuanSurplus(str.getInt("Quan_surplus"));
                itemDate.setSellerID(str.getInt("SellerID"));
                list.add((T) itemDate);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}


