package com.application.deprecated;

import java.util.HashMap;
import java.util.Map;


import com.application.util.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.application.json.FlexJsonUtil;
import com.application.util.HttpUtil.Response;

@Deprecated
public class WinXinTest {

	public static void main(String[] args) {
		// String access_token = getAccessToken();
		// pushAMessage(access_token);
		// WeixinAddGroup(access_token);
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
			com.application.deprecated.HttpUtil.Response response = com.application.deprecated.HttpUtil
					.sendPostRequest(url, param, false, false);
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
			com.application.deprecated.HttpUtil.Response response = com.application.deprecated.HttpUtil
					.sendPostRequest(url, param, false, false);
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
			com.application.deprecated.HttpUtil.Response response = com.application.deprecated.HttpUtil
					.sendPostRequest(url, param, false, false);
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
	 * @功能 执行微信方法
	 */
	// public static void excuteWeixin() {
	// if (token == null) {
	// token = WeixinGetToken();
	// }
	// if (!StringUtils.isEmpty(token)) {
	// WeiXinSendModeMessage(token);
	// }
	// }
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

	/**
	 * 重新获取access_token
	 * 
	 * @return
	 */
	public static String getAccessToken() {
		String access_token = "";
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("grant_type", "client_credential");
			params.put("appid", "wxbc1f8607137d3b8a");
			params.put("secret", "f34867da7b8b70f92e6e4789d7016b26");
			Response response = HttpUtil.sendGetRequest("https://api.weixin.qq.com/cgi-bin/token", params);
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
	 * 
	 * @param access_token
	 */
	/*
	 * private static void pushAMessage(String access_token) { try { Map<String,
	 * Object> params = new HashMap<String, Object>(); params.put("touser",
	 * "o8lZ9uGnn074M2wiP_5cWsZ3NL8s"); params.put("msgtype", "text");
	 * JSONObject object = new JSONObject(); object.put("content", "你好啊");
	 * params.put("text", object);
	 * System.out.println("json="+FlexJsonUtil.toJson(params)); Response
	 * response =HttpUtil.sendPostRequest(
	 * "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" +
	 * access_token, params, false, false);
	 * System.out.println(response.getStatusCode());
	 * System.out.println(response.getResponseText()); } catch (Exception e) {
	 * e.printStackTrace(); } }
	 */

	/*
	 * public static void getAllServers(String access_token) { try { Response
	 * response =HttpUtil.sendGetRequest(
	 * "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token="
	 * + access_token, null); System.out.println(response.getStatusCode());
	 * System.out.println(response.getResponseText()); } catch (Exception e) {
	 * e.printStackTrace(); } }
	 */

	/**
	 * @author LiuJie
	 * @功能:微信添加分组
	 */
	/*
	 * public static void WeixinAddGroup(String token){ String
	 * url="https://api.weixin.qq.com/cgi-bin/groups/create?access_token="+
	 * token; Map<String, Object> param = new HashMap<String, Object>();
	 * JSONObject object=new JSONObject(); object.put("name", "usoftchina");
	 * param.put("group", object); try { Response response
	 * =com.application.deprecated.HttpUtil.sendPostRequest(url,
	 * param,false,false);
	 * System.out.println("result:"+response.getResponseText()); } catch
	 * (Exception e) { e.printStackTrace(); } }
	 */
}
