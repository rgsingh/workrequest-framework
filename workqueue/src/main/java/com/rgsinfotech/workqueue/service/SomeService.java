package com.rgsinfotech.workqueue.service;

import javax.naming.ServiceUnavailableException;

public class SomeService implements Service{

	public void execute() throws ServiceUnavailableException {
		
		throw new ServiceUnavailableException("Oops. We cannot service your request because we are down!");
		
	}

	
	
}
