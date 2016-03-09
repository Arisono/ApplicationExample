package com.application.json.alibaba;

import com.alibaba.fastjson.JSON;

/**
  * @author Administrator
  * @功能:阿里的数据解析
  */
public class AlibabaMain {
	public static void main(String[] args) {
		TestJSONPaseType();
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
