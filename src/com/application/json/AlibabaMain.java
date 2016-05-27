package com.application.json;



import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

/**
  * @author Administrator
  * @功能:阿里的数据解析
  */
public class AlibabaMain {
	public static void main(String[] args) {
		 ArrayList<ArrayList<String>>  gridlists = new ArrayList<ArrayList<String>>();
		 String gridData="[[\"\",\"供应商\",\"采购额\"],[\"1\",\"胜芳\",\"152,002\"],[\"2\",\"B2B平台\",\"8,775\"],[\"3\",\"峰闵\",\"2,000\"],[\"4\",\"扬宇\",\"11\"]]";
		 gridlists=(ArrayList) JSON.parseArray(gridData,ArrayList.class);
		 
		 for(int i=0;i<gridlists.size();i++){
			 for (int j = 0; j < gridlists.get(i).size(); j++) {
				System.out.println(gridlists.get(i).get(j).toString());
				
			}
		 }
		 	
		
	}
    
	public static void TestJSONParseAarray(){
		String data="{\"fpd_data\":[[16318,1]],\"success\":true}";
		System.out.println(JSON.parseObject(data).getJSONArray("fpd_data").getJSONArray(0).get(1));
	}


	public static void TestJSONPaseType(){
		//根节点   ----------对象  { .....  }
		String data="{\"\":0,\"null\":\"\",\"age\":\"\",\"age1\":null,\"age2\":0,\"list\":[{\"\":null},null,9,{\"3\":\"12\"}]}";
		
		System.out.println(JSON.parseObject(data).get(""));
		System.out.println(JSON.parseObject(data).get("null"));
		System.out.println(JSON.parseObject(data).get("age1"));
		System.out.println(JSON.parseObject(data).get("age2"));
	
	}
//	{
//	  "":0,
//	  "null":"",
//	  "age":"",
//	  "age1":null,
//	  "age2":0,
//	  "list":[
//	     { "":null
//	     },
//	     null,
//	     9,
//	     {"3":"12"}
//	    ]
//	}
//
}
