package com.dwtedx.income.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class DiBudget {
    private Integer id;
    
    private Integer clientid;

    private BigDecimal moneysum;

    private BigDecimal moneylast;

    private String username;

    private Integer userid;

    private Integer yearnom;

    private Integer monthnom;

    private String remark;

    private Date createtime;

    private Date updatetime;
    
    

    public DiBudget() {
		super();
	}
    
	public DiBudget(Integer clientid, Integer userid) {
		super();
		this.clientid = clientid;
		this.userid = userid;
	}

	public DiBudget(Integer id, Integer clientid, BigDecimal moneysum, BigDecimal moneylast, String username,
			Integer userid, Integer yearnom, Integer monthnom, String remark, Date createtime, Date updatetime) {
		super();
		this.id = id;
		this.clientid = clientid;
		this.moneysum = moneysum;
		this.moneylast = moneylast;
		this.username = username;
		this.userid = userid;
		this.yearnom = yearnom;
		this.monthnom = monthnom;
		this.remark = remark;
		this.createtime = createtime;
		this.updatetime = updatetime;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getMoneysum() {
        return moneysum;
    }

    public void setMoneysum(BigDecimal moneysum) {
        this.moneysum = moneysum;
    }

    public BigDecimal getMoneylast() {
        return moneylast;
    }

    public void setMoneylast(BigDecimal moneylast) {
        this.moneylast = moneylast;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getYearnom() {
        return yearnom;
    }

    public void setYearnom(Integer yearnom) {
        this.yearnom = yearnom;
    }

    public Integer getMonthnom() {
        return monthnom;
    }

    public void setMonthnom(Integer monthnom) {
        this.monthnom = monthnom;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

	public Integer getClientid() {
		return clientid;
	}

	public void setClientid(Integer clientid) {
		this.clientid = clientid;
	}
    
}