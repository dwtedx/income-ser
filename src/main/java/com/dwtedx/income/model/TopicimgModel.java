package com.dwtedx.income.model;

import java.util.Date;

import com.dwtedx.income.utility.ICConsants;

public class TopicimgModel {
    private int id;

    private int topicid;

    private String name;

    private String path;
    
    private int width;

    private int height;

    private String remark;

    private int deleteflag;

    private Date createtime;

    private int createuser;

    private Date updatetime;

    private int updateuser;
    
    //APP携带
    private String serialVersionUID;
    private CREATOR CREATOR;
    private CREATOR mBounds;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPath() {
        return path;
    }
    
    public String getFullpath() {
        return  ICConsants.ITOPIC_HTTP_URL + path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
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
    

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getSerialVersionUID() {
		return serialVersionUID;
	}

	public void setSerialVersionUID(String serialVersionUID) {
		this.serialVersionUID = serialVersionUID;
	}
	
	public CREATOR getCREATOR() {
		return CREATOR;
	}

	public void setCREATOR(CREATOR cREATOR) {
		CREATOR = cREATOR;
	}
	
	public CREATOR getmBounds() {
		return mBounds;
	}

	public void setmBounds(CREATOR mBounds) {
		this.mBounds = mBounds;
	}



	public class CREATOR{
		private String serialVersionUID;

		public String getSerialVersionUID() {
			return serialVersionUID;
		}

		public void setSerialVersionUID(String serialVersionUID) {
			this.serialVersionUID = serialVersionUID;
		}
	}
    
}