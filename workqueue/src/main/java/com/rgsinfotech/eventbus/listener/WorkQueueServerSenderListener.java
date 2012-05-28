package com.rgsinfotech.eventbus.listener;

import java.util.List;

import javax.naming.ServiceUnavailableException;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.rgsinfotech.eventbus.event.WorkQueueServerSenderEvent;
import com.rgsinfotech.workqueue.service.WorkQueueServerService;

public class WorkQueueServerSenderListener implements
		Listener<WorkQueueServerSenderEvent>, InitializingBean   {

	
	private WorkQueueServerService<Integer> workQueueServerService;
	
	
	public WorkQueueServerSenderListener() {
		
	}
	
	
	public WorkQueueServerService<Integer> getWorkQueueServerService() {
		return workQueueServerService;
	}


	public void setWorkQueueServerService(
			WorkQueueServerService<Integer> workQueueServerService) {
		this.workQueueServerService = workQueueServerService;
	}



	@SuppressWarnings("unchecked")
	public void process(WorkQueueServerSenderEvent event) {
		
		List<Integer> data = (List<Integer>) event.getCollection();
		try {
			workQueueServerService.send(data);	
			
		} catch (ServiceUnavailableException e) {
			LoggerFactory.getLogger(getClass().getName()).error(e.getMessage());
		}
		
	}

	public void afterPropertiesSet() throws Exception {
		
	}

}
