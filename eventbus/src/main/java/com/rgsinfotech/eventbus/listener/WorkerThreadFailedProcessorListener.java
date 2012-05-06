package com.rgsinfotech.eventbus.listener;

import org.slf4j.LoggerFactory;

import com.rgsinfotech.eventbus.event.WorkerThreadFailedEvent;

public class WorkerThreadFailedProcessorListener implements Listener<WorkerThreadFailedEvent> {
	public void process(WorkerThreadFailedEvent event){
		System.out.println("WorkerThreadFailedEvent.servicedBy: " + event.getServicedBy() + ", WorkerThreadFailedEvent.workKey: " + event.getKey());
		
		LoggerFactory.getLogger(getClass().getName()).debug("WorkerThreadFailedEvent.servicedBy: " + event.getServicedBy() + ", WorkerThreadFailedEvent.workKey: " + event.getKey());
	}
}
