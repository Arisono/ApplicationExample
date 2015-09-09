package com.test.java.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpGet;

import com.test.android.entity.ExtraWork.items;
import com.test.android.entity.ExtraWorkItems;
import com.test.android.entity.LeaveEntity;
import com.test.android.entity.LoginEntity;
import com.test.java.base.FlexJsonUtil;
import com.test.java.util.HttpUtil.Response;

/**
 * @author :LiuJie 2015年9月2日 上午9:28:28
 * @注释:静态类
 */
public class StaticUtil {
	public static void main(String[] args) {
		//TestJsonInterToString();
		TestLogin();
	}
	
	@SuppressWarnings("null")
	public static void TestException() {
         try {
			Integer i=null;
			System.out.println(i.toString());
			System.out.println(i);
		   } catch (Exception e) {
			    Writer writer = new StringWriter();
			    PrintWriter pw = new PrintWriter(writer);
			    e.printStackTrace(pw);
			    pw.close();
			    String error= writer.toString();
			    System.out.println(error);
//			  System.out.println(e.getClass());
//			  System.out.println(getStackMsgDetail(e,true));
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
	
	public static String getStackMsgDetail(Throwable e,boolean isfilter) {
		StringBuffer sb = new StringBuffer();
		/*Throwable [] ecause =e.getSuppressed();
		for (int i = 0; i < ecause.length; i++) {
			System.out.println("cause:"+ecause[i].getMessage());
		}*/
		StackTraceElement[] stackArray = e.getStackTrace();
		sb.append("--------------------异常共  "+stackArray.length+" 条----------------------------------\n");
		for (int i =stackArray.length-1; i>=0; i--) {
			StackTraceElement element = stackArray[i];
			if (isfilter) {
				if (element.getLineNumber()==-1) continue;
			}
			sb.append("-------------------------------------------------------\n");
			sb.append("classname:   "+element.getClassName() + "\n");
			sb.append("   method:   "+element.getMethodName()+ "()\n");
			sb.append("     line:   "+element.getLineNumber() + "\n");
			sb.append("--------------------------------------------------------\n");
		}
		return sb.toString();
	}
	
	
	
	
	/**
	 * @author LiuJie
	 * @功能:支持浮点数正整数的正则表达式
	 */
	public static void TestJavaRegx() {
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
			list = (List<String>) FlexJsonUtil.fromJson(jsonString).get(
					"combdatas");
		}
		System.out.println(list.toString());
		for (String string : list) {
			System.err.println(string);
		}
	}
	
	/**
	 * @author LiuJie
	 * @功能:integer to string string to integer  fail
	 */
	public static void TestJsonInterToString() {
		String jsonString="[{\"RN\":1,\"wod_woid\":2286,\"wod_id\":15246,\"wod_detno\":1,\"wod_empname\":\"CS029\",\"wod_type\":\"双休日加班\",\"wod_startdate\":\"2015-09-14 00:00:00\",\"wod_enddate\":\"2015-09-17 00:00:00\",\"wod_isallday\":\"0\",\"wod_count\":3}]";
		List<ExtraWorkItems> items=FlexJsonUtil.fromJsonArray(jsonString, ExtraWorkItems.class);
		System.out.println(""+items.get(0));
	}
	
	public static void TestHttpGetData() {
		String url="http://218.17.158.219:8090/ERP/mobile/common/getCombo.action";
		Map<String, String> param = new HashMap<String, String>();
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

	public static void TestLogin() {
		String url = "http://manage.ubtob.com/public/account";
		Map<String, String> param = new HashMap<String, String>();
		param.put("user", "13798490565");
		param.put("password", "123456");
		try {
			Response response = HttpUtil.sendGetRequest(url, param, true);
			System.out.println("login 状态码：" + response.getStatusCode());
			System.out.println("login 正文：" + response.getResponseText());
		} catch (Exception e) {
			System.out.println("超时");
			System.out.println(e.getMessage());
		}
	}

	public static void TestLoginERP() {
		String url_erp = "http://218.17.158.219:8090/ERP/mobile/login.action";
		Map<String, String> param = new HashMap<String, String>();
		param.put("username", "YINGP");
		param.put("password", "123456");
		param.put("master", "UAS");
		try {
			System.out.println(url_erp);
			Response response = HttpUtil.sendPostRequest(url_erp, param, true);
			System.out.println("" + response.getStatusCode());
			System.out.println("" + response.getResponseText());
		} catch (Exception e) {
			System.out.println("超时");
			System.out.println(e.getMessage());
		}
	}

	public static void TestLoginB2B() {
		// post
		String url = "http://218.17.158.219:9090/platform-b2b/j_spring_security_check";
		Map<String, String> param = new HashMap<String, String>();
		param.put("j_username", "13798490565");
		param.put("j_password", "123456");
		try {
			Response response = HttpUtil.sendPostRequest(url, param, true);
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
		System.out.println(header_first.getName() + "  "
				+ header_first.getValue());

		System.out.println("-----------2---getAllHeaders-----------");

		Header headers[] = httpGet.getAllHeaders();
		for (Header header : headers) {
			System.out.println(header.getName() + "  " + header.getValue());
		}
		httpGet.addHeader("Cookie", "bb");
		System.out.println("-----------3----getFirstHeader----------");

		header_first = httpGet.getFirstHeader("Cookie");
		System.out.println(header_first.getName() + "  "
				+ header_first.getValue());

		System.out.println("-----------4----getAllHeaders----------");
		headers = httpGet.getAllHeaders();
		for (Header header : headers) {
			System.out.println(header.getName() + "  " + header.getValue());
		}
		httpGet.setHeader("Cookie", "cc");
		System.out.println("-----------3----getFirstHeader----------");

		header_first = httpGet.getFirstHeader("Cookie");
		System.out.println(header_first.getName() + "  "
				+ header_first.getValue());

		System.out.println("-----------4----getAllHeaders----------");
		headers = httpGet.getAllHeaders();
		for (Header header : headers) {
			System.out.println(header.getName() + "  " + header.getValue());
		}
		httpGet.setHeader("Cookie", "dd");
		System.out.println("-----------3----getFirstHeader----------");

		header_first = httpGet.getFirstHeader("Cookie");
		System.out.println(header_first.getName() + "  "
				+ header_first.getValue());

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
		String jsonString = "[{\"va_code\": \"AL15090076\","
				+ "\"va_holidaytype\": \"全日\", "
				+ "\"va_vacationtype\": \"事假\", " + "\"va_department\": \"\", "
				+ "\"va_position\": \"\", " + "\"va_alldays\": 25, "
				+ "\"va_date\": \"2015-09-08 00:00:00\", "
				+ "\"va_startime\": \"2015-09-12 18:56:00\", "
				+ "\"va_status\": \"已提交\", " + "\"va_id\": 9941, "
				+ "\"va_endtime\": \"2015-09-19 20:56:00\"" + "}]";
		System.out.println(jsonString);
		List<LeaveEntity> iEntities = FlexJsonUtil.fromJsonArray(jsonString,
				LeaveEntity.class);
		System.out.println(iEntities);
		System.out.println(iEntities.size());
		System.out.println(iEntities.get(0).getVa_statuscode());
	}

	/**
	 * @author LiuJie
	 * @功能:json转内部实体类，解析失败；
	 */
	public static void TestEntityItem() {
		String jsonString = "{" + "\"RN\": 1," + "\"wod_id\": 15059,"
				+ "\"wod_woid\": 9950," + "\"wod_detno\": 1,"
				+ "\"wod_empname\": \"YINGP\"," + "\"wod_type\": \"普通加班\","
				+ "\"wod_startdate\": \"2015-09-09 00:00:00\","
				+ "\"wod_enddate\": \"2015-09-10 00:00:00\","
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
		String jsonString = "{\"va_code\": \"AL15090076\","
				+ "\"va_holidaytype\": \"全日\", "
				+ "\"va_vacationtype\": \"事假\", " + "\"va_department\": \"\", "
				+ "\"va_position\": \"\", " + "\"va_alldays\": 25, "
				+ "\"va_date\": \"2015-09-08 00:00:00\", "
				+ "\"va_startime\": \"2015-09-12 18:56:00\", "
				+ "\"va_status\": \"已提交\", " + "\"va_id\": 9941, "
				+ "\"va_endtime\": \"2015-09-19 20:56:00\"" + "}";
		System.out.println(jsonString);
		LeaveEntity iEntities = FlexJsonUtil.fromJson(jsonString,
				LeaveEntity.class);
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
				Map<String, Object> map = (HashMap<String, Object>) logMsg
						.get(i);
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
		List<LoginEntity> logMsg = FlexJsonUtil.fromJsonArray(url,
				LoginEntity.class);
		System.out.println(logMsg.size());
		System.out.println(logMsg.get(1).getAccount());
	}

	public static void TestJsonData() {
		//ArrayList<String> niArrayList = new ArrayList<String>();
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
	public static int TestdaysBetween(Date smdate, Date bdate)
			throws ParseException {
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
		long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours
				* (1000 * 60 * 60))
				/ (1000 * 60);
		System.out.println("天:" + days);
		System.out.println("时:" + hours);
		System.out.println("分：" + minutes);

		return Float.parseFloat(String.valueOf(between_days));
	}

}
