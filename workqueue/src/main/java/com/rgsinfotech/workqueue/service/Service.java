package com.rgsinfotech.workqueue.service;

import java.rmi.RemoteException;
import java.util.List;

import javax.naming.ServiceUnavailableException;

import org.springframework.beans.factory.InitializingBean;

public interface Service<T> extends InitializingBean {

	public void init() throws RemoteException;

	public void send(T data) throws ServiceUnavailableException,
			RemoteException;

	public void send(List<T> data) throws ServiceUnavailableException,
			RemoteException;

	public T getResult() throws ServiceUnavailableException, RemoteException;

	public void shutdown() throws RemoteException;
}
