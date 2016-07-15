package com.application.api.im;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.application.api.erp.ErpApiUtil;
import com.application.util.DateFormatUtil;
import com.application.util.HttpUtil.Response;
import com.application.util.Md5Util;

public class IMAPITestMain {

	public static String access_token = "8f3711ff3576498584609278794d9e41";

	public static void main(String[] args) {
		downloadInitIM();
	}
	
	public static void getPublicMessage(){
		String url ="http://113.105.74.140:8092/b/circle/msg/list";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("access_token", access_token);
		param.put("pageSize", "10");
		param.put("type", "0");
		Response response=	ErpApiUtil.commomHttpMethod(url, param, null, null, "get");
		System.out.println(response.getResponseText());
	}
	
	/*I/Arison: LoginActivity:login:159:model=MI 2S
			I/Arison: LoginActivity:login:160:osVersion=4.1.1
			I/Arison: LoginActivity:login:161:serial=863121023715394
			I/Arison: LoginActivity:login:162:latitude=22.54049
			I/Arison: LoginActivity:login:163:longitude=113.953253
	*/
	public static void loginIM(String phoneNumber,String digestPwd){
		String url ="http://113.105.74.140:8092/user/login";
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("telephone", Md5Util.toMD5(phoneNumber));// 账号登陆的时候需要MD5加密，服务器需求
	    params.put("password", digestPwd);
	    params.put("model", "MI 2S");
	    params.put("osVersion", "4.1.1");
	    params.put("serial", "863121023715394");
	    params.put("latitude", "22.54049");
	    params.put("longitude", "113.953253");
		Response response=	ErpApiUtil.commomHttpMethod(url, params, null, null, "get");
		System.out.println(response.getResponseText());
		
	}

	public static void downloadInitIM() {
		downloadCircleMessage();
		downloadUserPhoto();
		downloadAddressBook();
		downloadRoom();
		downloadUserInfo();
	}
	
	public static void downloadCircleMessage(){
		String url ="http://113.105.74.140:8092/b/circle/msg/ids";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("access_token", access_token);
		Response response=	ErpApiUtil.commomHttpMethod(url, param, null, null, "get");
		System.out.println(response.getResponseText());
	}
	
	public static void downloadAddressBook(){
		String url ="http://113.105.74.140:8092/friends/attention/list";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("access_token", access_token);
		Response response=	ErpApiUtil.commomHttpMethod(url, param, null, null, "get");
		System.out.println(response.getResponseText());
	}
	
	public static void downloadUserInfo(){
		String url ="http://113.105.74.140:8092/user/get";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("access_token", access_token);
		Response response=	ErpApiUtil.commomHttpMethod(url, param, null, null, "get");
		System.out.println(response.getResponseText());
	}

	
	public static void downloadUserPhoto(){
		String url ="http://113.105.74.140:8092/user/photo/list";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("access_token", access_token);
		param.put("roomId", "574f907e0cf2c145924e3cc6");
		Response response=	ErpApiUtil.commomHttpMethod(url, param, null, null, "get");
		System.out.println(response.getResponseText());
	}
	
	
	public static void downloadRoom(){
		String url ="http://113.105.74.140:8092/room/list/his";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("access_token", access_token);
		param.put("type", "0");
		param.put("pageIndex", "0");
		param.put("pageSize", "200");// 给一个尽量大的值
		Response response=	ErpApiUtil.commomHttpMethod(url, param, null, null, "get");
		System.out.println(response.getResponseText());
	}

/*	public static void getChatImageUrl() {
		String url ="	http://113.105.74.140:8081/";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("access_token", access_token);
		param.put("roomId", "574f907e0cf2c145924e3cc6");
		Response response=	ErpApiUtil.commomHttpMethod(url, param, null, null, "get");
		System.out.println(response.getResponseText());
	}*/

	public static void printlnDate() {
		System.out.println(DateFormatUtil.getFormatDate(1464832126));
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.valueOf("1464832126"+"000"))));
		System.out.println(timeStamp2Date("1464832126", "yyyy-MM-dd HH:mm:ss"));//20160602094858
	}

	public static void getChatPhotoId() {
		String url ="http://113.105.74.140:8092/room/getRelationGroupPhoto";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("access_token", access_token);
		param.put("roomId", "574f907e0cf2c145924e3cc6");
		Response response=	ErpApiUtil.commomHttpMethod(url, param, null, null, "get");
		System.out.println(response.getResponseText());
	}
	
	public static void setChatPhotoId() {
		String url ="http://113.105.74.140:8092/room/setRelationGroupPhoto";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("access_token", access_token);
		param.put("roomId", "58c1be5cb8784dcebd07eb2c5b161c02");
		System.out.println("id="+UUID.randomUUID().toString());
		param.put("photoid","101010");
		Response response=	ErpApiUtil.commomHttpMethod(url, param, null, null, "get");
		System.out.println(response.getResponseText());
	}

	
	
	public static Response getChatList() {
		String url ="http://113.105.74.140:8092/room/list/his";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("access_token", access_token);
		param.put("pageSize", "30");
		param.put("pageIndex", "0");
		Response response=	ErpApiUtil.commomHttpMethod(url, param, null, null, "get");
		System.out.println(response.getResponseText());
		return response;
	}
	
	 public static String timeStamp2Date(String  seconds,String format) {  
	        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
	            return "";  
	        }  
	        if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";  
	        SimpleDateFormat sdf = new SimpleDateFormat(format);  
	        return sdf.format(new Date(Long.valueOf(seconds+"000")));  
	 } 


}
