package com.application.api.news;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.application.entity.NewEntity;
import com.application.entity.RootEntity;
import com.application.util.HttpUtil.Response;

public class Main {

	public static void main(String[] args) {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
		@SuppressWarnings("unused")
		ExecutorService fixedThreadPool2 = Executors.newFixedThreadPool(1);
		for (int i = 0; i < 155; i++) {
			final int j=i+1;
			fixedThreadPool.execute(new Runnable() {
				
				@Override
				public void run() {
					
					/* try {
						Thread.sleep(500);*/
						Response r1=NewsMain.getNewsList(j, "国内最新");
						RootEntity<NewEntity> data=	NewsMain.parseNewsList(r1, NewEntity.class);
						System.out.println("国内最新："+data.getShowapi_res_body().getPagebean().getCurrentPage());
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}  
				}
			});
			
         /*fixedThreadPool2.execute(new Runnable() {
				
				@Override
				public void run() {
					Response r2= NewsMain.getNewsList(300, "军事焦点");
					RootEntity<NewEntity> data=	NewsMain.parseNewsList(r2, NewEntity.class);
					System.out.println("军事焦点："+data.getShowapi_res_body().getPagebean().getContentlist().get(0).getChannelName());
					 try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}  
				}
			});*/
		}
	}

}
