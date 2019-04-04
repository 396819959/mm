package com.example.liu.myappmao.net;

import android.content.Context;
import android.os.Message;
import android.util.Log;

import com.example.liu.myappmao.itemData.HomePageRvItemDate;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Connection;
import okhttp3.Handshake;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/**
 * Created by Administrator on 2018/12/20.
 */

public class MyHttpManager implements HttpUtilCallBack {
    private Context context;
    private Request request;
    private String mstr;
    private JSONObject jsonOb;
    private JSONArray jsonArray;
    private HomePageRvItemDate itemDate;
    private OkHttpClient client;
    private OkHttpClient builder;
    private onResponseCallBack rcb;
    private mHandler handler = new mHandler();

    public MyHttpManager(Context context) {
        this.context = context;
        initData();

    }


    private void initData() {

        client = new OkHttpClient();
        builder = new OkHttpClient.Builder()

                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(new mInterceptor())
                .sslSocketFactory(createSSLSocketFactory())
                //证书验证器
                .hostnameVerifier(new mHostnameVerifier())
                .build();

    }


    public void setHttpGet(String path) {
        // Log.i("MyHttpManager", "setHttpGet方法");

        request = new Request.Builder().get().url(path).build();
        setRequest(request);

        //   Log.i("MyHttpManager", "setHttpGet方法=" + );


    }

    public void setHttpGet(URL url) {

        request = new Request.Builder().get().url(url).build();
        setRequest(request);


    }


    private void setRequest(Request request) {
        final Response[] responsee = {null};

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    Message msg = Message.obtain();
                    msg.obj = response;
                    handler.sendMessage(msg);


                }
            }
        });


    }

    /**
     * 信任所有证书
     *
     * @return
     */
    //ssl套接字工厂
    private static SSLSocketFactory createSSLSocketFactory() {

        SSLSocketFactory sslSocketFactory = null;

        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("TLS");
            //secureRandom:随机数生成器
            sc.init(null, new TrustManager[]{new mX509()}, new SecureRandom());
            sslSocketFactory = sc.getSocketFactory();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        return sslSocketFactory;
    }

    @Override
    public void setRestonse(onResponseCallBack rcb) {
        this.rcb = rcb;
    }


    private static class mX509 implements X509TrustManager {


        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            Log.i("MyHttpManager", "checkClientTrusted方法=" + authType);

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            Log.i("MyHttpManager", "checkServerTrusted方法=" + authType);

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    /**
     * 判断返回结果是否与请求相同，true:相同。false:不相同
     */
    private class mHostnameVerifier implements HostnameVerifier {


        @Override
        public boolean verify(String hostname, SSLSession session) {


            Log.i("MyHttpManager", "方法，hostname=" + hostname);
            return true;
        }
    }


    //拦截器
    private class mInterceptor implements Interceptor {


        @Override
        public Response intercept(Chain chain) throws IOException {

            String str = chain.request().toString();
            Connection conn = chain.connection();
            Response response = chain.proceed(chain.request());
            Socket socket = conn.socket();//返回套接字
            Route route = conn.route();//路由、线路
            Handshake handshake = conn.handshake();//返回用于建立此连接的TLS握手，如果连接不是，则返回null

            Log.i("MyHttpManager", "拦截器，方法 response= " + response.body().string() + "str=" + str + "::socket=" + socket + "::route=" + route + "::handshake=" + handshake);
            return response;
        }
    }


    private class mHandler extends android.os.Handler {


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg != null) {

                try {

                    Response response = (Response) msg.obj;
                   String str = response.body().string();
                 //  Log.i(getClass().getSimpleName(),"handleMessage方法str="+str);
                    rcb.getResponse(str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
