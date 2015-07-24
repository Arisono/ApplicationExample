package com.test.java.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author :LiuJie 2015年8月12日 下午1:41:35
 * @注释:Json解析
 */
public class JsonTest {

	public static void main(String[] args) {
      
		//
		TestJsonDateList();
	}
	
	//解析数组形式的json格式
	public  static void  TestJsonDateList(){
		String json="[{\"account\":\"200040149\",\"platform\":\"B2B\"},{\"account\":\"YINGP\",\"master\":\"UAS\",\"platform\":\"ERP\",\"website\":\"http://218.17.158.219:8090/ERP/\"},{\"account\":\"ADMIN\",\"master\":\"SAAS_10041166\",\"platform\":\"ERP\",\"website\":\"http://aaa.saas.ubtob.com\"}]";
		System.out.println(json);
		List<?> logMsg= (List<?>) FlexJsonUtil.fromJson(json,ArrayList.class);
		System.out.println(logMsg.size());
		if (logMsg!=null&&!logMsg.isEmpty()) {
			for (int i = 0; i <logMsg.size(); i++) {
				@SuppressWarnings("unchecked")
				Map<String, Object> map=(HashMap<String, Object>)logMsg.get(i);
				if (map.get("platform").toString().equals("ERP")) {
					System.out.println("ERP:"+	map.get("platform").toString());
					System.out.println("ERP:"+	map.get("account").toString());
					System.out.println("ERP:"+	map.get("website").toString());
					System.out.println("ERP:"+	map.get("master").toString());
				}else if (map.get("platform").toString().equals("B2B")) {
					System.out.println(	"B2B:"+	map.get("platform").toString());
					System.out.println(	"B2B:"+	map.get("account").toString());
				}else if (map.get("platform").toString().equals("SAS")) {
					System.out.println("SAS:"+  map.get("platform").toString());
					System.out.println(	"SAS:"+	map.get("account").toString());
					System.out.println("SAS:"+	map.get("website").toString());
				}
				
			}
		}
	}
	
	/**@注释：list 批量删除  */
	public  static void TestJsonData(){
		 ArrayList<String> niArrayList=new ArrayList<String>();
//	       niArrayList.add("niha");
//	       String liString=  FlexJsonUtil.toJsonArray(niArrayList);
//	       List<String> newList=  FlexJsonUtil.fromJsonArray(liString, String.class);
//		   System.out.println(newList.get(0));
			List<String> list=new ArrayList<String>();
			list.add("0");
			list.add("1");
			list.add("2");
			list.add("3");
			list.add("4");
			List<String> list1=new ArrayList<String>();
			list1.add("0");
			list1.add("1");
			list1.add("2");
			list1.add("3");
			list1.add("4");
//			list.remove(2);
//			list.remove(7);
			list.removeAll(list1);
			for (int i = 0; i < list.size(); i++) {
				System.out.println(""+list.get(i));
				
			}
	}

}
