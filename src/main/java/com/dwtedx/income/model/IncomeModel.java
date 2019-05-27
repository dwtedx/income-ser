package com.dwtedx.income.model;

import java.math.BigDecimal;
import java.util.List;

public class IncomeModel extends BaseModel{
	
	private int clientid;
	
    private String username;

    private int userid;

    private int role;

    private BigDecimal moneysum;
    
    private BigDecimal moneysumLeft;
    
    private BigDecimal moneysumRight;

    private String type;

    private int typeid;

    private String account;

    private int accountid;

    private String remark;
    
    private String location;

    private String voicepath;

    private String imagepath;
    
    private int recordtype;

    private String recordtime;

    private String createtime;

    private String updatetime;
    
    private int isupdate;
    
    private int serverid;
    
    private int deletefalag;
    
    private String color;
    
    private String icon;
    
    private String recordtimeformat;
    
    private List<ScanModel> scanList;
    

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public BigDecimal getMoneysum() {
		return moneysum;
	}

	public void setMoneysum(BigDecimal moneysum) {
		this.moneysum = moneysum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getAccountid() {
		return accountid;
	}

	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getVoicepath() {
		return voicepath;
	}

	public void setVoicepath(String voicepath) {
		this.voicepath = voicepath;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public String getRecordtime() {
		return recordtime;
	}

	public void setRecordtime(String recordtime) {
		this.recordtime = recordtime;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public BigDecimal getMoneysumLeft() {
		return moneysumLeft;
	}

	public void setMoneysumLeft(BigDecimal moneysumLeft) {
		this.moneysumLeft = moneysumLeft;
	}

	public BigDecimal getMoneysumRight() {
		return moneysumRight;
	}

	public void setMoneysumRight(BigDecimal moneysumRight) {
		this.moneysumRight = moneysumRight;
	}

	public int getIsupdate() {
		return isupdate;
	}

	public void setIsupdate(int isupdate) {
		this.isupdate = isupdate;
	}

	public int getClientid() {
		return clientid;
	}

	public void setClientid(int clientid) {
		this.clientid = clientid;
	}

	public int getServerid() {
		return serverid;
	}

	public void setServerid(int serverid) {
		this.serverid = serverid;
	}

	public int getRecordtype() {
		return recordtype;
	}

	public void setRecordtype(int recordtype) {
		this.recordtype = recordtype;
	}

	public List<ScanModel> getScanList() {
		return scanList;
	}

	public void setScanList(List<ScanModel> scanList) {
		this.scanList = scanList;
	}

	public int getDeletefalag() {
		return deletefalag;
	}

	public void setDeletefalag(int deletefalag) {
		this.deletefalag = deletefalag;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getRecordtimeformat() {
		return recordtimeformat;
	}

	public void setRecordtimeformat(String recordtimeformat) {
		this.recordtimeformat = recordtimeformat;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}
