//package com.example.liu.myappmao.net;
//
//import android.content.Context;
//import android.widget.Toast;
//
//import com.example.liu.myappmao.itemData.HomePageRvItemDate;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.util.List;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//import static android.R.id.list;
//
///**
// * Created by Administrator on 2018/12/20.
// */
//
//public class MyHttpManager {
//    private Context context;
//   private Request request;
//   private String mstr;
//    private JSONObject jsonOb;
//    private JSONArray jsonArray;
//    private HomePageRvItemDate itemDate;
//
//    MyHttpManager(Builder builder) {
//
//       mstr = builder.str;
//    }
//
//
//    public static class Builder {
//        Context context;
//        OkHttpClient Okclient;
//        OkHttpClient.Builder okBuilder;
//        Request request;
//        String str;
//        List list;
//
//        public Builder(Context context,List list) {
//            this.context = context;
//            Okclient = new OkHttpClient();
//            okBuilder = new OkHttpClient.Builder();
//            this.list = list;
//        }
//
//        public Builder setGet(String url) {
//                if(url == null)throw new NullPointerException("url == null");
//
//            request = new Request.Builder().url(url).get().build();
//            Okclient.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    Toast.makeText(context, "获取连接失败...", Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    str = response.body().string();
//                    getResult(str);
//                }
//            });
//            return this;
//        }
//
//
//
//
//
//
//        public MyHttpManager build() {
//            //  if (url == null) throw new IllegalStateException("url为空");
//            return new MyHttpManager(this);
//        }
//    }
//
//
//
//
//}
