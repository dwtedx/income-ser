package com.dwtedx.income.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class DiIncome {
    private Integer id;
    
    private Integer clientid;

    private String username;

    private Integer userid;

    private Integer role;

    private BigDecimal moneysum;

    private String type;

    private Integer typeid;

    private String account;

    private Integer accountid;

    private String remark;
    
    private String location;

    private String voicepath;

    private String imagepath;
    
    private Date recordtime;

    private Integer recordtype;
    
    private Integer deletefalag;
    
    private Date createtime;

    private Date updatetime;

    public DiIncome() {
		super();
	}

	public DiIncome(Integer clientid, Integer userid) {
		super();
		this.clientid = clientid;
		this.userid = userid;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public void setMoneysum(BigDecimal moneysum) {
		this.moneysum = moneysum;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setVoicepath(String voicepath) {
		this.voicepath = voicepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public void setRecordtime(Date recordtime) {
		this.recordtime = recordtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getUsername() {
		return username;
	}

	public Integer getUserid() {
		return userid;
	}

	public Integer getRole() {
		return role;
	}

	public BigDecimal getMoneysum() {
		return moneysum;
	}

	public String getType() {
		return type;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public String getAccount() {
		return account;
	}

	public Integer getAccountid() {
		return accountid;
	}

	public String getRemark() {
		return remark;
	}

	public String getVoicepath() {
		return voicepath;
	}

	public String getImagepath() {
		return imagepath;
	}

	public Date getRecordtime() {
		return recordtime;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public Integer getClientid() {
		return clientid;
	}

	public void setClientid(Integer clientid) {
		this.clientid = clientid;
	}

	public Integer getRecordtype() {
		return recordtype;
	}

	public void setRecordtype(Integer recordtype) {
		this.recordtype = recordtype;
	}

	public Integer getDeletefalag() {
		return deletefalag;
	}

	public void setDeletefalag(Integer deletefalag) {
		this.deletefalag = deletefalag;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}