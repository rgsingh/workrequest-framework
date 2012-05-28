package com.rgsinfotech.workqueue.service;

import java.util.ArrayList;
import java.util.List;

import javax.naming.ServiceUnavailableException;

import com.rgsinfotech.eventbus.api.EventDispatcher;
import com.rgsinfotech.eventbus.event.WorkQueueServerSenderEvent;
import com.rgsinfotech.eventbus.listener.Listener;


/**
 * Responsible for consuming a DTO representing the data to be provided to this service
 * which is used to perform work. In this case, work is to delegate to a simple event
 * dispatching mechanism which will in turn notify a daemon service via a listener interface.
 * 
 * @author Rai Singh
 *
 * 
 */
public class WorkInjectionService<T> implements Service<String> {

	private EventDispatcher<WorkQueueServerSenderEvent> workQueueServerSenderEventDispatcher;
	private Listener<WorkQueueServerSenderEvent> workQueueServerSenderListener;

	public void init() {

	}

	/**
	 * Method invoked which indirectly interacts with a corresponding server service via
	 * an internal event bus.
	 * 
	 * @param data The raw DTO payload which is post-processed within this method.
	 */
	public void send(String data) throws ServiceUnavailableException {

		String[] values = data.split(",");
		List<Integer> dataSplit = new ArrayList<Integer>();
		for (int i = 0; i < values.length; i++) {
			dataSplit.add(Integer.parseInt(values[i]));
		}

		getWorkQueueServerSenderEventDispatcher().addListener(
				getWorkQueueServerSenderListener());

		getWorkQueueServerSenderEventDispatcher().dispatchEvent(
				new WorkQueueServerSenderEvent(dataSplit, getClass()
						.getName(), "<some transaction id>"));

	}

	public void send(List<String> data) throws ServiceUnavailableException {

	}

	public void shutdown() {

	}

	public void afterPropertiesSet() throws Exception {

	}

	public EventDispatcher<WorkQueueServerSenderEvent> getWorkQueueServerSenderEventDispatcher() {
		return workQueueServerSenderEventDispatcher;
	}

	public void setWorkQueueServerSenderEventDispatcher(
			EventDispatcher<WorkQueueServerSenderEvent> workQueueServerSenderEventDispatcher) {
		this.workQueueServerSenderEventDispatcher = workQueueServerSenderEventDispatcher;
	}

	public Listener<WorkQueueServerSenderEvent> getWorkQueueServerSenderListener() {
		return workQueueServerSenderListener;
	}

	public void setWorkQueueServerSenderListener(
			Listener<WorkQueueServerSenderEvent> workQueueServerSenderListener) {
		this.workQueueServerSenderListener = workQueueServerSenderListener;
	}

}
