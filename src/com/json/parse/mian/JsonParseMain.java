package com.json.parse.mian;

import com.alibaba.fastjson.JSON;

public class JsonParseMain {

	public static void main(String[] args) {
      String json="{\"taskmsg\":[{\"内容\":\"好的\\n\",\"录入人\":\"陈璐\",\"时间\":\"2016-01-20 14:47:33\"}],\"success\":true}";
	  System.out.println(JSON.parseObject(json).getJSONArray("taskmsg").getJSONObject(0).getString("内容"));
	}

}
