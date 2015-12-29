package com.test.android.xinge;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONObject;

import com.tencent.xinge.ClickAction;
import com.tencent.xinge.Message;
import com.tencent.xinge.Style;
import com.tencent.xinge.TimeInterval;
import com.tencent.xinge.XingeApp;

public class XingeMain {

	public static void main(String[] args) {
		
		Timer timer=new Timer();
		TimerTask task=new TimerTask() {
			int i=0;
			@Override
			public void run() {
				System.out.println(getSysCurrtentTime()+"推送i="+i++);
				TestAppPush("13600403575","陈璐，你有一条采购单",getSysCurrtentTime());
				TestIOSAppPush("13600403575");
			}
		};
		timer.schedule(task, 1000,1000);
		
	}
    
	public static String getSysCurrtentTime(){
		return new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
	}
	
	private static void TestAppPush(String phone,String title,String time) {
		JSONObject object=	XingePusher.pushSingleAccountAndroid(
				"HUASL_SZ", 
				 phone, 
				 title, 
				"您收到到推送消息吗？     时间:"+time,
				"10041106", 
				"jsps/mobile/task.jsp?caller=ResourceAssignment!Bill%26id=484060",
				"我的任务");
		
//		JSONObject object=	XingePusher.pushSingleAccountAndroid(
//				"DataCenter", 
//				"13798490565", 
//				"新的单据", 
//				"测试单据",
//				"10041106", 
//				"jsps/mobile/task.jsp?caller=ResourceAssignment!Bill%26id=484060",
//				"待办事宜");
//		JSONObject object=	XingePusher.pushSingleAccountAndroid(
//				"HUASL_SZ", 
//				"13798490565", 
//				"新的单据", 
//				"测试单据",
//				"10041106", 
//				"jsps/mobile/task.jsp?caller=ResourceAssignment!Bill%26id=484060",
//				"待办事宜");
//		
//		JSONObject object=	XingePusher.pushSingleAccountAndroid(
//				"UAS", 
//				"13798490565", 
//				"新的单据", 
//				"测试单据",
//				"10041166", 
//				"jsps/mobile/task.jsp?caller=ResourceAssignment!Bill%26id=484060",
//				"待办事宜");
      System.out.println(object.toString());
	}

	public static void TestIOSAppPush(String phone) {
		JSONObject object=	XingePusher.pushSingleAccountIOS(
				"HUASL_SZ", 
				phone, 
				"您有一条新的采购单", 
				"测试单据",
				"10041106", 
				"jsps/mobile/task.jsp?caller=ResourceAssignment!Bill%26id=484060",
				"我的任务");
		
//		JSONObject object=	XingePusher.pushSingleAccountAndroid(
//				"DataCenter", 
//				"13798490565", 
//				"新的单据", 
//				"测试单据",
//				"10041106", 
//				"jsps/mobile/task.jsp?caller=ResourceAssignment!Bill%26id=484060",
//				"待办事宜");
//		JSONObject object=	XingePusher.pushSingleAccountAndroid(
//				"HUASL_SZ", 
//				"13798490565", 
//				"新的单据", 
//				"测试单据",
//				"10041106", 
//				"jsps/mobile/task.jsp?caller=ResourceAssignment!Bill%26id=484060",
//				"待办事宜");
//		
//		JSONObject object=	XingePusher.pushSingleAccountAndroid(
//				"UAS", 
//				"13798490565", 
//				"新的单据", 
//				"测试单据",
//				"10041166", 
//				"jsps/mobile/task.jsp?caller=ResourceAssignment!Bill%26id=484060",
//				"待办事宜");
      System.out.println(object.toString());
	}

	protected JSONObject demoPushSingleDeviceMessage() {
		XingeApp xinge = new XingeApp(000, "secret_key");
		Message message = new Message();
		message.setTitle("title");
		message.setContent("content");
		message.setType(Message.TYPE_MESSAGE);
		message.setExpireTime(86400);
		JSONObject ret = xinge.pushSingleDevice("token", message);

		return ret;
	}

	protected JSONObject demoPushSingleDeviceNotification() {
		XingeApp xinge = new XingeApp(000, "secret_key");
		JSONObject ret = xinge.pushSingleDevice("token", message1);
		return (ret);
	}


	protected JSONObject demoPushSingleDeviceNotificationIntent() {
		XingeApp xinge = new XingeApp(000, "secret_key");
		JSONObject ret = xinge.pushSingleDevice("token", message2);
		return (ret);
	}

