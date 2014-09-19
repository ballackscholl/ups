package com.autonavi.ups.core;

public class ComponentFactory {

	public static Parser getParserInstance(String parserClassName)
			throws Exception {

		Class<?> clazz = Class.forName(parserClassName);
		if (clazz == null) {
			return null;
		}

		Object parser = clazz.newInstance();

		if (parser instanceof Parser) {
			return (Parser) parser;
		}

		return null;

	}

	public static Service getServiceInstance(String serviceClassName)
			throws Exception {

		Class<?> clazz = Class.forName(serviceClassName);
		if (clazz == null) {
			return null;
		}

		Object parser = clazz.newInstance();

		if (parser instanceof Service) {
			return (Service) parser;
		}

		return null;
	}
	
	public static ReqBean getBeanInstance(String beanClassName)
			throws Exception {

		Class<?> clazz = Class.forName(beanClassName);
		if (clazz == null) {
			return null;
		}

		Object reqBean = clazz.newInstance();

		if (reqBean instanceof ReqBean) {
			return (ReqBean) reqBean;
		}

		return null;
	}
	
}
