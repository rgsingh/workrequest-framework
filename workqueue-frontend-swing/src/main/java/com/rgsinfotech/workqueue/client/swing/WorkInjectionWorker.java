package com.rgsinfotech.workqueue.client.swing;

import java.rmi.RemoteException;

import javax.naming.ServiceUnavailableException;
import javax.swing.SwingWorker;

import org.slf4j.LoggerFactory;

import com.rgsinfotech.workqueue.service.Service;

/*
 * This class can only be executed once. A new instance will need to be 
 * created in order to invoke the execute method.
 */
public class WorkInjectionWorker extends SwingWorker<Void, Void> {

	private String injectedWork;
	private Service<String> workInjectionService;

	@Override
	protected Void doInBackground() throws Exception {

		populateWorkQueue();

		return null;
	}

	private void populateWorkQueue() {

		try {
			getWorkInjectionService().send(getInjectedWork());
		} catch (ServiceUnavailableException e) {
			LoggerFactory
					.getLogger(getClass().getName())
					.error("WorkInjectionWorker error sending data to WorkInjectionService.",
							e);
		} catch (RemoteException e) {
			LoggerFactory
			.getLogger(getClass().getName())
			.error("WorkInjectionWorker error sending data to WorkInjectionService.",
					e);
		}

	}

	public String getInjectedWork() {
		return injectedWork;
	}

	public void setInjectedWork(String injectedWork) {
		this.injectedWork = injectedWork;
	}

	public Service<String> getWorkInjectionService() {
		return workInjectionService;
	}

	public void setWorkInjectionService(
			Service<String> workInjectionService) {
		this.workInjectionService = workInjectionService;
	}
}
