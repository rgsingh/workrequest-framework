package com.rgsinfotech.eventbus.listener;

import org.slf4j.LoggerFactory;

import com.rgsinfotech.eventbus.event.WorkQueueServerStartEvent;
import com.rgsinfotech.workqueue.service.WorkQueueServerService;

public class WorkQueueServerStartListener implements Listener<WorkQueueServerStartEvent> {
	
	protected static class WorkQueueServerServiceHolder {
		public static final WorkQueueServerService<Integer> INSTANCE = new WorkQueueServerService<Integer>();
	}
	
	public void process(WorkQueueServerStartEvent event) {
		
		WorkQueueServerService<Integer> queueServerService = WorkQueueServerServiceHolder.INSTANCE;
		
		System.out.println("WorkQueueServerStartEvent.createdBy: "
				+ event.getCreatedBy() + ", WorkQueueServerStartEvent.workKey: "
				+ event.getKey());

		LoggerFactory.getLogger(getClass().getName()).debug(
				"WorkQueueServerStartEvent.createdBy: " + event.getCreatedBy()
						+ ", WorkQueueServerStartEvent.workKey: " + event.getKey());
	}

}
