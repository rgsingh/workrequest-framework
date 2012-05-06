package com.rgsinfotech.eventbus.listener;

import java.util.List;

import javax.naming.ServiceUnavailableException;

import org.slf4j.LoggerFactory;

import com.rgsinfotech.eventbus.event.WorkQueuePopulatorEvent;
import com.rgsinfotech.workqueue.service.WorkQueueServerService;

public class WorkQueuePopulatorProcessorListener implements
		Listener<WorkQueuePopulatorEvent> {

	private static class WorkQueueServerServiceHolder {
		public static final WorkQueueServerService<Integer> INSTANCE = new WorkQueueServerService<Integer>();
	}
	
	@SuppressWarnings("unchecked")
	public void process(WorkQueuePopulatorEvent event) {
		
		WorkQueueServerService<Integer> queueServerService = WorkQueueServerServiceHolder.INSTANCE;
		
		
		List<Integer> data = (List<Integer>) event.getCollection();
		try {
			queueServerService.send(data);	
			
		} catch (ServiceUnavailableException e) {
			LoggerFactory.getLogger(getClass().getName()).error(e.getMessage());
		}
		
	}

}
