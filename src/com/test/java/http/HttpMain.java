package com.test.java.http;

import java.util.HashMap;
import java.util.Map;

import com.test.java.util.StaticUtil;

/**
 * @author :LiuJie 2015年10月13日 上午11:52:13
 * @注释:测试http请求工具
 */
public class HttpMain {
    /**
	 * @author LiuJie
	 * @功能:可以设置请求头，设置cookie，get或者是post
	 */
	public static void main(String[] args) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("foo", "A");
        StaticUtil.TestSpringMvc(
    		  "http://localhost:8080/spring-mvc-showcase/mapping/parameter",
    		   param, 
    		   "get");
	}

}
