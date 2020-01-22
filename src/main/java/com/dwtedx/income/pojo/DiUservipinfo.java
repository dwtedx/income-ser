package com.dwtedx.income.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class DiUservipinfo {
    private Integer id;

    private Integer userid;

    private Date starttime;

    private Date endtime;

    private Integer type;

    private BigDecimal payaccount;

    private BigDecimal totalpayaccount;

    private Integer paytype;

    private Date paytime;

    private String remark;

    private Integer deleteflag;

    private Date createtime;

    private Integer createuser;

    private Date updatetime;

    private Integer updateuser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getPayaccount() {
        return payaccount;
    }

    public void setPayaccount(BigDecimal payaccount) {
        this.payaccount = payaccount;
    }

    public BigDecimal getTotalpayaccount() {
        return totalpayaccount;
    }

    public void setTotalpayaccount(BigDecimal totalpayaccount) {
        this.totalpayaccount = totalpayaccount;
    }

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer paytype) {
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

    public Integer getDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getCreateuser() {
        return createuser;
    }

    public void setCreateuser(Integer createuser) {
        this.createuser = createuser;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(Integer updateuser) {
        this.updateuser = updateuser;
    }
}