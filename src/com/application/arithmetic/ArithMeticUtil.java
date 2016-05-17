package com.application.arithmetic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author:Arison 2016年5月11日 下午5:20:36
 * @desc:算法趣味题目大全
 */
public class ArithMeticUtil {
	/**
	 * @author:Arison
	 * @desc:一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
	 */
	public static int JumpFloorII(int target) {
		if (target <= 0)
			return -1;
		if (target == 1)
			return 1;
		if (target > 1)
			return 2 * JumpFloorII(target - 1);
		return 0;
	}

	/**
	 * @author Administrator
	 * @功能:字符串互异算法
	 */
	public static boolean checkDifferent(String iniString) {
		if (iniString.length() > 256) {
			return false;
		}
		boolean[] char_set = new boolean[256];
		for (int i = 0; i < iniString.length(); i++) {
			char ch = iniString.charAt(i);
			if (char_set[ch])
				return false;
			char_set[ch] = true;
		}
		return true;
	}

	/**
	 * @author:Arison
	 * @desc:测试函数removeDuplicate
	 */
	public static void testListDuplicate() {
		List<String> lists = new ArrayList<String>();
		lists.add("10012");
		lists.add("10011");
		lists.add("10014");
		lists.add("10012");
		lists.add("10016");
		lists.add("10016");
		lists = removeDuplicate(lists);
		System.out.println(JSON.toJSONString(lists));
	}

	/**
	 * @author:Arison
	 * @desc:
	 */
	public static String replaceSpace(String iniString, int length) {
		int cnt = 0;
		int newLen;
		for (int i = 0; i < iniString.length(); i++) {
			if (iniString.charAt(i) == ' ')
				cnt++;
		}
		newLen = cnt * 2 + length;
		char[] res = new char[newLen];
		for (int i = length - 1; i >= 0; i--) {
			if (iniString.charAt(i) == ' ') {
				res[newLen - 1] = '0';
				res[newLen - 2] = '2';
				res[newLen - 3] = '%';
				newLen = newLen - 3;
			} else {
				res[newLen - 1] = iniString.charAt(i);
				newLen = newLen - 1;
			}
		}
		String s = new String(res);
		return s;
	}

	/** @注释：list去除重复数据 */
	public static List<String> removeDuplicate(List<String> list) {
		HashSet<String> h = new HashSet<String>(list);
		list.clear();
		list.addAll(h);
		return list;
	}

	public static void testReplace() {
		String str = "@昵称 打发第三方  @你第三方   发生啥地方   范德萨发";
		System.out.println(str.trim());
		System.out.println(str.replace("@昵称 ", "@昵称"));
	}

	public static void testStringCaseOne() {
		String str = "柳发平，虽然不常见，可没忘记你；每个日子里，衷心祝福你：上帝保佑你!菩萨爱护你!财神抱住你!爱神射住你!愿你要云得云,要雨得雨，开心如";
		String str1 = "意，黄金遍地！新年到来，请遵守四项基本原则：将财神看守到底，将幸福紧握到底，将好运怀抱到底、将爱情进行到底！请严格遵守，直至革命胜利！";
		String str3 = "新年到来，请遵守四项基本原则：将财神看守到底，将幸福紧握到底，将好运怀抱到底、将爱情进行到底！请严格遵守，直至革命胜利！";
		String str5 = str + str1;
		System.out.println(str5.length() % 67);
		if (str5.length() % 67 > 0) {
			System.out.println(str5.length() / 67 + 1);
		} else {
			System.out.println(str5.length() / 67 == 0 ? 1 : str5.length() / 67);
		}
		System.out.println(str.length());
		System.out.println(str1.length());
		System.out.println(str3.length());
	}

	/**
	 * @author Administrator
	 * @功能:JSON支持List反转
	 */
	public static void testJsonStr() {
		List<String> tem = new ArrayList<String>();
		tem.add("sdafds");
		tem.add("sdafds");
		tem.add("sdafds");
		tem.add("sdafds");
		tem.add("刘杰");
		String jsonStr = JSON.toJSONString(tem);
		System.out.println(jsonStr);

		List<String> newstr = JSON.parseArray(jsonStr, String.class);
		for (int i = 0; i < newstr.size(); i++) {
			System.out.println(newstr.get(i).toString());
		}
	}

	public static void replaceStr() {
		String str = "4325432";
		str = str.replace("@昵称", "刘杰");
		System.out.println("替换之后：" + str);
	}

	/**
	 * @author Administrator
	 * @功能:时间相减去
	 */
	public static String testTime() {
		String str = "08:08:99";
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(new SimpleDateFormat("HH:mm:ss").format(date));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 2);
		System.out.println(new SimpleDateFormat("HH:mm:ss").format(calendar.getTime()));
		return new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
	}

	public static void testList() {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		System.out.println(list.size());
		System.out.println(list.get(list.size() - 1));
	}

	/**
	 * @author Administrator
	 * @功能:删除map对象最后一个元素
	 */
	public static void testHashMap() {
		Map<String, Object> temp = new LinkedHashMap<>();
		temp.put("1", "5");
		temp.put("2", "2");
		temp.put("3", "5");
		temp.put("4", "4");
		temp.put("5", "9");

		Object object = temp.remove("5");
		System.out.println(object.toString());
		System.out.println(temp.toString());

	}

	public static void isNull() {
		String str = "";
		String str_one = null;
		String str_two = "null";
		String str_three = " ";
		System.out.println(StringUtils.isEmpty(str));
		System.out.println(StringUtils.isEmpty(str_one));
		System.out.println(StringUtils.isEmpty(str_three));
		System.out.println(StringUtils.isEmpty(str_two));
	}

	/**
	 * @author Administrator
	 * @功能:测试空指针
	 */
	@SuppressWarnings("null")
	public static void parseObjectIsNull() {
		String json = "{\"name\":\"\",\"url\":\"null\",\"age\":null}";
		@SuppressWarnings("unused")
		String jsonArray = "{\"obj\":[\"黄玉林\",\"/usr/local/apache-tomcat-7.0.32/webapps/postattach/U0323/797fad2f4be6496cb0b267176b816d63.png\"],\"success\":true}";
		String jsonArrayNull = "{\"exception\":\"程序错误\"}";
		JSONObject object = JSON.parseObject(json);
		System.out.println(object.getString("age"));
		JSONObject arrayNull = JSON.parseObject(jsonArrayNull);
		System.err.println(arrayNull.get("exception"));
		JSONArray array = JSON.parseArray(arrayNull.getString("obj"));
		System.out.println("array:" + array);
		if (array == null) {
			System.out.println(array.getString(0));
			System.out.println(array.getString(1));
		}

	}

	public static void parseString() {
		String url = "/usr/local/apache-tomcat-7.0.32/webapps/postattach/U0323/21811559ce3a46bc97a46ec7716b4476.png";
		System.out.println(url.split("/")[5]);
		System.out.println(url.split("/")[7]);
	}

	public static void parseJsonObject() {
		String url = "{\"obj\":[\"黄玉林\",\"/usr/local/apache-tomcat-7.0.32/webapps/postattach/U0323/21811559ce3a46bc97a46ec7716b4476.png\"],\"success\":true}";
		JSONObject object = JSON.parseObject(url);
		JSONArray array = JSON.parseArray(object.getString("obj"));
		System.out.println(array.getString(0));
		System.out.println(array.getString(1));
	}

}
