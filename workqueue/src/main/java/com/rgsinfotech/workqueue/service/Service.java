package com.rgsinfotech.workqueue.service;

import java.util.List;

import javax.naming.ServiceUnavailableException;

public interface Service<T> {

	public void init();
	public void send(T data) throws ServiceUnavailableException;
	public void send(List<T> data) throws ServiceUnavailableException;
	public void shutdown();
}
