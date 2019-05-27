package com.dwtedx.income.model;

import java.math.BigDecimal;

public class BudgetModel extends BaseModel{

    private BigDecimal moneysum;

    private BigDecimal moneylast;

    private String username;

    private int userid;

    private int yearnom;

    private int monthnom;

    private String remark;

    private String createtime;

    private String updatetime;
    
    private int isupdate;//客户端用
    
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

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getYearnom() {
        return yearnom;
    }

    public void setYearnom(int yearnom) {
        this.yearnom = yearnom;
    }

    public int getMonthnom() {
        return monthnom;
    }

    public void setMonthnom(int monthnom) {
        this.monthnom = monthnom;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

	public int getIsupdate() {
		return isupdate;
	}

	public void setIsupdate(int isupdate) {
		this.isupdate = isupdate;
	}
    
}