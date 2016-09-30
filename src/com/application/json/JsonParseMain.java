package com.application.json;

import com.alibaba.fastjson.JSON;

public class JsonParseMain {

	public static void main(String[] args) {
		System.out.println(new JsonValidator().validate("{\"exceptionInfo\":\"ERR_POWER_001:您没有<新增>单据的权限!\"}"));
//      String json="{\"taskmsg\":[{\"内容\":\"好的\\n\",\"录入人\":\"陈璐\",\"时间\":\"2016-01-20 14:47:33\"}],\"success\":true}";
//	  System.out.println(JSON.parseObject(json).getJSONArray("taskmsg").getJSONObject(0).getString("内容"));
	}

}
