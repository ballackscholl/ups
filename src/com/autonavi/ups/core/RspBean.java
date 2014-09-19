package com.autonavi.ups.core;

import java.util.Map;

public abstract class RspBean {

	private String contentType;

	private int statusCode;

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	abstract public byte[] getRespData(Map<String, String> header);
}
