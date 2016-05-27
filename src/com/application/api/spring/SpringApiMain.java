package com.application.api.spring;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.application.util.HttpUtil;

public class SpringApiMain extends SpringApiBase{
	public static Map<String, Object> param = new HashMap<String, Object>();
	public static void main(String[] args) {
//		String url="http://127.0.0.1:8080/spring-mvc-showcase/"+"/simple1/save/";
//		param.put("name", "妮娜s");
//		param.put("password", "1w2sd345621");
//		HttpEntity httpEntity=new SpringApiBase().new HttpEntity(url,param);
//		httpEntity.setMethod("post");
//		httpEntity.startHttpTask();
		
	String url="http://113.105.74.140:8081/avatar/o/123/100123.jpg";
	String path=HttpUtil.download(url, "C:/Users/Arisono/Desktop/nihao/ji1.jpg");
	System.out.println(path+"1:"+new SimpleDateFormat("MM-dd:HH:mm:ss:SS").format(new Date()));
	
	
	
	 url="http://113.105.74.140:8081/avatar/o/123/100123.jpg";
	 path=HttpUtil.download(url, "C:/Users/Arisono/Desktop/ji2.jpg");
	System.out.println(path+"2:"+new SimpleDateFormat("MM-dd:HH:mm:ss:SS").format(new Date()));
	}

	
}
