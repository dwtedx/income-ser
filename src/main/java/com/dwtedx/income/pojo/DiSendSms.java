package com.dwtedx.income.pojo;

import java.util.Date;

public class DiSendSms {
    private Integer id;

    private String paramstr;

    private String mobile;

    private String tempcode;

    private String signname;

    private String result;

    private Date createtime;

    private Date updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParamstr() {
        return paramstr;
    }

    public void setParamstr(String paramstr) {
        this.paramstr = paramstr == null ? null : paramstr.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getTempcode() {
        return tempcode;
    }

    public void setTempcode(String tempcode) {
        this.tempcode = tempcode == null ? null : tempcode.trim();
    }

    public String getSignname() {
        return signname;
    }

    public void setSignname(String signname) {
        this.signname = signname == null ? null : signname.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
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