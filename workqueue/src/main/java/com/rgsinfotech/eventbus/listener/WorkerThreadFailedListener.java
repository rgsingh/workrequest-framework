package com.rgsinfotech.eventbus.listener;

import org.slf4j.LoggerFactory;

import com.rgsinfotech.eventbus.event.WorkerThreadFailedEvent;

public class WorkerThreadFailedListener implements
		Listener<WorkerThreadFailedEvent> {
	public void process(WorkerThreadFailedEvent event) {
		System.out.println("WorkerThreadFailedEvent.createdBy: "
				+ event.getCreatedBy() + ", WorkerThreadFailedEvent.workKey: "
				+ event.getKey());

		LoggerFactory.getLogger(getClass().getName()).debug(
				"WorkerThreadFailedEvent.createdBy: " + event.getCreatedBy()
						+ ", WorkerThreadFailedEvent.workKey: "
						+ event.getKey());
	}
}
