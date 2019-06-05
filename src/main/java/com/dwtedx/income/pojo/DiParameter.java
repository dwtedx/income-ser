package com.dwtedx.income.pojo;

import java.util.Date;

public class DiParameter {
    private Integer id;

    private String content;

    private String remark;

    private Date createtime;

    private Date updatetime;

    private Integer deletefalag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    public Integer getDeletefalag() {
        return deletefalag;
    }

    public void setDeletefalag(Integer deletefalag) {
        this.deletefalag = deletefalag;
    }
}