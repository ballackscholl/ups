package com.autonavi.ups.core;

public class Utility {

	////respones status
	public static final int RESP_STATUS_OK 					= 200;
	public static final int RESP_STATUS_NOCONTENT 			= 206;
	public static final int RESP_STATUS_BADREQUEST 			= 400;
	public static final int RESP_STATUS_NOTFOUND 			= 404;
	public static final int RESP_STATUS_NOTALLOWED 			= 405;
	public static final int RESP_STATUS_SERVERERROR 		= 500;
	public static final int RESP_STATUS_GATEWAYERROR 		= 502;
	public static final int RESP_STATUS_SERVICEUNAVAILABLE 	= 503;
	public static final int RESP_STATUS_GATEWAYTIMEOUT 		= 504;
	
	
	public static final String CONTENT_TYPE_XML = "application/xml; charset=\"utf-8\"";
	public static final String CONTENT_TYPE_ZIP = "application/zip";
	public static final String CONTENT_TYPE_7Z  = "application/7z";
	
	public static final String UPS_CONTEXT_ATTR_CONFIG = "UPSconfig";
	
	public static final String UPS_CONTEXT_ATTR_PARAM =  "UPSParam";
	
	
	
	public static boolean IsEmpty(String str){
		if(str == null || "".equals(str)){
			return true;
		}
		return false;
	}
	
}
