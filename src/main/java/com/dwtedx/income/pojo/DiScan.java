package com.dwtedx.income.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class DiScan {
	/**
	create table di_scan(
		id int primary key auto_increment,
		username varchar(500) null,
		userid integer null,
		incomeid integer null,
		moneysum decimal(18,2) null,
		name varchar(500) null,
		store varchar(500) null,
		brand varchar(500) null,
		quantity varchar(500) null,
		type integer null,
		sequence integer null,
		remark varchar(1000) null,
		createtime datetime null,
		updatetime datetime null,
		deletefalag integer null
	);
	 */
    private Integer id;

    private String username;

    private Integer userid;

    private Integer incomeid;

    private BigDecimal moneysum;

    private String name;

    private String store;

    private String brand;

    private String quantity;

    private Integer type;

    private Integer sequence;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getIncomeid() {
        return incomeid;
    }

    public void setIncomeid(Integer incomeid) {
        this.incomeid = incomeid;
    }

    public BigDecimal getMoneysum() {
        return moneysum;
    }

    public void setMoneysum(BigDecimal moneysum) {
        this.moneysum = moneysum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store == null ? null : store.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity == null ? null : quantity.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
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