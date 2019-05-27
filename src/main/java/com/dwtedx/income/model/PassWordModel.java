package com.dwtedx.income.model;

public class PassWordModel extends BaseModel{

    private String password;

    private String passwordnew;

    private String passwordconfig;

    private int type;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordnew() {
		return passwordnew;
	}

	public void setPasswordnew(String passwordnew) {
		this.passwordnew = passwordnew;
	}

	public String getPasswordconfig() {
		return passwordconfig;
	}

	public void setPasswordconfig(String passwordconfig) {
		this.passwordconfig = passwordconfig;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
    
}