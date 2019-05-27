package com.dwtedx.income.model;

public class TaobaoModel extends BaseModel {
	private String search;
	private long numid;
	private int categoryId;
	private int start;
	private int length;
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public long getNumid() {
		return numid;
	}
	public void setNumid(long numid) {
		this.numid = numid;
	}
	
}
