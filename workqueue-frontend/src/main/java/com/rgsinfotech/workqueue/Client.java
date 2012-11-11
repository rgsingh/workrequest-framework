package com.rgsinfotech.workqueue;

import java.rmi.RemoteException;

import javax.naming.ServiceUnavailableException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rgsinfotech.workqueue.service.Service;

public class Client {
	
	public static void main(String[] args) throws ServiceUnavailableException, RemoteException {
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/spring/activemq-workqueue-frontend.xml");
		ctx.start();
		
		@SuppressWarnings("unchecked")
		Service<Integer> service = (Service<Integer>) ctx.getBean("workQueueServiceProxy");

		System.out.println("Calling service...");
		service.send(new Integer(45));
		System.out.println("Service call result... " + service.getResult());
		System.out.println("Service call done.");
		
	}

}
