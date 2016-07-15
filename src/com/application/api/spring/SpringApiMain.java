package com.application.api.spring;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.application.api.erp.ErpApiUtil;
import com.application.util.HttpUtil.Response;

public class SpringApiMain extends SpringApiBase{
	public static Map<String, Object> param = new HashMap<String, Object>();
	public static void main(String[] args) {

		String url="http://192.168.253.200:8080/spring-mvc-showcase/"+"/client/info";
		LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
		headers.put("cookie", "JSESSIONID=13266699268");
	    Response response=	ErpApiUtil.commomHttpMethod(url, null, headers, null, "get");
	    System.out.println(response.getResponseText());	
	
	}

	
}
