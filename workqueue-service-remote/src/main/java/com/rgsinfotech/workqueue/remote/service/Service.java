package com.rgsinfotech.workqueue.remote.service;

import java.util.List;

import javax.naming.ServiceUnavailableException;

import org.springframework.beans.factory.InitializingBean;

public interface Service<T> extends InitializingBean {

	public void init();

	public void send(T data) throws ServiceUnavailableException;

	public void send(List<T> data) throws ServiceUnavailableException;

	public T getResult() throws ServiceUnavailableException;

	public void shutdown();
}
