package com.dwtedx.income.model.common;

public class RequestHeadInfo {
	/// 3:Android 4:Android Pad 5:iPhone 6:iPad
	private int clientType;

	/// 客户端SID
	private String clientSID;

	/// 客户端版本号
	private String clientVersion;

	/// 用户凭证
	private String sign;

	/// 用户Pid
	private int userId;

	public int getClientType() {
		return clientType;
	}

	public void setClientType(int clientType) {
		this.clientType = clientType;
	}

	public String getClientSID() {
		return clientSID;
	}

	public void setClientSID(String clientSID) {
		this.clientSID = clientSID;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

}
