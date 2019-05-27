package com.dwtedx.income.model.common;

import com.dwtedx.income.utility.ICConsants;

public class ResultInfo {
	
	private ResultHeadInfo head;
	private Object body;
	
	public ResultInfo() 
    {
        this.head = new ResultHeadInfo(ICConsants.ERRORCODE_10000);
    }

	public ResultHeadInfo getHead() {
		return head;
	}

	public void setHead(ResultHeadInfo head) {
		this.head = head;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	


}
