package com.application.json;



import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

/**
  * @author Administrator
  * @功能:阿里的数据解析
  */
public class AlibabaMain {
	public static void main(String[] args) {
		testNull();
		
	}
	
	private static void testNull() {
		String json="{\"success\":true,\"combo\":[],\"count\":null}";
	    JSONArray array=JSON.parseObject(json).getJSONArray("combo");
	   
        System.out.println(array==null);
        System.out.println(array.isEmpty());
        System.out.println(array.size());
        System.out.println(JSON.parseObject(json).getIntValue("count"));
        //注意防止异常
        //System.out.println(array.getJSONObject(2));
	}
	/**
	 * 去对象JSONObject-数组JSONArray-对象JSONObject-字符串String
	 */
	public static void JSONParseArray(){
		String json="{\"success\":true,\"stages\":[{\"BS_ID\":221,\"BS_CODE\":\"2016050002\",\"BS_NAME\":\"初次拜访\",\"BS_REMARK\":null,\"BS_RELATIVEITEM\":null,\"BS_COLOR\":\"00FFFF\",\"BS_DETNO\":0},{\"BS_ID\":1,\"BS_CODE\":\"2015080001\",\"BS_NAME\":\"送样\",\"BS_REMARK\":\"测试111\",\"BS_RELATIVEITEM\":\"Sample\",\"BS_COLOR\":\"86A723\",\"BS_DETNO\":1},{\"BS_ID\":2,\"BS_CODE\":\"2015080002\",\"BS_NAME\":\"报价\",\"BS_REMARK\":\"测试\",\"BS_RELATIVEITEM\":\"Quote\",\"BS_COLOR\":\"306DA6\",\"BS_DETNO\":2},{\"BS_ID\":3,\"BS_CODE\":\"2015080003\",\"BS_NAME\":\"下单\",\"BS_REMARK\":\"测试\",\"BS_RELATIVEITEM\":\"Sale\",\"BS_COLOR\":\"556B2F\",\"BS_DETNO\":3},{\"BS_ID\":4,\"BS_CODE\":\"2015080004\",\"BS_NAME\":\"出货\",\"BS_REMARK\":\"测试4\",\"BS_RELATIVEITEM\":\"Shipment\",\"BS_COLOR\":\"EE7942\",\"BS_DETNO\":4},{\"BS_ID\":183,\"BS_CODE\":\"2016010003\",\"BS_NAME\":\"测试\",\"BS_REMARK\":null,\"BS_RELATIVEITEM\":\"Shipment\",\"BS_COLOR\":\"FF6600\",\"BS_DETNO\":12},{\"BS_ID\":241,\"BS_CODE\":\"2016070001\",\"BS_NAME\":\"初次沟通\",\"BS_REMARK\":null,\"BS_RELATIVEITEM\":null,\"BS_COLOR\":\"FF99CC\",\"BS_DETNO\":51},{\"BS_ID\":242,\"BS_CODE\":\"2016070002\",\"BS_NAME\":\"产品演示\",\"BS_REMARK\":null,\"BS_RELATIVEITEM\":null,\"BS_COLOR\":\"FFCC99\",\"BS_DETNO\":52},{\"BS_ID\":243,\"BS_CODE\":\"2016070003\",\"BS_NAME\":\"理想评估\",\"BS_REMARK\":null,\"BS_RELATIVEITEM\":null,\"BS_COLOR\":\"FFFF99\",\"BS_DETNO\":53},{\"BS_ID\":244,\"BS_CODE\":\"2016070004\",\"BS_NAME\":\"需求分析\",\"BS_REMARK\":null,\"BS_RELATIVEITEM\":null,\"BS_COLOR\":\"CCFFCC\",\"BS_DETNO\":54},{\"BS_ID\":245,\"BS_CODE\":\"2016070005\",\"BS_NAME\":\"样品报价\",\"BS_REMARK\":null,\"BS_RELATIVEITEM\":null,\"BS_COLOR\":\"CCFFFF\",\"BS_DETNO\":55},{\"BS_ID\":246,\"BS_CODE\":\"2016070006\",\"BS_NAME\":\"商务谈判\",\"BS_REMARK\":null,\"BS_RELATIVEITEM\":null,\"BS_COLOR\":\"99CCFF\",\"BS_DETNO\":56},{\"BS_ID\":247,\"BS_CODE\":\"2016070007\",\"BS_NAME\":\"合同签约\",\"BS_REMARK\":null,\"BS_RELATIVEITEM\":null,\"BS_COLOR\":\"CC99FF\",\"BS_DETNO\":57},{\"BS_ID\":248,\"BS_CODE\":\"2016070008\",\"BS_NAME\":\"完成交易\",\"BS_REMARK\":null,\"BS_RELATIVEITEM\":null,\"BS_COLOR\":\"FF00FF\",\"BS_DETNO\":58},{\"BS_ID\":249,\"BS_CODE\":\"2016070009\",\"BS_NAME\":\"多次交易\",\"BS_REMARK\":null,\"BS_RELATIVEITEM\":null,\"BS_COLOR\":\"FF9900\",\"BS_DETNO\":59}]}";
		JSONArray array=JSON.parseObject(json).getJSONArray("stages");
		ArrayList<String> items=new ArrayList<String>();
		for (int i = 0; i < array.size(); i++) {
			items.add(array.getJSONObject(i).getString("BS_NAME"));
		}
		for (String string : items) {
			System.out.println(string);
		}
	}
    
	public static void TestJSONParseAarray(){
		String data="{\"fpd_data\":[[16318,1]],\"success\":true}";
		System.out.println(JSON.parseObject(data).getJSONArray("fpd_data").getJSONArray(0).get(1));
	}
	public static void TestJSONPaseType(){
		String data="{\"\":0,\"null\":\"\",\"age\":\"\",\"age1\":null,\"age2\":0,\"list\":[{\"\":null},null,9,{\"3\":\"12\"}]}";
		System.out.println(JSON.parseObject(data).get(""));
		System.out.println(JSON.parseObject(data).get("null"));
		System.out.println(JSON.parseObject(data).get("age1"));
		System.out.println(JSON.parseObject(data).get("age2"));
	
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void testJsonOne() {
		ArrayList<ArrayList<String>>  gridlists = new ArrayList<ArrayList<String>>();
		 String gridData="[[\"\",\"供应商\",\"采购额\"],[\"1\",\"胜芳\",\"152,002\"],[\"2\",\"B2B平台\",\"8,775\"],[\"3\",\"峰闵\",\"2,000\"],[\"4\",\"扬宇\",\"11\"]]";
		 gridlists=(ArrayList) JSON.parseArray(gridData,ArrayList.class);
		 
		 for(int i=0;i<gridlists.size();i++){
			 for (int j = 0; j < gridlists.get(i).size(); j++) {
				System.out.println(gridlists.get(i).get(j).toString());
				
			}
		 }
	}
}
