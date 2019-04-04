package com.example.liu.myappmao.net;

import java.util.List;

/**
 * Created by Administrator on 2018/12/23.
 */

public interface DataTransmission{


    void getData(getJson json);

//void onRestonse(setResponse response);

    interface getJson{

        void setData(List list);

    }

//    interface setResponse{
//
//        void setResponse();
//    }
}
