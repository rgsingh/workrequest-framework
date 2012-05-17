package com.rgsinfotech.workqueue.ui;

import org.springframework.context.support.ClassPathXmlApplicationContext;



public class Main {

	public static void main(String[] args) {
        String[] contextPaths = new String[]{"META-INF/spring/workqueue-context.xml"};
        new ClassPathXmlApplicationContext(contextPaths);
		
//		Thread producerThread = new Thread(new Runnable() {
//			
//			public void run() {
//				WorkQueueWindow window = new WorkQueueWindow();
//
//				window.show();
//			}
//		});
//
//		producerThread.start();
//		
//
//		try {
//			producerThread.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
