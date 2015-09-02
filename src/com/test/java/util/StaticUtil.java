package com.test.java.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpGet;

import com.test.java.util.HttpUtil.Response;

/**
 * @author :LiuJie 2015年9月2日 上午9:28:28
 * @注释:静态类
 */
public class StaticUtil {
	
	public  static void TestLogin() {
    	String url="http://manage.ubtob.com/public/account";
		Map<String, String> param =new HashMap<String, String>();
	    param.put("user", "13798490565");
	    param.put("password", "123456");
		try {
			Response response=HttpUtil.sendGetRequest(url, param, true);
			System.out.println("login 状态码：" + response.getStatusCode());
			System.out.println("login 正文：" + response.getResponseText());
		} catch (Exception e) {
			System.out.println("超时");
			System.out.println(e.getMessage());
		}
	}
    
	public  static void TestLoginERP(){
	    String url_erp="http://218.17.158.219:8090/ERP/mobile/login.action";
		Map<String, String> param =new HashMap<String, String>();
	    param.put("username", "YINGP");
	    param.put("password", "123456");
	    param.put("master", "UAS");
		try {
			System.out.println(url_erp);
			Response response=HttpUtil.sendPostRequest(url_erp, param, true);
			System.out.println("" + response.getStatusCode());
			System.out.println("" + response.getResponseText());
		} catch (Exception e) {
			System.out.println("超时");
			System.out.println(e.getMessage());
		}
    }
    
	public  static void TestLoginB2B(){
		//post
    	String url="http://218.17.158.219:9090/platform-b2b/j_spring_security_check";
		Map<String, String> param =new HashMap<String, String>();
	    param.put("j_username", "13798490565");
	    param.put("j_password", "123456");
		try {
			Response response=HttpUtil.sendPostRequest(url, param, true);
			System.out.println("" + response.getStatusCode());
			System.out.println("" + response.getResponseText());
		} catch (Exception e) {
			System.out.println("超时");
			System.out.println(e.getMessage());
		}
    }
    
	//添加请求头
	//	小结：
	//	1、同名Header可以有多个 ，Header[] getHeaders(String name)。
	//	2、运行时使用的是第一个， Header getFirstHeader(String name)。
	//	3、addHeader，如果同名header已存在，则追加至原同名header后面。
	//	4、setHeader，如果同名header已存在，则覆盖一个同名header。
	public  static void testHttpHead() {
		 HttpGet httpGet = new HttpGet("");  
	        httpGet.addHeader("Cookie", "aa"); 
	        httpGet.addHeader("Cookie","liujie");
	        System.out.println("------------1---getFirstHeader----------");  
	        Header header_first = httpGet.getFirstHeader("Cookie");  
	        System.out.println(header_first.getName()+"  "+header_first.getValue());  
	          
	        System.out.println("-----------2---getAllHeaders-----------");  
	          
	        Header headers[] = httpGet.getAllHeaders();  
	        for(Header header:headers){  
	            System.out.println(header.getName()+"  "+header.getValue());  
	        }  
	        httpGet.addHeader("Cookie", "bb");  
	        System.out.println("-----------3----getFirstHeader----------");  
	          
	        header_first = httpGet.getFirstHeader("Cookie");  
	        System.out.println(header_first.getName()+"  "+header_first.getValue());  
	          
	        System.out.println("-----------4----getAllHeaders----------");  
	        headers = httpGet.getAllHeaders();  
	        for(Header header:headers){  
	            System.out.println(header.getName()+"  "+header.getValue());  
	        }  
	        httpGet.setHeader("Cookie", "cc");  
	        System.out.println("-----------3----getFirstHeader----------");  
	          
	        header_first = httpGet.getFirstHeader("Cookie");  
	        System.out.println(header_first.getName()+"  "+header_first.getValue());  
	          
	        System.out.println("-----------4----getAllHeaders----------");  
	        headers = httpGet.getAllHeaders();  
	        for(Header header:headers){  
	            System.out.println(header.getName()+"  "+header.getValue());  
	        }  
	        httpGet.setHeader("Cookie", "dd");  
	        System.out.println("-----------3----getFirstHeader----------");  
	          
	        header_first = httpGet.getFirstHeader("Cookie");  
	        System.out.println(header_first.getName()+"  "+header_first.getValue());  
	          
	        System.out.println("-----------4----getAllHeaders----------");  
	        headers = httpGet.getAllHeaders();  
	        for(Header header:headers){  
	            System.out.println(header.getName()+"  "+header.getValue());  
	        }  
	}
}
