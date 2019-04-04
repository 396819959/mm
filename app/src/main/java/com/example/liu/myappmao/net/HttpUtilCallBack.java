package com.example.liu.myappmao.net;

public interface HttpUtilCallBack {

    void setRestonse(onResponseCallBack rcb) ;

    interface onResponseCallBack{

       void  getResponse(String str);
    }
}
