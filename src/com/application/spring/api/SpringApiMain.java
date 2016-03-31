package com.application.spring.api;

import com.application.base.SpringApiBase;

public class SpringApiMain extends SpringApiBase{
 

	public static void main(String[] args) {
		url=url+"/simple1/save/";
		param.put("name", "妮娜");
		param.put("password", "1w2sd345621");
		load(url, param, headers, null, "post");
	}

	
}
