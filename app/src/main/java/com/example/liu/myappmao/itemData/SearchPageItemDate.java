package com.example.liu.myappmao.itemData;

import java.io.Serializable;

public class SearchPageItemDate implements Serializable {


    String goods_id; //商品ID
    String goods_pic;//商品主图
    String goods_long_pic; //商品推广长图
    String goods_title;//商品标题
    String goods_short_title;//商品短标题
    String goods_intro;//商品文案
    int goods_cate_id;//商品分类
    float goods_price;//商品原价
    int goods_sale_num;//商品销量
    float commission_rate;//佣金
    String seller_id;//卖家ID
    String coupon_id;//券ID
    float coupon_apply_amount;//优惠券满
    float coupon_amount;//优惠券减
    String coupon_start_time;//券开始时间
    String coupon_end_time;//券结束时间
    int is_tmall;//是否天猫
    int juhuasuan;//是否聚划算
    int taoqianggou;//是否淘抢购
    int yunfeixian;//是否运费险
    int jinpai;//是否金牌
    int jiyoujia;//是否极有家
    int haitao;//是否海淘
    float dsr;//DSR描述评分

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public void setGoods_pic(String goods_pic) {
        this.goods_pic = goods_pic;
    }

    public void setGoods_long_pic(String goods_long_pic) {
        this.goods_long_pic = goods_long_pic;
    }

    public void setGoods_title(String goods_title) {
        this.goods_title = goods_title;
    }

    public void setGoods_short_title(String goods_short_title) {
        this.goods_short_title = goods_short_title;
    }

    public void setGoods_intro(String goods_intro) {
        this.goods_intro = goods_intro;
    }

    public void setGoods_cate_id(int goods_cate_id) {
        this.goods_cate_id = goods_cate_id;
    }

    public void setGoods_price(float goods_price) {
        this.goods_price = goods_price;
    }

    public void setGoods_sale_num(int goods_sale_num) {
        this.goods_sale_num = goods_sale_num;
    }

    public void setCommission_rate(float commission_rate) {
        this.commission_rate = commission_rate;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public void setCoupon_id(String coupon_id) {
        this.coupon_id = coupon_id;
    }

    public void setCoupon_apply_amount(float coupon_apply_amount) {
        this.coupon_apply_amount = coupon_apply_amount;
    }

    public void setCoupon_amount(float coupon_amount) {
        this.coupon_amount = coupon_amount;
    }

    public void setCoupon_start_time(String coupon_start_time) {
        this.coupon_start_time = coupon_start_time;
    }

    public void setCoupon_end_time(String coupon_end_time) {
        this.coupon_end_time = coupon_end_time;
    }

    public void setIs_tmall(int is_tmall) {
        this.is_tmall = is_tmall;
    }

    public void setJuhuasuan(int juhuasuan) {
        this.juhuasuan = juhuasuan;
    }

    public void setTaoqianggou(int taoqianggou) {
        this.taoqianggou = taoqianggou;
    }

    public void setYunfeixian(int yunfeixian) {
        this.yunfeixian = yunfeixian;
    }

    public void setJinpai(int jinpai) {
        this.jinpai = jinpai;
    }

    public void setJiyoujia(int jiyoujia) {
        this.jiyoujia = jiyoujia;
    }

    public void setHaitao(int haitao) {
        this.haitao = haitao;
    }

    public void setDsr(float dsr) {
        this.dsr = dsr;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public String getGoods_pic() {
        return goods_pic;
    }

    public String getGoods_long_pic() {
        return goods_long_pic;
    }

    public String getGoods_title() {
        return goods_title;
    }

    public String getGoods_short_title() {
        return goods_short_title;
    }

    public String getGoods_intro() {
        return goods_intro;
    }

    public int getGoods_cate_id() {
        return goods_cate_id;
    }

    public float getGoods_price() {
        return goods_price;
    }

    public int getGoods_sale_num() {
        return goods_sale_num;
    }

    public float getCommission_rate() {
        return commission_rate;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public String getCoupon_id() {
        return coupon_id;
    }

    public float getCoupon_apply_amount() {
        return coupon_apply_amount;
    }

    public float getCoupon_amount() {
        return coupon_amount;
    }

    public String getCoupon_start_time() {
        return coupon_start_time;
    }

    public String getCoupon_end_time() {
        return coupon_end_time;
    }

    public int getIs_tmall() {
        return is_tmall;
    }

    public int getJuhuasuan() {
        return juhuasuan;
    }

    public int getTaoqianggou() {
        return taoqianggou;
    }

    public int getYunfeixian() {
        return yunfeixian;
    }

    public int getJinpai() {
        return jinpai;
    }

    public int getJiyoujia() {
        return jiyoujia;
    }

    public int getHaitao() {
        return haitao;
    }

    public float getDsr() {
        return dsr;
    }
}