	protected JSONObject demoPushSingleAccount() {
		XingeApp xinge = new XingeApp(2100046094,
				"5805cd8bf93ea5405c98b3a6e81e29b3");
		Message message = new Message();
		Style style = new Style(0, 1, 1, 1, 1, 1, 1, 1);
		// message.setExpireTime(86400);
		message.setTitle("���յ�����");
		message.setStyle(style);
		message.setContent("content");
		message.setType(Message.TYPE_NOTIFICATION);
		JSONObject ret = xinge.pushSingleAccount(0,
				"UAS_SAAS_150228001zhongyl@usoft", message);
		return (ret);
	}

	protected JSONObject demoPushAccountList() {
		XingeApp xinge = new XingeApp(000, "secret_key");
		Message message = new Message();
		message.setExpireTime(86400);
		message.setTitle("title");
		message.setContent("content");
		message.setType(Message.TYPE_MESSAGE);
		List<String> accountList = new ArrayList<String>();
		accountList.add("joelliu");
		accountList.add("joelliu");
		JSONObject ret = xinge.pushAccountList(0, accountList, message);
		return (ret);
	}

	
	protected JSONObject demoPushAllDevice() {
		XingeApp xinge = new XingeApp(000, "secret_key");

		JSONObject ret = xinge.pushAllDevice(0, message1);
		return (ret);
	}

	protected JSONObject demoPushTags() {
		XingeApp xinge = new XingeApp(000, "secret_key");
		List<String> tagList = new ArrayList<String>();
		tagList.add("joelliu");
		tagList.add("phone");
		JSONObject ret = xinge.pushTags(0, tagList, "OR", message1);
		return (ret);
	}

	protected JSONObject demoQueryPushStatus() {
		XingeApp xinge = new XingeApp(000, "secret_key");
		List<String> pushIdList = new ArrayList<String>();
		pushIdList.add("390");
		pushIdList.add("389");
		JSONObject ret = xinge.queryPushStatus(pushIdList);
		return (ret);
	}

	protected JSONObject demoQueryDeviceCount() {
		XingeApp xinge = new XingeApp(000, "secret_key");
		JSONObject ret = xinge.queryDeviceCount();
		return (ret);
	}

	protected JSONObject demoQueryTags() {
		XingeApp xinge = new XingeApp(000, "secret_key");
		JSONObject ret = xinge.queryTags();
		return (ret);
	}
	protected JSONObject demoQueryTagTokenNum() {
		XingeApp xinge = new XingeApp(000, "secret_key");
		JSONObject ret = xinge.queryTagTokenNum("tag");
		return (ret);
	}

	protected JSONObject demoQueryTokenTags() {
		XingeApp xinge = new XingeApp(000, "secret_key");
		JSONObject ret = xinge.queryTokenTags("token");
		return (ret);
	}

	protected JSONObject demoCancelTimingPush() {
		XingeApp xinge = new XingeApp(000, "secret_key");
		JSONObject ret = xinge.cancelTimingPush("32");
		return (ret);
	}

	
	public XingeMain() {
		message1 = new Message();
		message1.setType(Message.TYPE_NOTIFICATION);
		Style style = new Style(1);
		style = new Style(3, 1, 0, 1, 0);
		ClickAction action = new ClickAction();
		action.setActionType(ClickAction.TYPE_URL);
		action.setUrl("http://xg.qq.com");
		Map<String, Object> custom = new HashMap<String, Object>();
		custom.put("key1", "value1");
		custom.put("key2", 2);
		message1.setTitle("title");
		message1.setContent("��С");
		message1.setStyle(style);
		message1.setAction(action);
		message1.setCustom(custom);
		TimeInterval acceptTime1 = new TimeInterval(0, 0, 23, 59);
		message1.addAcceptTime(acceptTime1);

		message2 = new Message();
		message2.setType(Message.TYPE_NOTIFICATION);
		message2.setTitle("title");
		message2.setContent("֪ͨ���ִ��Intent����");
		style = new Style(1);
		action = new ClickAction();
		action.setActionType(ClickAction.TYPE_INTENT);
		action.setIntent("intent:10086#Intent;scheme=tel;action=android.intent.action.DIAL;S.key=value;end");
		message2.setStyle(style);
		message2.setAction(action);

		message3 = new Message();
		message3.setType(Message.TYPE_NOTIFICATION);
		message3.setTitle("title");
		message3.setContent("֪ͨ���ִ��Intent����");
		style = new Style(1);
		// �Զ�����ʽ
		style.setRingRaw("ring.mp3");
		style.setIconRes("xg.png");
		style.setSmallIcon("xg1.png");
		// ͨ������Ӧ��
		action = new ClickAction();
		action.setActionType(ClickAction.TYPE_PACKAGE);
		action.setPackageName("test.package.name");
		action.setPackageDownloadUrl("http://www.downloadurl.com");
		action.setConfirmOnPackageDownloadUrl(1);
		message3.setStyle(style);
		message3.setAction(action);
	}

	private Message message1;
	private Message message2;
	private Message message3;
}
