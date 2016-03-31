package com.application.http.httpclient;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.application.news.StaticUtil;
import com.application.util.HttpUtil.Response;

public class SpringApiMain {
	
	public static Map<String, Object> param=new HashMap<String, Object>();
    public static LinkedHashMap<String , Object> headers=new LinkedHashMap<>();
    public static String url="http://127.0.0.1:8080/spring-mvc-showcase/";

	public static void main(String[] args) {
		url=url+"/simple1/save/";
		param.put("name", "妮娜");
		param.put("password", "1w2sd345621");
		Response response=load(url, param, headers, null, "post");
	    System.out.println(JSON.toJSONString(response.getHttpResponse().getAllHeaders()));
	    System.out.println(JSON.toJSONString(response.getHttpResponse().getEntity()));
	    System.out.println(JSON.toJSONString(response.getHttpResponse().getLocale()));
		
	}

	/**
	  * @author Administrator
	  * @功能:响应函数
	  */
	public static Response load(String url, 
			Map<String, Object> param, 
			LinkedHashMap<String, Object> headers,
			String bodyJson,
			String post){
	  Response response=StaticUtil.commomHttpMethod(url, param, headers, bodyJson, post);
	  System.out.println("--------------------------------------------------------------");
	  System.out.println(response.getResponseText());
	  System.out.println(response.getStatusCode());
	  System.out.println(url);
	  System.out.println("--------------------------------------------------------------");
	  return response;
	}
}
