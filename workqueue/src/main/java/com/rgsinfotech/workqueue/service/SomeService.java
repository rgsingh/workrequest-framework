package com.rgsinfotech.workqueue.service;

import java.util.List;

import javax.naming.ServiceUnavailableException;

import org.slf4j.LoggerFactory;

public class SomeService<T> implements Service<T> {

	public void init() {

	}

	@SuppressWarnings("unchecked")
	public void send(T data) throws ServiceUnavailableException {
		LoggerFactory.getLogger(getClass().getName()).debug("Data in: " + data + " in SomeService");
		if (data instanceof Integer) {
			Integer dataInteger = (Integer)data;
			dataInteger *= 1000;
			data = (T) dataInteger;
		}
		LoggerFactory.getLogger(getClass().getName()).debug("Data out: " + data + " in SomeService");
	}
	
	public void send(List<T> data) throws ServiceUnavailableException {
		throw new UnsupportedOperationException("Only a value of type T is supported.");
	}

	public void shutdown() {
		
	}

	
	
}
