package com.application.spring.api;

import java.util.HashMap;
import java.util.Map;

import com.application.base.SpringApiBase;

public class SpringApiMain extends SpringApiBase{
	public static Map<String, Object> param = new HashMap<String, Object>();
	public static void main(String[] args) {
		String url="http://127.0.0.1:8080/spring-mvc-showcase/"+"/simple1/save/";
		param.put("name", "妮娜s");
		param.put("password", "1w2sd345621");
		HttpEntity httpEntity=new SpringApiBase().new HttpEntity(url,param);
		httpEntity.setMethod("post");
		httpEntity.startHttpTask();
	}

	
}
