package com.dwtedx.income.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class DiExpexcel {
    private Integer id;

    private Integer userid;

    private String username;

    private String name;

    private Integer role;

    private BigDecimal moneysumstart;

    private BigDecimal moneysumend;

    private String type;

    private String typeid;

    private String account;

    private String accountid;

    private Date recordtimestart;

    private Date recordtimeend;

    private Integer recordtype;

    private String filepath;

    private Integer status;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public BigDecimal getMoneysumstart() {
        return moneysumstart;
    }

    public void setMoneysumstart(BigDecimal moneysumstart) {
        this.moneysumstart = moneysumstart;
    }

    public BigDecimal getMoneysumend() {
        return moneysumend;
    }

    public void setMoneysumend(BigDecimal moneysumend) {
        this.moneysumend = moneysumend;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid == null ? null : typeid.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid == null ? null : accountid.trim();
    }

    public Date getRecordtimestart() {
        return recordtimestart;
    }

    public void setRecordtimestart(Date recordtimestart) {
        this.recordtimestart = recordtimestart;
    }

    public Date getRecordtimeend() {
        return recordtimeend;
    }

    public void setRecordtimeend(Date recordtimeend) {
        this.recordtimeend = recordtimeend;
    }

    public Integer getRecordtype() {
        return recordtype;
    }

    public void setRecordtype(Integer recordtype) {
        this.recordtype = recordtype;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath == null ? null : filepath.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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