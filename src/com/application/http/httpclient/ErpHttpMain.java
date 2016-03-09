package com.application.http.httpclient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;



import com.alibaba.fastjson.JSON;
import com.application.news.StaticUtil;
import com.application.util.Constans;
import com.application.util.HttpUtil;
import com.application.util.HttpUtil.Response;
/**
  * @author Administrator
  * @功能:uas 接口测试
  */
public class ErpHttpMain {
	public static Map<String, Object> param=new HashMap<String, Object>();
    public static LinkedHashMap<String , Object> headers=new LinkedHashMap<>();
    public static String url=Constans.API_NEWS;
    public static String sessionId;
    
	public static void main(String[] args) {
		//Constans.ERP_BASIC="http://xjxmy.saas.ubtob.com/";
		sessionId=	getCookieLogin("13352991628",
				 "az00213381",
				 "SAAS_10041495");
	   getAllHrorgEmps(Constans.ERP_GETALLHRORGEMPS,"SAAS_10041495","",sessionId);
	}

	public static void TestLogin(String phone, String password) {
		String url = "http://manage.ubtob.com/public/account";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("user", phone);
		param.put("password", password);
		try {
			Response response = HttpUtil.sendGetRequest(url, param, true);
			System.out.println("login 状态码：" + response.getStatusCode());
			System.out.println("login 正文：" + response.getResponseText());
		} catch (Exception e) {
			System.out.println("超时");
			System.out.println(e.getMessage());
		}
	}
	
	
	public static String getCookieLogin(String phone,String password,String master) {
		Response response=getERPLogin(phone, password, master);//标准版
		sessionId=JSON.parseObject(response.getResponseText()).getString("sessionId");
		return sessionId;
	}
	
 
	public static Response getERPLogin(String phone,String password,String master) {
		cleardata();
		url=Constans.ERP_LOGIN;
        final Map<String,Object> param=new HashMap<>();
        param.put("username", phone);
		param.put("password", password);
		param.put("master", master);
       return loadNewsList(url, param, headers, null, "get");
	}
	
	@SuppressWarnings("unused")
	public static void getERPNewsList(String sessionId) {
		cleardata();
		url=Constans.ERP_NEWS;
        final Map<String,Object> param=new HashMap<>();
        param.put("caller", "News");
        param.put("sessionId", sessionId);
        param.put("page", "1");
        param.put("pageSize", "100");
        param.put("condition", "1=1");
        headers.put("Cookie", "JSESSIONID="+sessionId);
        loadNewsList(url, param, headers, null, "get");
	}
	
	/**
	  * @author Administrator
	  * @功能:获取组织架构全部信息
	  */
	public static Response getAllHrorgEmps(String url,String master,String date,String sessionId) {
		cleardata();
        final Map<String,Object> param=new HashMap<>();
        String datastr=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        System.out.println(datastr);
        param.put("master", master);
        param.put("lastdate",date);
        headers.put("Cookie", "JSESSIONID="+sessionId);
        return loadNewsList(url, param, headers, null, "get");
	}

	@SuppressWarnings("unused")
	public static void getERPNoticesDetail(int id) {
		cleardata();
		url=Constans.ERP_NOTICES_DETAIL;
        final Map<String,Object> param=new HashMap<>();
        param.put("caller", "News");
        param.put("id", id);
        headers.put("Cookie", "JSESSIONID="+sessionId);
        loadNewsList(url, param, headers, null, "get");
	}
	
	@SuppressWarnings("unused")
	public static void getERPNoticesList(String sessionId) {
		cleardata();
		url=Constans.ERP_NEWS;
        final Map<String,Object> param=new HashMap<>();
        param.put("caller", "Note");
        param.put("sessionId", sessionId);
        param.put("page", "1");
        param.put("pageSize", "100");
        //param.put("condition", "NO_INFOTYPE='TZ'");
        param.put("condition", "NO_INFOTYPE='TZ'");
        headers.put("Cookie", "JSESSIONID="+sessionId);
        loadNewsList(url, param, headers, null, "get");
	}
	
	@SuppressWarnings("unused")
	public static void getERPNewsDetail(String id,String sessionId) {
		cleardata();
		url=Constans.ERP_NEWS_DETAIL;
        final Map<String,Object> param=new HashMap<>();
        param.put("caller", "News");
        param.put("id", id);
        headers.put("Cookie", "JSESSIONID="+sessionId);
        loadNewsList(url, param, headers, null, "get");
	}
	
	/**
	  * @author Administrator
	  * @功能:下载附件
	  */
	public static void getERPDownload(String id,String sessionId) {
		cleardata();
		url=Constans.ERP_DOWNLOAD_ID;
        final Map<String,Object> param=new HashMap<>();
        param.put("id", id);
        headers.put("Cookie", "JSESSIONID="+sessionId);
       //loadNewsList(url, param, headers, null, "get");
        System.out.println(url+"?id="+id);
        HttpUtil.download(url+"?id="+id, "C:/Users/Administrator/Desktop/"+id+".amr");
	}
	
	/**
	  * @author Administrator
	  * @功能:响应函数
	  */
	public static Response loadNewsList(String url, Map<String, Object> param, LinkedHashMap<String, Object> headers,
			String bodyJson, String post){
	  Response response=StaticUtil.commomHttpMethod(url, param, headers, bodyJson, post);
	  System.out.println("--------------------------------------------------------------");
	  System.out.println(response.getResponseText());
	  System.out.println(response.getStatusCode());
	  System.out.println(url);
	 // System.out.println("-- "+response.getCookieStore());
	  System.out.println("--------------------------------------------------------------");
	  return response;
	}
	
	/**
	  * @author Administrator
	  * @功能:清空全局变量的值
	  */
	public static  void cleardata(){
		param.clear();
		headers.clear();
	}

}
