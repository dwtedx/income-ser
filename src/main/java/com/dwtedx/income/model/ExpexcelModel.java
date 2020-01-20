package com.dwtedx.income.model;

import java.math.BigDecimal;

public class ExpexcelModel {
    private int id;

    private int userid;

    private String username;

    private String name;

    private int role;

    private BigDecimal moneysumstart;

    private BigDecimal moneysumend;

    private String type;

    private String typeid;

    private String account;

    private String accountid;

    private String recordtimestart;

    private String recordtimeend;

    private int recordtype;
    
    private String filepath;
    
    private int status;

    private String remark;

    private int deleteflag;

    private String createtime;

    private int createuser;

    private String updatetime;

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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
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

    public String getRecordtimestart() {
        return recordtimestart;
    }

    public void setRecordtimestart(String recordtimestart) {
        this.recordtimestart = recordtimestart;
    }

    public String getRecordtimeend() {
        return recordtimeend;
    }

    public void setRecordtimeend(String recordtimeend) {
        this.recordtimeend = recordtimeend;
    }

    public int getRecordtype() {
        return recordtype;
    }

    public void setRecordtype(int recordtype) {
        this.recordtype = recordtype;
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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getCreateuser() {
        return createuser;
    }

    public void setCreateuser(int createuser) {
        this.createuser = createuser;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public int getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(int updateuser) {
        this.updateuser = updateuser;
    }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
    
}