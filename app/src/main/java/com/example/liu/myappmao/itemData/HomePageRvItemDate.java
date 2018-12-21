package com.example.liu.myappmao.itemData;

public class HomePageRvItemDate {
	
	
	private int ID;// 商品id
	private String GoodsID;// 商品淘宝id
	private String Title;// 商品标题
	private String Dtitle;// 商品短标题
	private String Pic;// 商品主图
	private int Cid; // 分类id
	private float OrgPrice;// 正常售价
	private float Price;// 券后价
	private int IsTmall; // 是否天猫
	private int SalesNum;// 商品销量
	private int Dsr; // 商品描述分
	private int SellerID;// 卖家ID
	private float CommissjonJihua; // 计划(通用)佣金比例
	private float CommissjonQueqiao; // 高佣鹊桥佣金比例
	private String JihuaLink; // 计划链接
	private int JihuaShenhe; // 计划审核状态
	private String Introduce; // 商品文案
	private String QuanId; // 优惠券Id
	private int QuanPrice; // 优惠券金额
	private String QuanTime; // 优惠券结束时间
	private int QuanSurplus; // 优惠券剩余数量
	private int QuanReceive; // 已领券数量
	private String QuanCondition; // 优惠券使用条件
	private String QuanMLink; // 手机券短链
	private String QuanLink; // 手机券链接
	private String AliClilck;// 淘宝客链接

	public HomePageRvItemDate() {};

	public HomePageRvItemDate(String Title, String Dtitle, String Pic, long OrgPrice, long Price, int SalesNum) {

		this.Dtitle = Dtitle;
		this.Title = Title;
		this.Pic = Pic;
		this.OrgPrice = OrgPrice;
		this.Price = Price;
		this.SalesNum = SalesNum;

	}

	public String getAliClilck() {
		return AliClilck;
	}

	public void setAliClilck(String aliClilck) {
		AliClilck = aliClilck;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getGoodsID() {
		return GoodsID;
	}

	public void setGoodsID(String goodsID) {
		GoodsID = goodsID;
	}

	public int getCid() {
		return Cid;
	}

	public void setCid(int cid) {
		Cid = cid;
	}

	public int getIsTmall() {
		return IsTmall;
	}

	public void setIsTmall(int isTmall) {
		IsTmall = isTmall;
	}

	public int getDsr() {
		return Dsr;
	}

	public void setDsr(int dsr) {
		Dsr = dsr;
	}

	public int getSellerID() {
		return SellerID;
	}

	public void setSellerID(int sellerID) {
		SellerID = sellerID;
	}

	public float getCommissjonJihua() {
		return CommissjonJihua;
	}

	public void setCommissjonJihua(long commissjonJihua) {
		CommissjonJihua = commissjonJihua;
	}

	public float getCommissjonQueqiao() {
		return CommissjonQueqiao;
	}

	public void setCommissjonQueqiao(long commissjonQueqiao) {
		CommissjonQueqiao = commissjonQueqiao;
	}

	public String getJihuaLink() {
		return JihuaLink;
	}

	public void setJihuaLink(String jihuaLink) {
		JihuaLink = jihuaLink;
	}

	public int getJihuaShenhe() {
		return JihuaShenhe;
	}

	public void setJihuaShenhe(int jihuaShenhe) {
		JihuaShenhe = jihuaShenhe;
	}

	public String getIntroduce() {
		return Introduce;
	}

	public void setIntroduce(String introduce) {
		Introduce = introduce;
	}

	public String getQuanId() {
		return QuanId;
	}

	public void setQuanId(String quanId) {
		QuanId = quanId;
	}

	public int getQuanPrice() {
		return QuanPrice;
	}

	public void setQuanPrice(int quanPrice) {
		QuanPrice = quanPrice;
	}

	public String getQuanTime() {
		return QuanTime;
	}

	public void setQuanTime(String quanItem) {
		QuanTime = quanItem;
	}

	public int getQuanSurplus() {
		return QuanSurplus;
	}

	public void setQuanSurplus(int quanSurplus) {
		QuanSurplus = quanSurplus;
	}

	public int getQuanReceive() {
		return QuanReceive;
	}

	public void setQuanReceive(int quanReceive) {
		QuanReceive = quanReceive;
	}

	public String getQuanCondition() {
		return QuanCondition;
	}

	public void setQuanCondition(String quanCondition) {
		QuanCondition = quanCondition;
	}

	public String getQuanMLink() {
		return QuanMLink;
	}

	public void setQuanMLink(String quanMLink) {
		QuanMLink = quanMLink;
	}

	public String getQuanLink() {
		return QuanLink;
	}

	public void setQuanLink(String quanLink) {
		QuanLink = quanLink;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDtitle() {
		return Dtitle;
	}

	public void setDtitle(String dtitle) {
		Dtitle = dtitle;
	}

	public String getPic() {
		return Pic;
	}

	public void setPic(String pic) {
		Pic = pic;
	}

	public float getOrgPrice() {
		return OrgPrice;
	}

	public void setOrgPrice(long orgPrice) {
		OrgPrice = orgPrice;
	}

	public float getPrice() {
		return Price;
	}

	public void setPrice(long price) {
		Price = price;
	}

	public int getSalesNum() {
		return SalesNum;
	}

	public void setSalesNum(int salesNum) {
		SalesNum = salesNum;
	}
}
