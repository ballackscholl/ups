package com.autonavi.ups.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.xmlpull.mxp1.MXParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ApplicationListener implements ServletContextListener {

	private final String configPath = "WEB-INF/service.xml"; 
	
	public void contextDestroyed(ServletContextEvent arg) {

	}

	public void contextInitialized(ServletContextEvent arg) {
		
		XmlPullParser parser = new MXParser();
		
		InputStream input;
		try {
			String path = arg.getServletContext().getRealPath("/") + configPath;
			input = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		} 
		
		try {
			parser.setInput(new InputStreamReader(input));
			
			Map<String, ConfigData> config = parserConfig(parser);
			
			arg.getServletContext().setAttribute(Utility.UPS_CONTEXT_ATTR_CONFIG, config);
			
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}finally{
			if(input != null)
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	
	private Map<String, ConfigData> parserConfig(XmlPullParser parser) throws XmlPullParserException, IOException{
	
		Map<String, ConfigData> configMap = new HashMap<String, ConfigData>();
		
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			
			if (eventType == XmlPullParser.START_TAG) {
				
				if("action".equals(parser.getName())){
					
					ConfigData data = new ConfigData();
					
					data.setOp(parser.getAttributeValue("", "op"));
					data.setParserClassName(parser.getAttributeValue("", "parser"));
					data.setServiceClassName(parser.getAttributeValue("", "service"));
					data.setReqBeanName(parser.getAttributeValue("", "bean"));
					
					configMap.put(data.getOp(), data);
				}	
			}
			
			eventType = parser.next();
		}
		
		return configMap;
	}
	
}
