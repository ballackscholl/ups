package com.autonavi.ups.core;

public class ConfigData {

	private String op;
	
	private String parserClassName;
	
	private String serviceClassName;
	
	private String reqBeanName;
	

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getParserClassName() {
		return parserClassName;
	}

	public void setParserClassName(String parserClassName) {
		this.parserClassName = parserClassName;
	}

	public String getServiceClassName() {
		return serviceClassName;
	}

	public void setServiceClassName(String serviceClassName) {
		this.serviceClassName = serviceClassName;
	}

	public String getReqBeanName() {
		return reqBeanName;
	}

	public void setReqBeanName(String reqBeanName) {
		this.reqBeanName = reqBeanName;
	}



}
