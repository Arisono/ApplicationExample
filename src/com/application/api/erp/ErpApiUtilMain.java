package com.application.api.erp;

/**
 * @author :LiuJie 2015年8月25日 下午2:40:14
 * @注释:  测试Http请求的类
 */
public class ErpApiUtilMain {

	public static void main(String[] args) {
		 ErpApiUtil.TestLogin("18666222419", "123456");
		 ErpApiUtil.TestLoginERP("http://218.17.161.61:8099/ERP/","18666222419", "123456", "NDataCenter");
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
