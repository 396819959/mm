package com.example.liu.myappmao.customView;

public interface CustomViewCallBack {

        void pullDownRefresh(onPullDownRefresh call);


        void setUpDataCompleted();


         interface onPullDownRefresh{

            void setPullDownRefresh();

            void upDataCompleted();
        }




}
