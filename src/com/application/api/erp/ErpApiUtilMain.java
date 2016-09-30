package com.application.api.erp;

/**
 * @author :LiuJie 2015年8月25日 下午2:40:14
 * @注释:  测试Http请求的类
 */
public class ErpApiUtilMain {

	public static void main(String[] args) {

		 ErpApiUtil.TestLogin("13416410624", "1");
		 ErpApiUtil.TestLoginERP("http://218.17.158.219:8090/ERP//","13509628051", "111111", "UAS");
	//     ErpApiUtil.TestLoginB2B("http://uas.ubtob.com/j_spring_security_check","13266699268", "1");
	
	}

	public static void startThread() {
		Thread thread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					try {
						 ErpApiUtil.TestLogin("13725547065", "111111");
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
	}
	
}
