package com.dwtedx.income.model.common;

public class ResultHeadInfo {
    /// 10000	请求成功
    /// 10001	请求失败
    /// 10002   验证用户token错误
    private int errorCode;
    private String message;

	public ResultHeadInfo() {
		super();
	}

	public ResultHeadInfo(int errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}
	
	public ResultHeadInfo(int errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
}
