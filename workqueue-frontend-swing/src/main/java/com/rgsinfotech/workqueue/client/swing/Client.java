package com.rgsinfotech.workqueue.client.swing;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
	
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/spring/swing-frontend.xml");
		ctx.start();
		
	}

}
