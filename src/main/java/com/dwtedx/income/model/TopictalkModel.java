package com.dwtedx.income.model;

import java.util.Date;
import java.util.List;

import com.dwtedx.income.utility.CommonUtility;

public class TopictalkModel {
    private int id;

    private int topicid;

    private int userid;

    private String name;

    private String content;

    private int liked;

    private String remark;

    private int deleteflag;

    private Date createtime;

    private int createuser;

    private Date updatetime;

    private int updateuser;
    
    
    private List<TopictalkimgModel> topictalkimg;
    private boolean userLiked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTopicid() {
        return topicid;
    }

    public void setTopicid(int topicid) {
        this.topicid = topicid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
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

    public String getCreatetimestr() {
        return CommonUtility.stringDateFormart(createtime);
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

	public boolean isUserLiked() {
		return userLiked;
	}

	public void setUserLiked(boolean userLiked) {
		this.userLiked = userLiked;
	}

	public List<TopictalkimgModel> getTopictalkimg() {
		return topictalkimg;
	}

	public void setTopictalkimg(List<TopictalkimgModel> topictalkimg) {
		this.topictalkimg = topictalkimg;
	}
    
}