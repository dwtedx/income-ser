package com.dwtedx.income.model;

public class UserinviteModel {
    private int id;

    private int userid;

    private int inviteuserid;

    private String invitephone;

    private String invitetime;
    
    private String invitetimeStr;

    private int givevip;

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

    public int getInviteuserid() {
        return inviteuserid;
    }

    public void setInviteuserid(int inviteuserid) {
        this.inviteuserid = inviteuserid;
    }

    public String getInvitephone() {
        return invitephone;
    }

    public void setInvitephone(String invitephone) {
        this.invitephone = invitephone == null ? null : invitephone.trim();
    }

    public String getInvitetime() {
        return invitetime;
    }

    public void setInvitetime(String invitetime) {
        this.invitetime = invitetime;
    }

    public int getGivevip() {
        return givevip;
    }

    public void setGivevip(int givevip) {
        this.givevip = givevip;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

	public String getInvitetimeStr() {
		return invitetimeStr;
	}

	public void setInvitetimeStr(String invitetimeStr) {
		this.invitetimeStr = invitetimeStr;
	}
    
}