package com.rgsinfotech.eventbus.listener;

import java.rmi.RemoteException;
import java.util.List;

import javax.naming.ServiceUnavailableException;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.rgsinfotech.eventbus.api.EventDispatcher;
import com.rgsinfotech.eventbus.event.Event;
import com.rgsinfotech.workqueue.event.EventDefinitions;
import com.rgsinfotech.workqueue.remote.service.Service;

public class WorkQueueServerSenderListener extends
		EventListener implements InitializingBean   {
	
	public WorkQueueServerSenderListener() {
	}

	private Service<Integer> workQueueServerService;

	
	public Service<Integer> getWorkQueueServerService() {
		return workQueueServerService;
	}

	public void setWorkQueueServerService(
			Service<Integer> workQueueServerService) {
		this.workQueueServerService = workQueueServerService;
	}
	
	public void process(Event event) {
		
		List<Integer> data = (List<Integer>) event.getPayloadIdSequence();
		try {
			workQueueServerService.send(data);	
			
		} catch (ServiceUnavailableException e) {
			LoggerFactory.getLogger(getClass().getName()).error(e.getMessage());
		} 
		
	}

	public void afterPropertiesSet() throws Exception {
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() {
		super.init();
		EventDispatcher.getInstance().addListener(EventDefinitions.EVENT_CLIENT_REQUEST_SENT, this);
	}

}
