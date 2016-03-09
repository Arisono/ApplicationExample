package com.application.news;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.client.methods.HttpGet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.application.entity.ExtraWorkItems;
import com.application.entity.LeaveEntity;
import com.application.entity.LoginEntity;
import com.application.entity.NewsChannel;
import com.application.entity.Weather;
import com.application.entity.ExtraWork.items;
import com.application.util.Constans;
import com.application.util.FlexJsonUtil;
import com.application.util.HttpUtil;
import com.application.util.HttpUtil.Response;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

/**
 * @author LiuJie
 *
 */
public class StaticUtil {

	
	public static void main(String[] args) {
		TestLogin("13266699268", "1");
		//TestLoginERP("13510568818", "111111", "USOFTSYS");
		//TestLoginB2B("13671962436", "111111");
	}

	public static String token = null;
	/** @注释：新闻 */
	public static void getNews() {
		String url = Constans.API_NEWS;
		String datatime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		System.out.println("datatime:" + datatime);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("showapi_appid", "12041");
		param.put("showapi_sign", "67f7892db890407f95cdf39f870b1234");
		param.put("showapi_timestamp", datatime);
		commomHttpMethod(url, param, null, null, "get");
		commomHttpMethod("http://route.showapi.com/109-34", param, null, null, "get");
	}

	/**
	 * @author LiuJie
	 * @功能:获取视频接口 body json 参数的传递
	 */
	public static void getVideo() {
		String url = Constans.API_VIDEO;
		LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
		headers.put("apikey", "da8115408ed5b6a3c28034276f7ae577");
		String body = "{\"query\":\"虎妈猫爸的最新剧集\",\"resource\":\"video_haiou\"}";
		commomHttpMethod(url, null, headers, body, "post");
	}

	/**
	 * @author LiuJie
	 * @功能:获取音乐文件接口
	 */
	public static void getMusicInfo() {
		String url = Constans.API_MUSIC;
		LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
		headers.put("apikey", "da8115408ed5b6a3c28034276f7ae577");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("s", "蔡依林");
		param.put("limit", 100);
		param.put("p", 1);
		commomHttpMethod(url, param, headers, null, "get");
	}

	/**
	 * @author LiuJie
	 * @功能:天狗图片 天狗接口中心
	 */
	public static void getGallery() {
		String url = Constans.API_TIANGOU_GALLERY;
		LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
		headers.put("apikey", "da8115408ed5b6a3c28034276f7ae577");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", 0);
		param.put("rows", 10);
		param.put("classify", 1);
		commomHttpMethod(url, param, headers, null, "get");
	}

