package com.application.java.io;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author :Administrator 2016年5月5日 上午9:36:56
 * @注释:测试java文件流IO领域
 */
public class IOMain {

	public static void main(String[] args) {
		 ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);  
		 for (int i = 0; i < 1500; i++) {
			   final int index = i;  
			  fixedThreadPool.execute(new Runnable() {
					
					@Override
					public void run() {
						System.out.println("*******"+index+"*********");
		                IOUtil.getInputStreaForFile();				
					}
				});
		}
	}

}
