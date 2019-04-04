package com.example.liu.myappmao.net;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.liu.myappmao.itemData.HomePageRvItemDate;
import com.example.liu.myappmao.itemData.SearchPageItemDate;

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

public class JsonFactory<T> implements DataTransmission {

    private Context context;
    // private MyHttpManager manager;
    private JSONObject jsonOb;
    private JSONArray jsonArray;
    private HomePageRvItemDate itemDate;
    private SearchPageItemDate sItemData;
    private List<T> list;
    private OkHttpClient Okclient;
    private Request request;
    private OkHttpClient.Builder okBuilder;
    private mHandler handler = new mHandler();


    private getJson json;

    public JsonFactory(Context context, List<T> list, String url) {
        this.context = context;
        this.list = list;
        Okclient = new OkHttpClient();
        okBuilder = new OkHttpClient.Builder();

        getJsonData(url);
    }

    public JsonFactory(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }

    public JsonFactory() {

    }


    public void setPath(String url) {
        getJsonData(url);

    }


    private void getJsonData(String url) {
        request = new Request.Builder().url(url).get().build();
        Okclient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(getClass().getSimpleName(), "获取连接失败...");
                //  Toast.makeText(context, "获取连接失败...", Toast.LENGTH_SHORT).show();
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


            //
            Message mess = Message.obtain();
            mess.obj = list;
            // Log.i(getClass().getSimpleName(), "getResult()方法，list数据：" + list.size());
            handler.sendMessage(mess);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public List setSearchJson(String str) {

        if(list.size() != 0){
            list.clear();
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(str);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                sItemData = new SearchPageItemDate();
                sItemData.setGoods_id(json.getString("goods_id"));
                sItemData.setGoods_pic(json.getString("goods_pic"));
                sItemData.setGoods_long_pic(json.getString("goods_long_pic"));
                sItemData.setGoods_title(json.getString("goods_title"));
                sItemData.setGoods_short_title(json.getString("goods_short_title"));
                sItemData.setGoods_intro(json.getString("goods_intro"));
                sItemData.setGoods_cate_id(json.getInt("goods_cate_id"));
                sItemData.setGoods_price((float) json.getDouble("goods_price"));
                sItemData.setGoods_sale_num(json.getInt("goods_sale_num"));
                sItemData.setCommission_rate((float) json.getDouble("commission_rate"));
                sItemData.setSeller_id(json.getString("seller_id"));
                sItemData.setCoupon_id(json.getString("coupon_id"));
                sItemData.setCoupon_apply_amount((float) json.getDouble("coupon_apply_amount"));
                sItemData.setCoupon_amount((float) json.getDouble("coupon_amount"));
                sItemData.setCoupon_start_time(json.getString("coupon_start_time"));
                sItemData.setCoupon_end_time(json.getString("coupon_end_time"));
                sItemData.setIs_tmall(json.getInt("is_tmall"));
                sItemData.setJuhuasuan(json.getInt("juhuasuan"));
                sItemData.setTaoqianggou(json.getInt("taoqianggou"));
                sItemData.setYunfeixian(json.getInt("yunfeixian"));
                sItemData.setJinpai(json.getInt("jinpai"));
                sItemData.setJiyoujia(json.getInt("jiyoujia"));
                sItemData.setHaitao(json.getInt("haitao"));
                 sItemData.setDsr((float) json.getDouble("dsr"));
             // Log.i(getClass().getSimpleName(), "setSearchJson()方法setGoods_title=" + sItemData.getGoods_title());

                list.add((T) sItemData);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
     //   Log.i(getClass().getSimpleName(), "setSearchJson()方法,list数据：" + list.size());

        return list;
    }

    @Override
    public void getData(getJson json) {
        this.json = json;

    }


    private class mHandler extends Handler {

        // private WeakReference<Context> weak;
        public mHandler() {


        }


        @Override
        public void handleMessage(Message msg) {


            super.handleMessage(msg);
            List<HomePageRvItemDate> list = (List<HomePageRvItemDate>) msg.obj;
           //  Log.i(getClass().getSimpleName(), "handleMessage()方法,list数据：" + list.size());
            json.setData(list);

        }
    }


}


