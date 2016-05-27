package com.application.api.erp;

import java.util.Date;

import com.application.util.DateFormatUtil;

/**
 * @author :LiuJie 2015年8月25日 下午2:40:14
 * @注释:  测试Http请求的类
 */
public class ErpApiUtilMain {

	public static void main(String[] args) {
		  ErpApiUtil.TestLogin("15989589091", "qwerty");
		 // ErpApiUtil.TestLoginERP("13352991628", "az00213381", "USOFTSYS");
//		startThread();
	}

	private static void startThread() {
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
		
//		while(thread.isAlive()){
//			System.out.println("线程正在运行。。。。。。"+DateFormatUtil.getChineseDateTime(new Date()));
//		}
//		System.out.println("线程结束");
	}
	
}