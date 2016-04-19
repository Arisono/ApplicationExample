package com.application.api.news;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.application.constans.Constans;
import com.application.entity.NewEntity;
import com.application.entity.PageBean;
import com.application.entity.PageBeanBody;
import com.application.entity.RootEntity;
import com.application.http.httpclient.StaticUtil;
import com.application.util.HttpUtil.Response;

public class NewsMain<T> {
	private static Map<String, Object> param = new HashMap<String, Object>();
	private static LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
	private static String url = Constans.API_NEWS;
	
	public static void main(String[] args) {
		getNewsList(1, "国内最新");
	}

	public static void getNewsList(int page,String channelName) {
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
		loadNewsList(url, param, headers, null, "get");
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
	public static void loadNewsList(String url, Map<String, Object> param, LinkedHashMap<String, Object> headers,
			String bodyJson, String post) {
		Response response = StaticUtil.commomHttpMethod(url, param, headers, bodyJson, post);
//		System.out.println(response.getStatusCode());
		System.out.println(response.getResponseText());
//		parseNewsList(response);
	RootEntity<PageBeanBody<NewEntity>> data=	parseNewsList(response, NewEntity.class);
	System.out.println(data.getShowapi_res_body().getPagebean().getContentlist().get(0).getChannelName());
	}

	public static <T> RootEntity<PageBeanBody<T>> parseNewsList(Response response,Class<T> mClazz) {
		//JSONObject root= JSON.parseObject(response.getResponseText());
		//NewsListEntity newsList = JSON.parseObject(response.getResponseText(), NewsListEntity.class);
		//System.out.println("新闻数据："+newsList.getShowapi_res_body().getPagebean().getContentlist().size());
		//List<Contentlist> clist = newsList.getShowapi_res_body().getPagebean().getContentlist();
		//System.out.println(clist.get(0).getDesc());
		RootEntity<PageBeanBody<T>> rootEntity=new RootEntity<PageBeanBody<T>>();
	    String json=JSON.parseObject(response.getResponseText()).getJSONObject("showapi_res_body")
		.getJSONObject("pagebean").getJSONArray("contentlist").toJSONString();
		List<T> lists=JSON.parseArray(json, mClazz);
	    rootEntity.getShowapi_res_body().getPagebean().
	    setContentlist(lists);
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
