package com.rgsinfotech.eventbus.listener;

import org.slf4j.LoggerFactory;

import com.rgsinfotech.eventbus.event.WorkRequestEvent;

public class WorkRequestProcessorListener implements Listener<WorkRequestEvent> {

	public void process(WorkRequestEvent event){
		System.out.println("WorkRequestEvent.servicedBy: " + event.getServicedBy() + ", WorkRequestEvent.workKey: " + event.getKey());
		
		LoggerFactory.getLogger(getClass().getName()).debug("WorkRequestEvent.servicedBy: " + event.getServicedBy() + ", WorkRequestEvent.workKey: " + event.getKey());
	}

}
