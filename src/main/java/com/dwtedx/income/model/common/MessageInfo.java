package com.dwtedx.income.model.common;

public class MessageInfo<T> {
	
	private RequestHeadInfo head;
	private T body;
	
	public RequestHeadInfo getHead() {
		return head;
	}
	public void setHead(RequestHeadInfo head) {
		this.head = head;
	}
	public T getBody() {
		return body;
	}
	public void setBody(T body) {
		this.body = body;
	}
	
	

}
