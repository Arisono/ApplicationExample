package com.test.java.util;

import java.util.HashMap;
import java.util.Map;

import com.test.java.util.HttpUtil;
import com.test.java.util.HttpUtil.Response;
/**
 * @author :LiuJie 2015年8月25日 下午2:40:14
 * @注释:  测试Http请求的类
 */
public class TestHttpMain {

	public static void main(String[] args) {
		// String
		// url="http://218.17.158.219:9090/platform-manage/public/account";
		// Map<String, String> param =new HashMap<String, String>();
		// param.put("user", "13266699268");
		// param.put("password", "111111");

		// String url =
		// "http://218.17.158.219:9090/platform-b2b/j_spring_security_check";
		// Map<String, String> params = new HashMap<String, String>();
		// params.put("j_username", "200040149");
		// params.put("j_password", "123456");

		String url = "http://218.17.158.219:9090/platform-b2b/j_spring_security_check";
		Map<String, String> params = new HashMap<String, String>();
		params.put("j_username", "200040149");
		params.put("j_password", "123456");

		try {
			Response response = HttpUtil.sendPostRequest(url, params, true);
			// Response response=HttpUtil.sendGetRequest(url, params, true);
			System.out.println("" + response.getStatusCode());
			System.out.println("" + response.getResponseText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
