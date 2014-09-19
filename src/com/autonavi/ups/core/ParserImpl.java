package com.autonavi.ups.core;

import java.io.EOFException;

import javax.servlet.http.HttpServletRequest;

import org.xmlpull.mxp1.MXParser;
import org.xmlpull.v1.XmlPullParser;

public abstract class ParserImpl implements Parser {

	private XmlPullParser parser;

	private HttpServletRequest servletReq;

	@Override
	public void initParser(HttpServletRequest req) throws Exception {
		parser = new MXParser();
		parser.setInput(req.getReader());
		servletReq = req;
	}

	abstract protected void parseUrlInfo(HttpServletRequest req,
			ReqBean bean) throws Exception;

	abstract protected void parseXmlInfo(XmlPullParser parser, ReqBean bean)
			throws Exception;

	@Override
	public void parse(ReqBean bean) throws Exception{
		
		if(servletReq != null){
			parseUrlInfo(servletReq, bean);
		}
		
		if(parser != null){
			try{
				parseXmlInfo(parser, bean);
			}catch(EOFException e){
			}
		}
		
	}
}
