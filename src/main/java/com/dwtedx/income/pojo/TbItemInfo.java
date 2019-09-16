package com.dwtedx.income.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbItemInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;

    private String itemUrl;

    private String nick;

    private String numIid;

    private Integer categoryId;

    private String pictUrl;

    private String provcity;

    private String reservePrice;

    private String sellerId;

    private String smallImages;

    private String title;

    private Integer userType;

    private Integer volume;

    private String zkFinalPrice;

    private String taokeUrl;

    private String brokeragePro;

    private String brokerage;

    private String storeName;

    private String coupon;

    private String couponId;

    private String couponUrl;

    private String couponUrlTaoke;

    private Integer couponCount;

    private Integer couponSurplus;

    private String couponStart;

    private String couponEnd;

    private String tagContent;

    private Date createtime;

    private Date updatetime;

    public TbItemInfo() {
		super();
	}
    
    public TbItemInfo(String itemUrl, String nick, String numIid, Integer categoryId, String pictUrl,
			String provcity, String reservePrice, String sellerId, String smallImages, String title, Integer userType,
			Integer volume, String zkFinalPrice, String taokeUrl, String brokeragePro, String brokerage,
			String storeName, String coupon, String couponId, String couponUrl, String couponUrlTaoke,
			Integer couponCount, Integer couponSurplus, String couponStart, String couponEnd, String tagContent,
			Date createtime, Date updatetime) {
		super();
		this.itemUrl = itemUrl;
		this.nick = nick;
		this.numIid = numIid;
		this.categoryId = categoryId;
		this.pictUrl = pictUrl;
		this.provcity = provcity;
		this.reservePrice = reservePrice;
		this.sellerId = sellerId;
		this.smallImages = smallImages;
		this.title = title;
		this.userType = userType;
		this.volume = volume;
		this.zkFinalPrice = zkFinalPrice;
		this.taokeUrl = taokeUrl;
		this.brokeragePro = brokeragePro;
		this.brokerage = brokerage;
		this.storeName = storeName;
		this.coupon = coupon;
		this.couponId = couponId;
		this.couponUrl = couponUrl;
		this.couponUrlTaoke = couponUrlTaoke;
		this.couponCount = couponCount;
		this.couponSurplus = couponSurplus;
		this.couponStart = couponStart;
		this.couponEnd = couponEnd;
		this.tagContent = tagContent;
		this.createtime = createtime;
		this.updatetime = updatetime;
	}

	public TbItemInfo(Integer id, String itemUrl, String nick, String numIid, Integer categoryId, String pictUrl,
			String provcity, String reservePrice, String sellerId, String smallImages, String title, Integer userType,
			Integer volume, String zkFinalPrice, String taokeUrl, String brokeragePro, String brokerage,
			String storeName, String coupon, String couponId, String couponUrl, String couponUrlTaoke,
			Integer couponCount, Integer couponSurplus, String couponStart, String couponEnd, String tagContent,
			Date createtime, Date updatetime) {
		super();
		this.id = id;
		this.itemUrl = itemUrl;
		this.nick = nick;
		this.numIid = numIid;
		this.categoryId = categoryId;
		this.pictUrl = pictUrl;
		this.provcity = provcity;
		this.reservePrice = reservePrice;
		this.sellerId = sellerId;
		this.smallImages = smallImages;
		this.title = title;
		this.userType = userType;
		this.volume = volume;
		this.zkFinalPrice = zkFinalPrice;
		this.taokeUrl = taokeUrl;
		this.brokeragePro = brokeragePro;
		this.brokerage = brokerage;
		this.storeName = storeName;
		this.coupon = coupon;
		this.couponId = couponId;
		this.couponUrl = couponUrl;
		this.couponUrlTaoke = couponUrlTaoke;
		this.couponCount = couponCount;
		this.couponSurplus = couponSurplus;
		this.couponStart = couponStart;
		this.couponEnd = couponEnd;
		this.tagContent = tagContent;
		this.createtime = createtime;
		this.updatetime = updatetime;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl == null ? null : itemUrl.trim();
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick == null ? null : nick.trim();
    }

    public String getNumIid() {
        return numIid;
    }

    public void setNumIid(String numIid) {
        this.numIid = numIid == null ? null : numIid.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getPictUrl() {
        return pictUrl;
    }

    public void setPictUrl(String pictUrl) {
        this.pictUrl = pictUrl == null ? null : pictUrl.trim();
    }

    public String getProvcity() {
        return provcity;
    }

    public void setProvcity(String provcity) {
        this.provcity = provcity == null ? null : provcity.trim();
    }

    public String getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(String reservePrice) {
        this.reservePrice = reservePrice == null ? null : reservePrice.trim();
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId == null ? null : sellerId.trim();
    }

    public String getSmallImages() {
        return smallImages;
    }

    public void setSmallImages(String smallImages) {
        this.smallImages = smallImages == null ? null : smallImages.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getZkFinalPrice() {
        return zkFinalPrice;
    }

    public void setZkFinalPrice(String zkFinalPrice) {
        this.zkFinalPrice = zkFinalPrice == null ? null : zkFinalPrice.trim();
    }

    public String getTaokeUrl() {
        return taokeUrl;
    }

    public void setTaokeUrl(String taokeUrl) {
        this.taokeUrl = taokeUrl == null ? null : taokeUrl.trim();
    }

    public String getBrokeragePro() {
        return brokeragePro;
    }

    public void setBrokeragePro(String brokeragePro) {
        this.brokeragePro = brokeragePro == null ? null : brokeragePro.trim();
    }

    public String getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(String brokerage) {
        this.brokerage = brokerage == null ? null : brokerage.trim();
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon == null ? null : coupon.trim();
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId == null ? null : couponId.trim();
    }

    public String getCouponUrl() {
        return couponUrl;
    }

    public void setCouponUrl(String couponUrl) {
        this.couponUrl = couponUrl == null ? null : couponUrl.trim();
    }

    public String getCouponUrlTaoke() {
        return couponUrlTaoke;
    }

    public void setCouponUrlTaoke(String couponUrlTaoke) {
        this.couponUrlTaoke = couponUrlTaoke == null ? null : couponUrlTaoke.trim();
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

    public Integer getCouponSurplus() {
        return couponSurplus;
    }

    public void setCouponSurplus(Integer couponSurplus) {
        this.couponSurplus = couponSurplus;
    }

    public String getCouponStart() {
        return couponStart;
    }

    public void setCouponStart(String couponStart) {
        this.couponStart = couponStart == null ? null : couponStart.trim();
    }

    public String getCouponEnd() {
        return couponEnd;
    }

    public void setCouponEnd(String couponEnd) {
        this.couponEnd = couponEnd == null ? null : couponEnd.trim();
    }

    public String getTagContent() {
        return tagContent;
    }

    public void setTagContent(String tagContent) {
        this.tagContent = tagContent == null ? null : tagContent.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}