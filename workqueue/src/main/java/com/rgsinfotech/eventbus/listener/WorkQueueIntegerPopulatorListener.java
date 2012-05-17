package com.rgsinfotech.eventbus.listener;

import java.util.List;

import javax.naming.ServiceUnavailableException;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.rgsinfotech.eventbus.event.WorkQueueIntegerPopulatorEvent;
import com.rgsinfotech.workqueue.service.WorkQueueServerService;

public class WorkQueueIntegerPopulatorListener implements
		Listener<WorkQueueIntegerPopulatorEvent>, InitializingBean   {

	
	private WorkQueueServerService<Integer> workQueueServerService;
	
	
	public WorkQueueIntegerPopulatorListener() {
		
	}
	
	
	public WorkQueueServerService<Integer> getWorkQueueServerService() {
		return workQueueServerService;
	}


	public void setWorkQueueServerService(
			WorkQueueServerService<Integer> workQueueServerService) {
		this.workQueueServerService = workQueueServerService;
	}



	@SuppressWarnings("unchecked")
	public void process(WorkQueueIntegerPopulatorEvent event) {
		
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
