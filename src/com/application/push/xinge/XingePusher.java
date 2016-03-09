package com.application.push.xinge;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import com.tencent.xinge.ClickAction;
import com.tencent.xinge.Message;
import com.tencent.xinge.MessageIOS;
import com.tencent.xinge.Style;
import com.tencent.xinge.TimeInterval;
import com.tencent.xinge.XingeApp;

/**
 * 信鸽推送
 * @author LiuJ
 * @date 2015年3月7日10:01:04
 *
 */
public class XingePusher {
	
	/**
	 * 向单个账套的单个账号Android设备推送一条信息<p>在客户端使用用户手机号向信鸽注册</p>
	 * @param master 账套名
	 * @param account 账号
	 * @param enUU 所属企业UU号
	 * @param tittle 推送信息标题
	 * @param content 推送信息内容
	 * @param url 信息对应网页链接
	 * @param pageTitle 网页webView标题
	 * @return
	 */
	public static JSONObject pushSingleAccountAndroid(String master, String account, String tittle, String content, String enUU, String url, String pageTitle) {
		XingeApp xinge = new XingeApp(2100046094, "5805cd8bf93ea5405c98b3a6e81e29b3");
		Message message = new Message();
		message.setTitle(tittle);//推送信息标题
		message.setContent(content);//推送信息内容（第二行）
		message.setType(Message.TYPE_NOTIFICATION);
		//依次为 本地通知样式(就用0)[,是否响铃][,是否震动][,是否可清除][,覆盖模式][,是否呼吸灯][,图标类型][,$styleId]
		Style style = new Style(0, 1, 1, 1, 0, 1, 0, 0);
		message.setStyle(style);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uu", enUU);
		map.put("master", master);
		if(!StringUtils.isEmpty(url)) {
			map.put("url", url);
			map.put("title", pageTitle);
			ClickAction clickAction = new ClickAction();
			clickAction.setActionType(ClickAction.TYPE_ACTIVITY);
			clickAction.setActivity("com.xzjmyk.pm.activity.WebViewLoadActivity");
			message.setAction(clickAction);
		}
		message.setCustom(map);
		JSONObject obj = xinge.pushSingleAccount(0, account, message);//发送给当前的账套
		return obj;
	}
	
	/**
	 * 向单个账套的单个账号IOS设备推送一条信息<p>在客户端使用用户手机号向信鸽注册</p>
	 * @param master 账套名
	 * @param account 用户名（手机号）
	 * @param title 消息标题
	 * @param content 消息内容（不显示）
	 * @param enUU 企业UU号
	 * @param url 消息对应链接
	 * @param pageTitle 网页webView
	 * @return
	 */
	public static JSONObject pushSingleAccountIOS(String master, String account, String title, String content, String enUU, String url, String pageTitle) {
		XingeApp xinge = new XingeApp(2200121309L, "fd52d406369688c2619b794862ea3f12");
		MessageIOS message = new MessageIOS();
		message.setAlert(title);
		System.out.println(title + " " + content + "${\"platform\":\"ERP\",\"url\":\"" + url + "\",\"enuu\":\"" + enUU + "\",\"master\":\"" + master + "\"}");
		message.setBadge(1);
		message.setSound("beep.wav");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("enuu", enUU);
		map.put("master", master);
		map.put("platform", "ERP");
		if(!StringUtils.isEmpty(url)) {
			map.put("url", url);
		}
		message.setCustom(map);
		TimeInterval acceptTime1 = new TimeInterval(0,0,23,59);
		message.addAcceptTime(acceptTime1);
		JSONObject obj = xinge.pushSingleAccount(0, account, message, XingeApp.IOSENV_PROD);
		return obj;
	}
	
	/**
	 * 推送单个账号（手机号）
	 * @param master
	 * @param account
	 * @param title
	 * @param content
	 * @param enUU
	 * @param url
	 * @param pageTitle
	 * @return
	 */
	public static JSONObject[] pushByAccount(String master, String account, String title, String content, String enUU, String url, String pageTitle) {
		if(StringUtils.isEmpty(account)) {
			JSONObject[] json = new JSONObject[2];
			json[0] = pushSingleAccountAndroid(master, account, title, content, enUU, url, pageTitle);
			json[1] = pushSingleAccountIOS(master, account, title, content, enUU, url, pageTitle);
			return json;
		} else {
			return new JSONObject[2];
		}
	}

}