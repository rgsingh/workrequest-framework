package com.rgsinfotech.eventbus.listener;

import org.slf4j.LoggerFactory;

import com.rgsinfotech.eventbus.event.Event;

public class WorkerThreadFailedListener extends EventListener {
	public WorkerThreadFailedListener() {
		
	}

	public void process(Event event) {
		System.out.println("Event.detail: "
				+ event.getDetail() + ", Event.workKey: "
				+ event.getKey());

		LoggerFactory.getLogger(getClass().getName()).debug(
				"Event.detail: " + event.getDetail()
						+ ", Event.workKey: "
						+ event.getKey());
	}


}
