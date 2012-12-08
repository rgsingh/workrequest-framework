package com.rgsinfotech.workqueue.client.remote;

import java.rmi.RemoteException;

import javax.naming.ServiceUnavailableException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rgsinfotech.workqueue.remote.service.Service;

public class Client {
	
	public static void main(String[] args) throws ServiceUnavailableException, RemoteException {
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/spring/activemq-workqueue-frontend.xml");
		ctx.start();
		
		try {
			@SuppressWarnings("unchecked")
			Service<Integer> service = (Service<Integer>) ctx.getBean("workQueueServiceProxy");

			System.out.println("Calling service...");
			service.send(new Integer(450000000));
			System.out.println("Service call result... " + service.getResult());
			System.out.println("Service call done.");
		} catch (Exception e) {
			System.out.println("Problem calling service send method : " + e.getMessage()); // just for testing anyway.
		} finally {
			if (ctx != null) {
				ctx.stop();				
			}
		}
		
		
	}

}
