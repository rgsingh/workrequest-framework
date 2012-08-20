package com.rgsinfotech.workqueue;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.ServiceUnavailableException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rgsinfotech.workqueue.service.Service;

public class Client {
	
	public static void main(String[] args) throws ServiceUnavailableException, RemoteException {
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/spring/workqueue-client.xml");
		ctx.start();
		
		Service service = (Service) ctx.getBean("workQueueServiceProxy");

		System.out.println("Calling service...");
		service.send(new Integer(45));
		System.out.println("Service call result... " + service.getResult());
		System.out.println("Service call done.");
		
	}

}
