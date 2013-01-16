package com.rgsinfotech.workqueue.bootstrap;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Server {

	public static void main(String[] args) {
		String[] contextPaths = new String[] { "META-INF/spring/activemq-workqueue-backend.xml" };
		new ClassPathXmlApplicationContext(contextPaths);
	}

}