	/**
	  * @author Administrator
	  * @功能:公用方法
	  */
	public static Response commomHttpMethod(String url, Map<String, Object> param, LinkedHashMap<String, Object> headers,
			String bodyJson, String post) {
		Response response=null;
		try {
			if ("get".equals(post)) {
				 response = HttpUtil.sendGetHeaderRequest(url, param, headers, false);
//				System.out.println("status:" + response.getStatusCode());
//				System.out.println("body:" + response.getResponseText());
			}
			if ("post".equals(post)) {
				 response = HttpUtil.sendPostJsonRequest(url, param, headers, bodyJson, false);
//				System.out.println("status:" + response.getStatusCode());
//				System.out.println("body:" + response.getResponseText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * @author LiuJie
	 * @功能:获取微信精选文章
	 */
	public static void getWxhot_huceo() {
		String url = Constans.API_WXHOT_HUCEO;
		LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
		headers.put("key", "209c5fb3f2ba9349bdb779e1d982b412");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("num", 10);
		param.put("rand", 1);
		param.put("word", "中国");
		param.put("page", 1);
		param.put("key", "209c5fb3f2ba9349bdb779e1d982b412");
		try {
			Response response = HttpUtil.sendGetHeaderRequest(url, param, null, false);
			System.out.println(response.getResponseText());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @author LiuJie
	 * @功能:获取微信精选文章
	 */
	public static void getWxhot_baidu() {
		String url = Constans.API_WXHOT;
		LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
		headers.put("apikey", "da8115408ed5b6a3c28034276f7ae577");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("num", 10);
		param.put("rand", 1);
		param.put("word", "中国");
		param.put("page", 1);
		try {
			Response response = HttpUtil.sendGetHeaderRequest(url, param, headers, false);
			System.out.println(response.getResponseText());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @author LiuJie
	 * @功能:获取身份证信息
	 */
	public static void getIdService() {
		String url = Constans.API_IDSERVICE + "36220219910812301X";
		LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
		headers.put("apikey", "da8115408ed5b6a3c28034276f7ae577");
		try {
			Response response = HttpUtil.sendGetHeaderRequest(url, null, headers, false);
			System.out.println("body:" + response.getResponseText());
			JSONObject object = JSON.parseObject(response.getResponseText());
			System.out.println("retMsg:" + object.getString("retMsg"));
			System.out.println("errNum:" + object.getIntValue("errNum"));
			JSONObject retData = object.getJSONObject("retData");
			System.out.println("address:" + retData.getString("address"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author LiuJie
	 * @功能:天气接口
	 */
	public static void getCityWether() {
		String url = "http://apis.baidu.com/heweather/weather/free";
		LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
		headers.put("apikey", "da8115408ed5b6a3c28034276f7ae577");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("city", "深圳");
		try {
			Response response = HttpUtil.sendGetHeaderRequest(url, params, headers, false);
			System.out.println("状态码：" + response.getStatusCode());
			System.out.println("正文：" + response.getResponseText());
			JSONObject object = JSON.parseObject(response.getResponseText());
			JSONArray array = (JSONArray) object.get("HeWeather data service 3.0");
			JSONObject jsObject = (JSONObject) array.get(0);
			Weather weather = JSON.parseObject(JSON.toJSONString(jsObject), Weather.class);
			System.out.println("--------------------------------------------");
			System.out.println("城市：" + weather.getBasic().getCity());
			System.out.println("今日天气状况：" + weather.getNow().getCond().getTxt());
			System.out.println("今日温度：" + weather.getNow().getTmp());
			System.out.println("当地时间：" + weather.getBasic().getUpdate().getLoc());

			System.out.println("明天白天天气：" + weather.getDaily_forecasts().get(1).getCond().getTxt_d());
			System.out.println("明天晚上天气：" + weather.getDaily_forecasts().get(1).getCond().getTxt_n());
			System.out.println("明天时间：" + weather.getDaily_forecasts().get(1).date);

			System.out.println("后天白天天气：" + weather.getDaily_forecasts().get(2).getCond().getTxt_d());
			System.out.println("后天晚上天气：" + weather.getDaily_forecasts().get(2).getCond().getTxt_n());
			System.out.println("后天时间：" + weather.getDaily_forecasts().get(2).date);
			System.out.println("--------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author LiuJie
	 * @功能:测试天气接口JavaBean
	 */
	public static void TestWeatherJavaBean() {
		Weather weather2 = JSON.parseObject("{\"daily_forecasts\":[{\"astro\":{\"sr\":\"898989\",\"ss\":\"88888\"}}]}",
				Weather.class);
		System.out.println(weather2.getDaily_forecasts().size());
		System.out.println(weather2.getDaily_forecasts());
	}

	/**
	 * @author LiuJie
	 * @功能:经过时间证明,Json解析不支持Java内部类
	 * 
	 */
	public static void TestFastjsonJavaBean() {

		NewsChannel group2 = JSON.parseObject(
				"{\"showapi_res_code\":0,\"showapi_res_error\":\"\",\"showapi_res_body\":{\"channelList\":[{\"channelId\":\"5572a108b3cdc86cf39001cd\",\"name\":\"国内焦点\"},{\"channelId\":\"5572a108b3cdc86cf39001ce\",\"name\":\"国际焦点\"},{\"channelId\":\"5572a108b3cdc86cf39001cf\",\"name\":\"军事焦点\"},{\"channelId\":\"5572a108b3cdc86cf39001d0\",\"name\":\"财经焦点\"},{\"channelId\":\"5572a108b3cdc86cf39001d1\",\"name\":\"互联网焦点\"},{\"channelId\":\"5572a108b3cdc86cf39001d2\",\"name\":\"房产焦点\"},{\"channelId\":\"5572a108b3cdc86cf39001d3\",\"name\":\"汽车焦点\"},{\"channelId\":\"5572a108b3cdc86cf39001d4\",\"name\":\"体育焦点\"},{\"channelId\":\"5572a108b3cdc86cf39001d5\",\"name\":\"娱乐焦点\"},{\"channelId\":\"5572a108b3cdc86cf39001d6\",\"name\":\"游戏焦点\"},{\"channelId\":\"5572a108b3cdc86cf39001d7\",\"name\":\"教育焦点\"},{\"channelId\":\"5572a108b3cdc86cf39001d8\",\"name\":\"女人焦点\"},{\"channelId\":\"5572a108b3cdc86cf39001d9\",\"name\":\"科技焦点\"},{\"channelId\":\"5572a109b3cdc86cf39001da\",\"name\":\"社会焦点\"},{\"channelId\":\"5572a109b3cdc86cf39001db\",\"name\":\"国内最新\"},{\"channelId\":\"5572a109b3cdc86cf39001dc\",\"name\":\"台湾最新\"},{\"channelId\":\"5572a109b3cdc86cf39001dd\",\"name\":\"港澳最新\"},{\"channelId\":\"5572a109b3cdc86cf39001de\",\"name\":\"国际最新\"},{\"channelId\":\"5572a109b3cdc86cf39001df\",\"name\":\"军事最新\"},{\"channelId\":\"5572a109b3cdc86cf39001e0\",\"name\":\"财经最新\"},{\"channelId\":\"5572a109b3cdc86cf39001e1\",\"name\":\"理财最新\"},{\"channelId\":\"5572a109b3cdc86cf39001e2\",\"name\":\"宏观经济最新\"},{\"channelId\":\"5572a109b3cdc86cf39001e3\",\"name\":\"互联网最新\"},{\"channelId\":\"5572a109b3cdc86cf39001e4\",\"name\":\"房产最新\"},{\"channelId\":\"5572a109b3cdc86cf39001e5\",\"name\":\"汽车最新\"},{\"channelId\":\"5572a109b3cdc86cf39001e6\",\"name\":\"体育最新\"},{\"channelId\":\"5572a10ab3cdc86cf39001e7\",\"name\":\"国际足球最新\"},{\"channelId\":\"5572a10ab3cdc86cf39001e8\",\"name\":\"国内足球最新\"},{\"channelId\":\"5572a10ab3cdc86cf39001e9\",\"name\":\"CBA最新\"},{\"channelId\":\"5572a10ab3cdc86cf39001ea\",\"name\":\"综合体育最新\"},{\"channelId\":\"5572a10ab3cdc86cf39001eb\",\"name\":\"娱乐最新\"},{\"channelId\":\"5572a10ab3cdc86cf39001ec\",\"name\":\"电影最新\"},{\"channelId\":\"5572a10ab3cdc86cf39001ed\",\"name\":\"电视最新\"},{\"channelId\":\"5572a10ab3cdc86cf39001ee\",\"name\":\"游戏最新\"},{\"channelId\":\"5572a10ab3cdc86cf39001ef\",\"name\":\"教育最新\"},{\"channelId\":\"5572a10ab3cdc86cf39001f0\",\"name\":\"女人最新\"},{\"channelId\":\"5572a10ab3cdc86cf39001f1\",\"name\":\"美容护肤最新\"},{\"channelId\":\"5572a10ab3cdc86cf39001f2\",\"name\":\"情感两性最新\"},{\"channelId\":\"5572a10ab3cdc86cf39001f3\",\"name\":\"健康养生最新\"},{\"channelId\":\"5572a10ab3cdc86cf39001f4\",\"name\":\"科技最新\"},{\"channelId\":\"5572a10bb3cdc86cf39001f5\",\"name\":\"数码最新\"},{\"channelId\":\"5572a10bb3cdc86cf39001f6\",\"name\":\"电脑最新\"},{\"channelId\":\"5572a10bb3cdc86cf39001f7\",\"name\":\"科普最新\"},{\"channelId\":\"5572a10bb3cdc86cf39001f8\",\"name\":\"社会最新\"}],\"ret_code\":0,\"totalNum\":44}}",
				NewsChannel.class);
		System.out.println(group2.getShowapi_res_body().getChannelList().size());
	}

	/**
	 * @author LiuJie
	 * @功能:计算日利息
	 */
	public static Double TestMoneyAccrual(Double total, Double days, Double rate) {
		Double accrual = total * (rate / 365 / 100) * days;
		System.out.println("日利率：" + rate / 365 / 100);
		System.out.println("利息：" + accrual);
		DecimalFormat df = new DecimalFormat("#,##0.##;(#)");
		System.out.println(df.format(total + accrual));
		return accrual;
	}

	/**
	 * @return
	 */
	public static boolean isCheckDateTime(String start, String end, String format) {
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = new SimpleDateFormat(format).parse(start);
			endDate = new SimpleDateFormat(format).parse(end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		boolean falg = endDate.before(startDate);
		return falg;
	}

	public static int compare_date(String DATE1, String DATE2, String format) {
		DateFormat df = new SimpleDateFormat(format);
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				System.out.println("dt1 在dt2前");
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				System.out.println("dt1在dt2后");
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/**
	 * @author LiuJie
	 * @功能 执行微信方法
	 */
	public static void excuteWeixin() {
		if (token == null) {
			token = WeixinGetToken();
		}
		if (!StringUtils.isEmpty(token)) {
			WeiXinSendModeMessage(token);
		}
	}

	/**
	 * @author LiuJie
	 * @功能:发送模板消息-微信
	 */
	public static void WeiXinSendModeMessage(String token) {
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send" + "?access_token=" + token;
		Map<String, Object> param = new HashMap<String, Object>();
		JSONObject object = new JSONObject();
		param.put("touser", "o8lZ9uGnn074M2wiP_5cWsZ3NL8s");
		param.put("template_id", "573gHAuLVj-CDh1ztVZtb8fmcQoPmlXg2uEtFpHBglg");
		param.put("url", "http://www.baidu.com");
		JSONObject object_first = new JSONObject();
		object_first.put("value", "请查阅您的待办事宜");
		object_first.put("color", "#173177");
		object.put("first", object_first);
		JSONObject object_value = new JSONObject();
		object_value.put("value", 12);
		object_value.put("color", "#173177");
		object.put("keynote", object_value);
		// object.put("keynote2", "恭喜你，购买成功！");
		// object.put("keynote3", "恭喜你，购买成功！");
		JSONObject object_remark = new JSONObject();
		object_remark.put("value", "您有疑问请咨询我们的客服");
		object_remark.put("color", "#173177");
		object.put("remark", object_remark);
		param.put("data", object);
		System.out.println("json:" + FlexJsonUtil.toJson(param));
		try {
			Response response = HttpUtil.sendHttpsPostRequest(url, param, false);
			System.out.println(response.getResponseText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void WeixinSendUser(String token) {
		String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + token;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("touser", "o8lZ9uGnn074M2wiP_5cWsZ3NL8s");
		param.put("msgtype", "image");
		// param.put("content", "{\"content\":\"Hello World By LiuJ Java.\"}");
		JSONObject object = new JSONObject();
		object.put("media_id", "1.png");
		param.put("image", object);
		// param.put("",
		// "{\"touser\":\"o8lZ9uK_9unAbInnlhmcbomhTmZ4\",\"msgtype\":\"text\",\"text\":{\"content\":\"Hello
		// World\"}}");
		try {
			Response response = HttpUtil.sendHttpsPostRequest(url, param, false);
			System.out.println(response.getResponseText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author LiuJie
	 * @功能:user info
	 */
	public static void WeixinGetUseInfo(String token) {
		String url = "https://api.weixin.qq.com/cgi-bin/user/info";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("access_token", token);
		param.put("lang", "zh_CN");
		param.put("openid", "o8lZ9uK0iLRfyLqYe79VBRv7FxgU");
		try {
			Response response = HttpUtil.sendHttpsPostRequest(url, param, false);
			System.out.println(response.getResponseText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author LiuJie
	 * @功能:user list
	 */
	public static void WeixinGetUser(String token) {
		String url = "https://api.weixin.qq.com/cgi-bin/user/info/batchget";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("access_token", token);
		try {
			Response response = HttpUtil.sendHttpsPostRequest(url, param, false);
			System.out.println(response.getResponseText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author LiuJie
	 * @功能:获取token
	 */
	public static String WeixinGetToken() {
		String url = "https://api.weixin.qq.com/cgi-bin/token";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("grant_type", "client_credential");
		param.put("appid", "wxbc1f8607137d3b8a");
		param.put("secret", "f34867da7b8b70f92e6e4789d7016b26");
		try {
			Response response = HttpUtil.sendGetRequest(url, param, false);
			Map<String, Object> rMap = FlexJsonUtil.fromJson(response.getResponseText());
			System.out.println("token:" + rMap.get("access_token").toString());
			return rMap.get("access_token").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author LiuJie
	 * @功能:设置行业模块
	 */
	public static void WeixinSetIndustry(String token) {
		String url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("access_token", token);
		param.put("name", "{\"group\":{\"name\":\"usoftchina\"}}");
		try {
			Response response = HttpUtil.sendHttpsPostRequest(url, param, false);
			System.out.println(response.getResponseText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author LiuJie
	 * @功能:微信添加分组
	 */
	public static void WeixinAddGroup(String token) {
		String url = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=" + token;
		Map<String, Object> param = new HashMap<String, Object>();
		JSONObject object = new JSONObject();
		object.put("name", "leader");
		param.put("group", object);
		try {
			com.application.base.HttpUtil.Response response = com.application.base.HttpUtil.sendPostRequest(url, param,
					false, false);
			System.out.println("result:" + response.getResponseText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void WeixinDeteGroup(String token) {
		String url = "https://api.weixin.qq.com/cgi-bin/groups/delete" + "?access_token=" + token;
		Map<String, Object> param = new HashMap<String, Object>();
		JSONObject object = new JSONObject();
		object.put("id", 101);
		param.put("group", object);
		System.out.println("json:" + FlexJsonUtil.toJson(param));
		try {
			com.application.base.HttpUtil.Response response = com.application.base.HttpUtil.sendPostRequest(url, param,
					false, false);
			System.out.println(response.getResponseText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void WeixinUpdateGroup(String token) {
		String url = "https://api.weixin.qq.com/cgi-bin/groups/update" + "?access_token=" + token;
		Map<String, Object> param = new HashMap<String, Object>();
		JSONObject object = new JSONObject();
		object.put("id", 102);
		object.put("name", "实施部");
		param.put("group", object);
		System.out.println("json:" + FlexJsonUtil.toJson(param));
		try {
			com.application.base.HttpUtil.Response response = com.application.base.HttpUtil.sendPostRequest(url, param,
					false, false);
			System.out.println(response.getResponseText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void WeixinGetGroup(String token) {
		String url = "https://api.weixin.qq.com/cgi-bin/groups/get" + "?access_token=" + token;
		Map<String, Object> param = new HashMap<String, Object>();
		// param.put("access_token", token);
		try {
			Response response = HttpUtil.sendHttpsPostRequest(url, param, false);
			System.out.println(response.getResponseText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author LiuJie
	 * @功能:自定义菜单
	 */
	public static void WeixinAddMenu(String token) {
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("access_token", token);
		param.put("body",
				"{\"button\":[{\"type\":\"click\",\"name\":\"今日电影\",\"key\":\"V1001_TODAY_MUSIC\"},{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"view\",\"name\":\"搜索\",\"url\":\"\"},{\"type\":\"view\",\"name\":\"视频\",\"url\":\"\"},{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\"}]}]}");
		try {
			Response response = HttpUtil.sendHttpsPostRequest(url, param, false);
			System.out.println(response.getResponseText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author LiuJie
	 * @功能:获取自定义菜单
	 */
	public static void WeixinGetMenu(String token) {
		String url = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("access_token", token);
		try {
			Response response = HttpUtil.sendPostRequest(url, param, false);
			System.out.println(response.getResponseText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("null")
	public static void TestException() {
		try {
			Integer i = null;
			System.out.println(i.toString());
			System.out.println(i);
		} catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter pw = new PrintWriter(writer);
			e.printStackTrace(pw);
			pw.close();
			String error = writer.toString();
			System.out.println(error);
		}
	}

	public static String getStackMsg(Throwable e) {
		StringBuffer sb = new StringBuffer();
		StackTraceElement[] stackArray = e.getStackTrace();
		for (int i = 0; i < stackArray.length; i++) {
			StackTraceElement element = stackArray[i];
			sb.append(element.toString() + "\n");
		}
		return sb.toString();
	}

	public static String getStackMsgDetail(Throwable e, boolean isfilter) {
		StringBuffer sb = new StringBuffer();
		/*
		 * Throwable [] ecause =e.getSuppressed(); for (int i = 0; i <
		 * ecause.length; i++) {
		 * System.out.println("cause:"+ecause[i].getMessage()); }
		 */
		StackTraceElement[] stackArray = e.getStackTrace();
		sb.append("--------------------异常共  " + stackArray.length + " 条----------------------------------\n");
		for (int i = stackArray.length - 1; i >= 0; i--) {
			StackTraceElement element = stackArray[i];
			if (isfilter) {
				if (element.getLineNumber() == -1)
					continue;
			}
			sb.append("-------------------------------------------------------\n");
			sb.append("classname:   " + element.getClassName() + "\n");
			sb.append("   method:   " + element.getMethodName() + "()\n");
			sb.append("     line:   " + element.getLineNumber() + "\n");
			sb.append("--------------------------------------------------------\n");
		}
		return sb.toString();
	}

	/**
	 * @author LiuJie
	 * @功能:支持浮点数正整数的正则表达式
	 */
	public static void TestJavaRegx() {
		// 电子邮件
		// String check =
		// "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern p3 = Pattern.compile("^(([0-9])|([0-9]+\\.?[0-9]+))$");
		Matcher m3 = p3.matcher("5555");
		while (m3.find()) {
			System.out.println(m3.group());
		}
	}

	@SuppressWarnings("unchecked")
	public static void TestJsonShuZhu() {
		String jsonString = "{\"combdatas\":[\"一般请购\",\"成品机及广告物料请购\",\"风险采购\",\"手机关键料风险备料\",\"MRP\",\"手机关键料请购\",\"辅料\",\"试产物料\"]}";
		List<String> list = new ArrayList<String>();
		if (FlexJsonUtil.fromJson(jsonString).get("combdatas") instanceof List<?>) {
			list = (List<String>) FlexJsonUtil.fromJson(jsonString).get("combdatas");
		}
		System.out.println(list.toString());
		for (String string : list) {
			System.err.println(string);
		}
	}

	/**
	 * @author LiuJie
	 * @功能:integer to string string to integer fail
	 */
	public static void TestJsonInterToString() {
		String jsonString = "[{\"RN\":1,\"wod_woid\":2286,\"wod_id\":15246,\"wod_detno\":1,\"wod_empname\":\"CS029\",\"wod_type\":\"双休日加班\",\"wod_startdate\":\"2015-09-14 00:00:00\",\"wod_enddate\":\"2015-09-17 00:00:00\",\"wod_isallday\":\"0\",\"wod_count\":3}]";
		List<ExtraWorkItems> items = FlexJsonUtil.fromJsonArray(jsonString, ExtraWorkItems.class);
		System.out.println("" + items.get(0));
	}

	public static void TestHttpGetData() {
		String url = "http://218.17.158.219:8090/ERP/mobile/common/getCombo.action";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("caller", "Ask4Leave");
		param.put("field", "va_vacationtype");

		try {
			Response response = HttpUtil.sendGetRequest(url, param, true);
			System.out.println("login 状态码：" + response.getStatusCode());
			System.out.println("login 正文：" + response.getResponseText());
		} catch (Exception e) {
			System.out.println("超时");
			System.out.println(e.getMessage());
		}
	}

	public static void TestSpringMvc(String url, Map<String, Object> param, String reMethod) {
		Response response = null;
		try {
			if ("get".equals(reMethod)) {
				response = HttpUtil.sendGetRequest(url, param, false);
			} else {
				response = HttpUtil.sendPostRequestNew(url, param, false);
			}
			System.out.println("login 状态码：" + response.getStatusCode());
			System.out.println("login 正文：" + response.getResponseText());
		} catch (Exception e) {
			System.out.println("超时");
			System.out.println(e.getMessage());
		}
	}

	public static void TestLogin(String phone, String password) {
		String url = "http://manage.ubtob.com/public/account";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("user", phone);
		param.put("password", password);
		try {
			//管理平台经过加密的
			Response response = HttpUtil.sendGetRequest(url, param, true);
			System.out.println("login 状态码：" + response.getStatusCode());
			System.out.println("login 正文：" + response.getResponseText());
		} catch (Exception e) {
			System.out.println("超时");
			System.out.println(e.getMessage());
		}
	}

	public static String TestLoginERP(String phone, String password, String master) {
		String url_erp = "http://218.17.158.219:8099/ERP/mobile/login.action";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("username", phone);
		param.put("password", password);
		param.put("master", master);
		try {
			System.out.println(url_erp);
			Response response = HttpUtil.sendPostRequest(url_erp, param, true);
			System.out.println("" + response.getStatusCode());
			System.out.println("" + response.getResponseText());
			String jessonid = FlexJsonUtil.fromJson(response.getResponseText()).get("sessionId").toString();
			return jessonid;
		} catch (Exception e) {
			System.out.println("超时");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static void TestERPLeavea(String jessonid) {
		String url_erp = "http://192.168.253.199:8099/ERP/mobile/common/getPanel.action";
		// Map<String, String> param = new HashMap<String, String>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("caller", "Ask4Leave");
		param.put("formCondition", "va_id=10444");
		param.put("gridCondition", "null=10444");
		System.out.println("jessonid=" + jessonid);
		param.put("sessionId", jessonid);
		try {
			// HttpUtil.sendPostRequestold(url_erp, param);
			Response response = HttpUtil.sendPostRequestNew(url_erp, param, false);
			System.out.println("" + response.getStatusCode());
			System.out.println("" + response.getResponseText());
		} catch (Exception e) {
			System.out.println("超时");
			System.out.println(e.getMessage());
		}
	}

	public static String TestLoginB2B(String phone, String password) {
		String url = "http://www.ubtob.com/j_spring_security_check";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("j_username", phone);
		param.put("j_password", password);
		try {
			Response response = HttpUtil.sendPostRequestNew(url, param, true);
			System.out.println("status:" + response.getStatusCode());
			System.out.println("text:" + response.getResponseText());
			org.apache.http.client.CookieStore cookieStore = Response.cookieStore;
			for (int i = 0; i < cookieStore.getCookies().size(); i++) {
				if ("www.ubtob.com".equals(cookieStore.getCookies().get(i).getDomain())) {
					return cookieStore.getCookies().get(i).getValue();

				}
			}
		} catch (Exception e) {
			System.out.println("超时");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static void TestB2BPur(String cookie) {
		String url = "http://www.ubtob.com/mobile/sale/orders";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", 1);
		param.put("count", 5);
		param.put("sorting", "{date:'desc'}");
		param.put("filter", "{status:200}");

		try {
			System.out.println("cookie=" + cookie);
			Response response = HttpUtil.sendGetRequest(url, param, true, cookie);
			System.out.println("" + response.getStatusCode());
			System.out.println("" + response.getResponseText());
		} catch (Exception e) {
			System.out.println("超时");
			System.out.println(e.getMessage());
		}
	}

	// 添加请求头
	// 小结：
	// 1、同名Header可以有多个 ，Header[] getHeaders(String name)。
	// 2、运行时使用的是第一个， Header getFirstHeader(String name)。
	// 3、addHeader，如果同名header已存在，则追加至原同名header后面。
	// 4、setHeader，如果同名header已存在，则覆盖一个同名header。
	public static void testHttpHead() {
		HttpGet httpGet = new HttpGet("");
		httpGet.addHeader("Cookie", "aa");
		httpGet.addHeader("Cookie", "liujie");
		System.out.println("------------1---getFirstHeader----------");
		Header header_first = httpGet.getFirstHeader("Cookie");
		System.out.println(header_first.getName() + "  " + header_first.getValue());

		System.out.println("-----------2---getAllHeaders-----------");

		Header headers[] = httpGet.getAllHeaders();
		for (Header header : headers) {
			System.out.println(header.getName() + "  " + header.getValue());
		}
		httpGet.addHeader("Cookie", "bb");
		System.out.println("-----------3----getFirstHeader----------");

		header_first = httpGet.getFirstHeader("Cookie");
		System.out.println(header_first.getName() + "  " + header_first.getValue());

		System.out.println("-----------4----getAllHeaders----------");
		headers = httpGet.getAllHeaders();

		for (Header header : headers) {
			System.out.println(header.getName() + "  " + header.getValue());
		}
		httpGet.setHeader("Cookie", "cc");
		System.out.println("-----------3----getFirstHeader----------");

		header_first = httpGet.getFirstHeader("Cookie");
		System.out.println(header_first.getName() + "  " + header_first.getValue());

		System.out.println("-----------4----getAllHeaders----------");
		headers = httpGet.getAllHeaders();
		for (Header header : headers) {
			System.out.println(header.getName() + "  " + header.getValue());
		}
		httpGet.setHeader("Cookie", "dd");
		System.out.println("-----------3----getFirstHeader----------");

		header_first = httpGet.getFirstHeader("Cookie");
		System.out.println(header_first.getName() + "  " + header_first.getValue());

		System.out.println("-----------4----getAllHeaders----------");
		headers = httpGet.getAllHeaders();
		for (Header header : headers) {
			System.out.println(header.getName() + "  " + header.getValue());
		}
	}

	/**
	 * @author LiuJie
	 * @功能:成功
	 */
	public static void TestEntityList() {
		String jsonString = "[{\"va_code\": \"AL15090076\"," + "\"va_holidaytype\": \"全日\", "
				+ "\"va_vacationtype\": \"事假\", " + "\"va_department\": \"\", " + "\"va_position\": \"\", "
				+ "\"va_alldays\": 25, " + "\"va_date\": \"2015-09-08 00:00:00\", "
				+ "\"va_startime\": \"2015-09-12 18:56:00\", " + "\"va_status\": \"已提交\", " + "\"va_id\": 9941, "
				+ "\"va_endtime\": \"2015-09-19 20:56:00\"" + "}]";
		System.out.println(jsonString);
		List<LeaveEntity> iEntities = FlexJsonUtil.fromJsonArray(jsonString, LeaveEntity.class);
		System.out.println(iEntities);
		System.out.println(iEntities.size());
		System.out.println(iEntities.get(0).getVa_statuscode());
	}

	/**
	 * @author LiuJie
	 * @功能:json转内部实体类，解析失败；
	 */
	public static void TestEntityItem() {
		String jsonString = "{" + "\"RN\": 1," + "\"wod_id\": 15059," + "\"wod_woid\": 9950," + "\"wod_detno\": 1,"
				+ "\"wod_empname\": \"YINGP\"," + "\"wod_type\": \"普通加班\","
				+ "\"wod_startdate\": \"2015-09-09 00:00:00\"," + "\"wod_enddate\": \"2015-09-10 00:00:00\","
				+ "\"wod_count\": \"\"" + "}";
		System.out.println(jsonString);
		items items = FlexJsonUtil.fromJson(jsonString, items.class);
		System.out.println(items);
		System.out.println(items.getWod_empname());

	}

	/**
	 * @author LiuJie
	 * @功能:成功
	 */
	public static void TestEntity() {
		String jsonString = "{\"va_code\": \"AL15090076\"," + "\"va_holidaytype\": \"全日\", "
				+ "\"va_vacationtype\": \"事假\", " + "\"va_department\": \"\", " + "\"va_position\": \"\", "
				+ "\"va_alldays\": 25, " + "\"va_date\": \"2015-09-08 00:00:00\", "
				+ "\"va_startime\": \"2015-09-12 18:56:00\", " + "\"va_status\": \"已提交\", " + "\"va_id\": 9941, "
				+ "\"va_endtime\": \"2015-09-19 20:56:00\"" + "}";
		System.out.println(jsonString);
		LeaveEntity iEntities = FlexJsonUtil.fromJson(jsonString, LeaveEntity.class);
		System.out.println(iEntities);
		System.out.println(iEntities.getVa_statuscode());
		System.out.println(iEntities.getVa_status());
	}

	public static void TestJsonDateList() {
		String json = "[{\"account\":\"200040149\",\"platform\":\"B2B\"},{\"account\":\"YINGP\",\"master\":\"UAS\",\"platform\":\"ERP\",\"website\":\"http://218.17.158.219:8090/ERP/\"},{\"account\":\"ADMIN\",\"master\":\"SAAS_10041166\",\"platform\":\"ERP\",\"website\":\"http://aaa.saas.ubtob.com\"}]";
		System.out.println(json);
		List<?> logMsg = (List<?>) FlexJsonUtil.fromJson(json, ArrayList.class);
		System.out.println(logMsg.size());
		if (logMsg != null && !logMsg.isEmpty()) {
			for (int i = 0; i < logMsg.size(); i++) {
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (HashMap<String, Object>) logMsg.get(i);
				if (map.get("platform").toString().equals("ERP")) {
					System.out.println("ERP:" + map.get("platform").toString());
					System.out.println("ERP:" + map.get("account").toString());
					System.out.println("ERP:" + map.get("website").toString());
				} else if (map.get("platform").toString().equals("B2B")) {
					System.out.println("B2B:" + map.get("platform").toString());
					System.out.println("B2B:" + map.get("account").toString());
				} else if (map.get("platform").toString().equals("SAS")) {
					System.out.println("SAS:" + map.get("platform").toString());
					System.out.println("SAS:" + map.get("account").toString());
					System.out.println("SAS:" + map.get("website").toString());
				}

			}
		}
	}

	public static void TestJsonEntity() {
		String url = "[{\"account\":\"200040149\",\"platform\":\"B2B\"},{\"account\":\"YINGP\",\"master\":\"DataCenter\",\"platform\":\"ERP\",\"website\":\"http://218.18.115.198:8098/ERP/\"},{\"account\":\"YINGP\",\"master\":\"UAS\",\"platform\":\"ERP\",\"website\":\"http://218.17.158.219:8090/ERP/\"}]";
		List<LoginEntity> logMsg = FlexJsonUtil.fromJsonArray(url, LoginEntity.class);
		System.out.println(logMsg.size());
		System.out.println(logMsg.get(1).getAccount());
	}

	public static void TestJsonData() {
		// ArrayList<String> niArrayList = new ArrayList<String>();
		// niArrayList.add("niha");
		// String liString= FlexJsonUtil.toJsonArray(niArrayList);
		// List<String> newList= FlexJsonUtil.fromJsonArray(liString,
		// String.class);
		// System.out.println(newList.get(0));
		List<String> list = new ArrayList<String>();
		list.add("0");
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		List<String> list1 = new ArrayList<String>();
		list1.add("0");
		list1.add("1");
		list1.add("2");
		list1.add("3");
		list1.add("4");
		// list.remove(2);
		// list.remove(7);
		list.removeAll(list1);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("" + list.get(i));

		}
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int TestdaysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 字符串的日期格式的计算
	 */
	public static float TestdaysBetween(String smdate, String bdate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Calendar cal = Calendar.getInstance();
		long time1 = 0;
		long time2 = 0;
		try {
			cal.setTime(sdf.parse(smdate));
			time1 = cal.getTimeInMillis();
			cal.setTime(sdf.parse(bdate));
			time2 = cal.getTimeInMillis();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("time1=" + time1);
		System.out.println("time2=" + time2);
		long between_days = (time2 - time1) / (1000 * 3600);

		long diff = time2 - time1;
		long days = diff / (1000 * 60 * 60 * 24);
		long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
		System.out.println("天:" + days);
		System.out.println("时:" + hours);
		System.out.println("分：" + minutes);
		return Float.parseFloat(String.valueOf(between_days));
	}

	/**
	 * @author LiuJie
	 * @功能:解析xml 字符串
	 */
	public static void XmlStringReader(String xmlString) {
		// 创建一个新的字符串
		StringReader read = new StringReader(xmlString);
		// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
		InputSource source = new InputSource(read);
		// 创建一个新的SAXBuilder
		SAXBuilder saxbBuilder = new SAXBuilder();
		try {
			// 通过输入源构造一个Document
			Document doc = saxbBuilder.build(source);
			// 取的根元素
			Element root = doc.getRootElement();
			List<?> node = root.getChildren();
			for (int i = 0; i < node.size(); i++) {
				Element element = (Element) node.get(i);
				System.out.println(element.getName());
				System.out.println("value=" + element.getValue());
				List<?> subNode = element.getChildren();
				for (int j = 0; j < subNode.size(); j++) {
					Element subElement = (Element) subNode.get(j);
					System.out.println(subElement.getName());
					System.out.println("Content：" + subElement.getAttributeValue("Content"));
					System.out.println("ToUserName：" + subElement.getAttributeValue("ToUserName"));
					System.out.println("FromUserName：" + subElement.getAttributeValue("FromUserName"));
					System.out.println("CreateTime：" + subElement.getAttributeValue("CreateTime"));
					System.out.println("MsgType：" + subElement.getAttributeValue("MsgType"));
					System.out.println("MsgId：" + subElement.getAttributeValue("MsgId"));
				}
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** @注释：截取指定字符插入空格 */
	public static String get3BrStr(String str, int size) {
		int length = str.length();
		if (length <= size) {
			return str;
		}
		int n = (length + size - 1) / size;
		int from = 0;
		int to = 0;
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < n; i++) {
			from = to;
			to = from + size;
			to = to > length ? length : to;
			if (i == n - 1) {
				builder.append(str.subSequence(from, to));
			} else {
				builder.append(str.subSequence(from, to)).append("\n");
			}
		}
		String result = builder.toString();
		return result;
	}

}
