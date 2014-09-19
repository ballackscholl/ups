package com.autonavi.ups.core;

import javax.servlet.http.HttpServletRequest;


public interface Parser {
	public void parse(ReqBean bean) throws Exception;
	
	public void initParser(HttpServletRequest req) throws Exception;
}
