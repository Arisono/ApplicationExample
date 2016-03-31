package com.application.base;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.application.http.httpclient.StaticUtil;
import com.application.util.HttpUtil.Response;

public class SpringApiBase {
	
	public static Map<String, Object> param=new HashMap<String, Object>();
	public static LinkedHashMap<String,Object> headers=new LinkedHashMap<>();
	public static String url="http://127.0.0.1:8080/spring-mvc-showcase/";
	/**
	  * @author Administrator
	  * @功能:响应函数
	  *       HttpClient方式
	  */
	public static Response load(String url, 
			Map<String, Object> param, 
			LinkedHashMap<String, Object> headers,
			String bodyJson,
			String post){
	  Response response=StaticUtil.commomHttpMethod(url, param, headers, bodyJson, post);
	  System.out.println("--------------------------------------------------------------");
	  System.out.println(url);
	  System.out.println(response.getResponseText());
	  System.out.println(response.getStatusCode());
	  System.out.println("--------------------------------------------------------------");
	  return response;
	}
	

}
