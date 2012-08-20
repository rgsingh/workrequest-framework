package com.rgsinfotech.workqueue.service;

import java.util.List;

import javax.naming.ServiceUnavailableException;

import org.slf4j.LoggerFactory;

public class SimpleMultiplierService<T> implements Service<T> {

	private T result;
	
	public void init() {

	}

	@SuppressWarnings("unchecked")
	public void send(T data) throws ServiceUnavailableException {
		LoggerFactory.getLogger(getClass().getName()).debug("Data in: " + data + " in SimpleMultiplierService");
		if (data instanceof Integer) {
			Integer dataInteger = (Integer)data;
			dataInteger *= 1000;
			data = (T) dataInteger;
			result = data;
		}
		LoggerFactory.getLogger(getClass().getName()).debug("Data out: " + data + " in SimpleMultiplierService");
	}
	
	public void send(List<T> data) throws ServiceUnavailableException {
		throw new UnsupportedOperationException("Only a value of type T is supported.");
	}

	public void shutdown() {
		
	}

	public void afterPropertiesSet() throws Exception {
		
	}

	@Override
	public T getResult() throws ServiceUnavailableException {
		return result;
	}

	
	
}
