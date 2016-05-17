package com.application.api.news;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.application.api.erp.ErpApiUtil;
import com.application.constans.Constans;
import com.application.entity.PageBean;
import com.application.entity.PageBeanBody;
import com.application.entity.RootEntity;
import com.application.util.HttpUtil.Response;

public class NewsMain<T> {
	private static Map<String, Object> param = new HashMap<String, Object>();
	private static LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
	private static String url = Constans.API_NEWS;
	
	public static void main(String[] args) {
		getNewsList(1, "国内最新");
	}

	public static Response getNewsList(int page,String channelName) {
		cleardata();
		url = Constans.API_NEWS;
		String datetime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		final Map<String, Object> param = new HashMap<>();
		param.put("showapi_appid", "12041");
		param.put("showapi_sign", "67f7892db890407f95cdf39f870b1234");
		param.put("showapi_timestamp", datetime);
		param.put("page", page);
		// param.put("channelId", value);
		param.put("channelName", channelName);
		return loadNewsList(url, param, headers, null, "get");
	}

	public static void getNewsChannel() {
		cleardata();
		url = Constans.API_NEWS_CHANNEL;
		String datetime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		final Map<String, Object> param = new HashMap<>();
		param.put("showapi_appid", "12041");
		param.put("showapi_sign", "67f7892db890407f95cdf39f870b1234");
		param.put("showapi_timestamp", datetime);
		loadNewsList(url, param, headers, null, "get");
	}

	/**
	 * @author Administrator
	 * @功能:解析新闻列表
	 */
	public static Response loadNewsList(String url, Map<String, Object> param, LinkedHashMap<String, Object> headers,
			String bodyJson, String post) {
		Response response = ErpApiUtil.commomHttpMethod(url, param, headers, bodyJson, post);
        return response;
	}


	public static <T> RootEntity<T> parseNewsList(Response response,Class<T> mClazz) {
		JSONObject root=JSON.parseObject(response.getResponseText());
		JSONObject body=JSON.parseObject(response.getResponseText()).getJSONObject("showapi_res_body");
		JSONObject page=JSON.parseObject(response.getResponseText()).getJSONObject("showapi_res_body").getJSONObject("pagebean");
	    String contentList=JSON.parseObject(response.getResponseText()).getJSONObject("showapi_res_body").getJSONObject("pagebean").getJSONArray("contentlist").toJSONString();
	    //实例化分页类
	    PageBean<T> pageBean=new PageBean<T>();
	    pageBean.setContentlist(JSON.parseArray(contentList, mClazz));//可能报错
	    pageBean.setAllNum(page.getIntValue("allNum"));
	    pageBean.setAllPages(page.getIntValue("allPages"));
	    pageBean.setCurrentPage(page.getIntValue("currentPage"));
	    pageBean.setMaxResult(page.getIntValue("maxResult"));
	    //实例化Body类
	    PageBeanBody<T> pageBeanBody=new PageBeanBody<T>();
	    pageBeanBody.setPagebean(pageBean);
	    pageBeanBody.setRet_code(body.getIntValue("ret_code"));
	    //实例化Root类
	    RootEntity<T> rootEntity=new RootEntity<T>();
	    rootEntity.setShowapi_res_body(pageBeanBody);
	    rootEntity.setShowapi_res_code(root.getIntValue("showapi_res_code"));
	    rootEntity.setShowapi_res_error(root.getString("showapi_res_error"));
	    return rootEntity;
	}

	/**
	 * @author Administrator
	 * @功能:清空全局变量的值
	 */
	public static void cleardata() {
		param.clear();
		headers.clear();
	}

}
