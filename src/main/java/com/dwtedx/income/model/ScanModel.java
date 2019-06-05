package com.dwtedx.income.model;

import java.math.BigDecimal;

public class ScanModel extends BaseModel{

    private String username;

    private int userid;

    private int incomeid;

    private BigDecimal moneysum;

    private String name;

    private String store;

    private String brand;

    private String quantity;

    private int type;

    private int sequence;

    private String remark;

    private String createtime;

    private String updatetime;

    private int deletefalag;
    
    private int serverid;
    
    private int isupdate;
    
    private int addbutton;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getIncomeid() {
        return incomeid;
    }

    public void setIncomeid(int incomeid) {
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public int getDeletefalag() {
        return deletefalag;
    }

    public void setDeletefalag(int deletefalag) {
        this.deletefalag = deletefalag;
    }

	public int getServerid() {
		return serverid;
	}

	public void setServerid(int serverid) {
		this.serverid = serverid;
	}

	public int getIsupdate() {
		return isupdate;
	}

	public void setIsupdate(int isupdate) {
		this.isupdate = isupdate;
	}

	public int isAddbutton() {
		return addbutton;
	}

	public void setAddbutton(int addbutton) {
		this.addbutton = addbutton;
	}
    
}