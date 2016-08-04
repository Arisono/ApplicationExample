package com.application.okhttp;

import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import com.alibaba.fastjson.JSON;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author Arison 参考文档： https://github.com/square/okhttp/wiki/Recipes
 */
public class OkhttpUtilsMain {

	public static void main(String[] args) {

	}

	/**
	 * @desc:post json数据提交   Header+params+json
	 */
	@SuppressWarnings("deprecation")
	public static void sendHeadersAndJSON() {

		// 表单提交 这种能满足大部分的需求
		RequestBody formBody = new FormBody.Builder()
				.add("jsonData", "{\"data\":\"121\",\"data1\":\"2232\"}")
				.add("username", "Arison+中文").add("password", "1111111")
				.build();

		String postBody = "{\"type\":\"post json提交\"}";
		String postBody2 = "{\"type2\":\"post json提交\"}";
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("http://localhost:8080/spring-mvc-showcase/api/getHeaders")
				.header("cookie", "JSESSIONID=EB36DE5E50E342D86C55DAE0CDDD4F6D")
				.addHeader("content-type", "application/json;charset:utf-8")
				.addHeader("Home", "china")// 自定义的header
				.addHeader("user-agent", "android")
				// .post(RequestBody.create(MEDIA_TYPE_TEXT, postBody))
				.post(formBody)
				// 表单提交
				.put(RequestBody.create(
						MediaType.parse("application/json; charset=utf-8"),
						postBody))// post json提交
				.put(RequestBody.create(
						MediaType.parse("application/json; charset=utf-8"),
						postBody2))// post json提交
				.build();
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				String json = response.body().string();
				System.out.println(json);
				String post = JSON.parseObject(json).getString("postBody");
				System.out.println("转义之前：" + post);
				System.out.println("转义之后：" + URLDecoder.decode(post));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @desc:发送请求头以及请求参数 Header+params
	 */
	public static void sendHeadersAndParams() {
		String china_str = "";
		try {
			china_str = URLEncoder.encode("中文", "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		// 表单提交
		RequestBody formBody = new FormBody.Builder().add("query", "Hello")
				.add("username", "Arison").add("password", "1111111").build();
		// 第二个表单会覆盖第一个
		/*
		 * RequestBody formBody2 = new FormBody.Builder() .add("search",
		 * "Jurassic Park") .build();
		 */
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("http://localhost:8080/spring-mvc-showcase/api/getHeaders")
				.header("cookie", "JSESSIONID=EB36DE5E50E342D86C55DAE0CDDD4F6D")
				.addHeader("content-type", "text/html;charset:utf-8")
				.addHeader("Home", "china")// 自定义的header
				.addHeader("Home1", china_str)// 自定义的header 传中文
				.addHeader("user-agent", "android")
				// .post(RequestBody.create(MEDIA_TYPE_TEXT, postBody))
				.post(formBody)
				// .post(formBody2)
				.build();
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				String json = response.body().string();
				System.out.println(json);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @desc:发送请求头
	 */
	public static void sendHeaders() {
		String china_str = "";
		try {
			china_str = URLEncoder.encode("中文", "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("http://localhost:8080/spring-mvc-showcase/api/getHeaders")
				.header("cookie", "JSESSIONID=EB36DE5E50E342D86C55DAE0CDDD4F6D")
				.addHeader("content-type", "text/html;charset:utf-8")
				.addHeader("Home", "china")// 自定义的header
				.addHeader("Home1", china_str)// 自定义的header 传中文
				.addHeader("user-agent", "android").build();
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				String json = response.body().string();
				System.out.println(json);
				String home1 = JSON.parseObject(json).getJSONObject("headers")
						.getString("home1");
				System.out.println(URLDecoder.decode(home1, "utf-8"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @dec 基本测试
	 * @throws IOException
	 */
	public static void sendBasicRequest() {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder().url("http://www.baidu.com")
				.build();

		try {
			Response response = client.newCall(request).execute();
			if (!response.isSuccessful()) {
				// throw new IOException("服务器端错误: " + response);
			}
			// 输入响应头
			Headers responseHeaders = response.headers();
			for (int i = 0; i < responseHeaders.size(); i++) {
				System.out.println(responseHeaders.name(i) + ": "
						+ responseHeaders.value(i));
			}
			// 输出响应实体
			// System.out.println(response.body().string());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
