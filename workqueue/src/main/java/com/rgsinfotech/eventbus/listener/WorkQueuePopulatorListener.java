package com.rgsinfotech.eventbus.listener;

import java.util.List;

import javax.naming.ServiceUnavailableException;

import org.slf4j.LoggerFactory;

import com.rgsinfotech.eventbus.event.WorkQueuePopulatorEvent;
import com.rgsinfotech.eventbus.listener.WorkQueueServerStartListener.WorkQueueServerServiceHolder;

public class WorkQueuePopulatorListener implements
		Listener<WorkQueuePopulatorEvent> {


	
	@SuppressWarnings("unchecked")
	public void process(WorkQueuePopulatorEvent event) {
		
		List<Integer> data = (List<Integer>) event.getCollection();
		try {
			WorkQueueServerServiceHolder.INSTANCE.send(data);	
			
		} catch (ServiceUnavailableException e) {
			LoggerFactory.getLogger(getClass().getName()).error(e.getMessage());
		}
		
	}

}
