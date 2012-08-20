package com.rgsinfotech.workqueue.service;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.ServiceUnavailableException;

import com.rgsinfotech.eventbus.api.EventDispatcher;
import com.rgsinfotech.eventbus.event.Event;
import com.rgsinfotech.eventbus.event.EventDefinitions;
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

	private String result;
	private Listener<Event> workQueueServerSenderListener;

	public void init() {

	}

	/**
	 * Method invoked which indirectly interacts with a corresponding server service via
	 * an internal event bus.
	 * 
	 * @param data The raw DTO payload which is post-processed within this method.
	 */
	@SuppressWarnings("unchecked")
	public void send(String data) throws ServiceUnavailableException {


        String[] values = data.split(",");		
		List<Integer> dataSplit = new ArrayList<Integer>();
		for (int i = 0; i < values.length; i++) {
			dataSplit.add(Integer.parseInt(values[i]));
		}

		
		//FIXME This is not going to work since "data" should be a valid EventDefinition entry.
		//Store the state (i.e. "data") in XML backed by an XSD that defines named payloads and
		//attributes. That XML request/response will be mapped to the message bus used to route
		//messages throughout a system (e.g. Event Bus<=>Apache Camel=>ActiveMQ using Spring Remoting).
		Event event = new Event(EventDefinitions.EVENT_CLIENT_REQUEST_SENT, getClass()
				.getName());
		event.getPayloadIdSequence().addAll(dataSplit);
		EventDispatcher.getInstance().dispatchEvent(event);

	}

	public void send(List<String> data) throws ServiceUnavailableException {

	}

	public void shutdown() {

	}

	public void afterPropertiesSet() throws Exception {

	}

	public Listener<Event> getWorkQueueServerSenderListener() {
		return workQueueServerSenderListener;
	}

	public void setWorkQueueServerSenderListener(
			Listener<Event> workQueueServerSenderListener) {
		this.workQueueServerSenderListener = workQueueServerSenderListener;
	}

	@Override
	public String getResult() throws ServiceUnavailableException,
			RemoteException {
		// TODO Auto-generated method stub
		return result;
	}

}
