package com.application.base;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.application.http.httpclient.StaticUtil;
import com.application.util.HttpUtil.Response;

public class SpringApiBase {
	/**
	 * @author Administrator
	 * @功能:响应函数 HttpClient方式
	 */
	public static Response load(String url, Map<String, Object> param, LinkedHashMap<String, Object> headers,
			String bodyJson, String post) {
		Response response = StaticUtil.commomHttpMethod(url, param, headers, bodyJson, post);
		System.out.println("--------------------------------------------------------------");
		System.out.println(url);
		System.out.println(response.getResponseText());
		System.out.println(response.getStatusCode());
		System.out.println("--------------------------------------------------------------");
		return response;
	}

	/**
	 * @author Administrator
	 * @功能:响应函数 HttpClient方式
	 */
	public static Response load() {
		Response response = StaticUtil.commomHttpMethod(Method.url, Method.param, Method.headers, Method.body,
				Method.method);
		System.out.println(Method.url);
		System.out.println(response.getResponseText());
		System.out.println(response.getStatusCode());
		return response;
	}

	
	/**
	 * @author Administrator
	 * @功能:响应函数 HttpClient方式
	 */
	public static Response load(HttpEntity httpEntity) {
		Response response = StaticUtil.commomHttpMethod(
				httpEntity.getUrl(), httpEntity.getParam(), httpEntity.getHeaders(),httpEntity.getBody(),
				httpEntity.getMethod());
		System.out.println(Method.url);
		System.out.println(response.getResponseText());
		System.out.println(response.getStatusCode());
		return response;
	}
	
	
	public static class Method {
		public static String method;
		public static String body;
		public static String url = "http://127.0.0.1:8080/spring-mvc-showcase/";
		public static Map<String, Object> param = new HashMap<String, Object>();
		public static LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
	}

	public class HttpEntity {
		public String method="get";
		public String body;
		public String url;
		public Map<String, Object> param = new HashMap<String, Object>();
		public LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
		
		public HttpEntity(String url,Map<String, Object> param){
			this.url=url;
			this.param=param;
		}
		
		public void startHttpTask(){
			load(this);
		}

		public String getMethod() {
			return method;
		}

		public void setMethod(String method) {
			this.method = method;
		}

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public Map<String, Object> getParam() {
			return param;
		}

		public void setParam(Map<String, Object> param) {
			this.param = param;
		}

		public LinkedHashMap<String, Object> getHeaders() {
			return headers;
		}

		public void setHeaders(LinkedHashMap<String, Object> headers) {
			this.headers = headers;
		}

	}
}
