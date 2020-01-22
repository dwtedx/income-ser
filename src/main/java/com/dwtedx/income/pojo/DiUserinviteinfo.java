package com.dwtedx.income.pojo;

import java.util.Date;

public class DiUserinviteinfo {
    private Integer id;

    private Integer userid;

    private Integer inviteuserid;

    private Date invitetime;

    private Integer givevip;

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

    public Integer getInviteuserid() {
        return inviteuserid;
    }

    public void setInviteuserid(Integer inviteuserid) {
        this.inviteuserid = inviteuserid;
    }

    public Date getInvitetime() {
        return invitetime;
    }

    public void setInvitetime(Date invitetime) {
        this.invitetime = invitetime;
    }

    public Integer getGivevip() {
        return givevip;
    }

    public void setGivevip(Integer givevip) {
        this.givevip = givevip;
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