package com.dwtedx.income.model;

import java.math.BigDecimal;
import java.util.Date;

public class UservipModel {
	private int id;

    private int userid;

    private int months;

    private Date starttime;

    private Date endtime;

    private int type;

    private String typename;

    private String ordercode;

    private BigDecimal payaccount;

    private BigDecimal paytotalaccount;

    private int paytype;

    private Date paytime;

    private String remark;

    private int deleteflag;

    private Date createtime;

    private int createuser;

    private Date updatetime;

    private int updateuser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode == null ? null : ordercode.trim();
    }

    public BigDecimal getPayaccount() {
        return payaccount;
    }

    public void setPayaccount(BigDecimal payaccount) {
        this.payaccount = payaccount;
    }

    public BigDecimal getPaytotalaccount() {
        return paytotalaccount;
    }

    public void setPaytotalaccount(BigDecimal paytotalaccount) {
        this.paytotalaccount = paytotalaccount;
    }

    public int getPaytype() {
        return paytype;
    }

    public void setPaytype(int paytype) {
        this.paytype = paytype;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public int getDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(int deleteflag) {
        this.deleteflag = deleteflag;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public int getCreateuser() {
        return createuser;
    }

    public void setCreateuser(int createuser) {
        this.createuser = createuser;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public int getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(int updateuser) {
        this.updateuser = updateuser;
    }
}