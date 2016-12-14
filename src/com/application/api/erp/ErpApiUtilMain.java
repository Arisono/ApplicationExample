package com.application.api.erp;

/**
 * @author :LiuJie 2015年8月25日 下午2:40:14
 * @注释:  测试Http请求的类
 */
public class ErpApiUtilMain {

	public static void main(String[] args) {
//		 ErpApiUtil.TestLogin("13416410624", "1");
//		 ErpApiUtil.TestLoginERP("http://218.17.158.219:8090/ERP//","13509628051", "111111", "UAS");
	     ErpApiUtil.TestLoginB2B("http://113.105.74.135:8001/sso/login",
	    		 "15012345678", 
	    		 "111111");

	}
}
