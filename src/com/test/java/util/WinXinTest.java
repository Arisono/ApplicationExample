package com.test.java.util;

import java.util.HashMap;
import java.util.Map;




import com.alibaba.fastjson.JSONObject;
import com.test.java.base.HttpUtil.Response;



public class WinXinTest {

	public static void main(String[] args) {
		String access_token = getAccessToken();
		pushAMessage(access_token);
		WeixinAddGroup(access_token);
	}
	
	/**
	 * 重新获取access_token
	 * @return
	 */
	private static String getAccessToken() {
		String access_token = "";
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("grant_type", "client_credential");
			params.put("appid", "wxbc1f8607137d3b8a");
			params.put("secret", "f34867da7b8b70f92e6e4789d7016b26");
			Response response = com.test.java.base.HttpUtil.sendGetRequest(
					"https://api.weixin.qq.com/cgi-bin/token",
					params);
			System.out.println(response.getStatusCode());
			System.out.println(response.getResponseText());
			Map<String, String> result = FlexJsonUtil.fromJson(response.getResponseText());
			access_token = result.get("access_token");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return access_token;
	}
	
	/**
	 * 定向向用户发送文本信息
	 * @param access_token
	 */
	private static void pushAMessage(String access_token) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("touser", "o8lZ9uGnn074M2wiP_5cWsZ3NL8s");
			params.put("msgtype", "text");
			JSONObject object = new JSONObject();
			object.put("content", "你好啊");
			params.put("text", object);
			System.out.println("json="+FlexJsonUtil.toJson(params));
			Response response = com.test.java.base.HttpUtil.sendPostRequest(
					"https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + access_token,
					params, false, false);
			System.out.println(response.getStatusCode());
			System.out.println(response.getResponseText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
     public static void getAllServers(String access_token) {
		try {
			Response response =com.test.java.base.HttpUtil.sendGetRequest(
					"https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=" + access_token,
					null);
			System.out.println(response.getStatusCode());
			System.out.println(response.getResponseText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
     
     
     /**
 	 * @author LiuJie
 	 * @功能:微信添加分组
 	 */
 	public static void WeixinAddGroup(String token){
 		String url="https://api.weixin.qq.com/cgi-bin/groups/create?access_token="+token;
 		Map<String, Object> param = new HashMap<String, Object>();
 		JSONObject object=new JSONObject();
 		object.put("name", "usoftchina");
 		param.put("group", object);
 		try {
 			Response  response =com.test.java.base.HttpUtil.sendPostRequest(url, param,false,false);
 			System.out.println("result:"+response.getResponseText());
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 	}
}
