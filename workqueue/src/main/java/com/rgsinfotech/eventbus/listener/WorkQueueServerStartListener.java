package com.rgsinfotech.eventbus.listener;

import org.slf4j.LoggerFactory;

import com.rgsinfotech.eventbus.event.WorkQueueServerStartEvent;

public class WorkQueueServerStartListener implements Listener<WorkQueueServerStartEvent> {
	
//	protected static class WorkQueueServerServiceHolder {
//		public static final WorkQueueServerService<Integer> INSTANCE = new WorkQueueServerService<Integer>();
//	}
	
	public void process(WorkQueueServerStartEvent event) {
		
//		WorkQueueServerServiceHolder.INSTANCE.init();
		
		System.out.println("WorkQueueServerStartEvent.createdBy: "
				+ event.getCreatedBy() + ", WorkQueueServerStartEvent.workKey: "
				+ event.getKey());

		LoggerFactory.getLogger(getClass().getName()).debug(
				"WorkQueueServerStartEvent.createdBy: " + event.getCreatedBy()
						+ ", WorkQueueServerStartEvent.workKey: " + event.getKey());
	}

}
