package com.rgsinfotech.workqueue.service;

import javax.naming.ServiceUnavailableException;

public interface Service {

	public void execute() throws ServiceUnavailableException;
}
