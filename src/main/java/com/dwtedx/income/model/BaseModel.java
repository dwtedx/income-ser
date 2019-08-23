package com.dwtedx.income.model;

public class BaseModel {
	private int id;
	
	private int start;
	private int length;
	
	private String serialVersionUID;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getSerialVersionUID() {
		return serialVersionUID;
	}

	public void setSerialVersionUID(String serialVersionUID) {
		this.serialVersionUID = serialVersionUID;
	}
	
}
