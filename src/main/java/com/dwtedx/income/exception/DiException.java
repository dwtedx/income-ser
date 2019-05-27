package com.dwtedx.income.exception;

import com.dwtedx.income.utility.ICConsants;

public class DiException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int errorCode;
	private String errorMag;

	public DiException() {
		super();
		this.errorCode = ICConsants.ERRORCODE_10001;
		this.errorMag = "系统异常";
	}

	public DiException(int errorCode, String errorMag) {
		super();
		this.errorCode = errorCode;
		this.errorMag = errorMag;
	}
	
	public DiException(String errorMag) {
		super();
		this.errorMag = errorMag;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMag() {
		return errorMag;
	}

	public void setErrorMag(String errorMag) {
		this.errorMag = errorMag;
	}

}
