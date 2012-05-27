package com.rgsinfotech.workqueue.ui;

import org.springframework.context.support.ClassPathXmlApplicationContext;



public class Main {

	public static void main(String[] args) {
        String[] contextPaths = new String[]{"META-INF/spring/workqueue-context.xml"};
        new ClassPathXmlApplicationContext(contextPaths);
	}

}
