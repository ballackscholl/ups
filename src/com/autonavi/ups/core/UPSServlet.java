package com.autonavi.ups.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UPSServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse rsp)
			throws ServletException, IOException {

		String op = req.getParameter("op");
		
		Object attr = getServletContext().getAttribute(Utility.UPS_CONTEXT_ATTR_CONFIG);
		
		if(!(attr instanceof Map<?,?>)){
			rsp.setStatus(Utility.RESP_STATUS_SERVERERROR);
			return;
		}

		@SuppressWarnings("unchecked")
		Map<String, ConfigData> config = (Map<String, ConfigData>)attr;
		
		ConfigData configData = config.get(op);
		if(configData == null){
			rsp.setStatus(Utility.RESP_STATUS_BADREQUEST);
			return;
		}
		
		Parser parser = null;
		ReqBean bean = null;
		try {
			parser = ComponentFactory.getParserInstance(configData.getParserClassName());
			
			bean = ComponentFactory.getBeanInstance(configData.getReqBeanName());
			
			if(parser == null || bean == null){
				rsp.setStatus(Utility.RESP_STATUS_BADREQUEST);
				return;
			}
			
			parser.initParser(req);
			parser.parse(bean);
			
		} catch (Exception e) {
			rsp.setStatus(Utility.RESP_STATUS_BADREQUEST);
			e.printStackTrace();
			return;
		}
		
		if(!bean.check()){
			rsp.setStatus(Utility.RESP_STATUS_BADREQUEST);
			return;
		}
		
		Service service = null;
		try {
			service = ComponentFactory.getServiceInstance(configData.getServiceClassName());
		} catch (Exception e) {
			rsp.setStatus(Utility.RESP_STATUS_SERVERERROR);
			e.printStackTrace();
			return;
		}
		
		if(service == null){
			rsp.setStatus(Utility.RESP_STATUS_SERVERERROR);
			return;
		}
		
		Object ob = getServletContext().getAttribute(Utility.UPS_CONTEXT_ATTR_PARAM);
		
		RspBean rspBean = service.execute(op, bean, ob);
		if(rspBean != null){
			rsp.setStatus(rspBean.getStatusCode());
			rsp.setContentType(rspBean.getContentType());
			
			Map<String, String> header = new HashMap<String, String>();
	
			byte[] data = rspBean.getRespData(header);
			for(String key : header.keySet()){
				rsp.setHeader(key, header.get(key));
			}
			
			if(data != null){
				rsp.getOutputStream().write(data);
			}	
		}else{
			rsp.setStatus(Utility.RESP_STATUS_SERVICEUNAVAILABLE);
		}
		
	}

}
