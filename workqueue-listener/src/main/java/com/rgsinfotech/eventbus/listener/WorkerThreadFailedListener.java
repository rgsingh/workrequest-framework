package com.rgsinfotech.eventbus.listener;

import org.slf4j.LoggerFactory;

import com.rgsinfotech.eventbus.api.EventDispatcher;
import com.rgsinfotech.eventbus.event.Event;
import com.rgsinfotech.workqueue.event.EventDefinitions;

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
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() {
		EventDispatcher.getInstance().addListener(
				EventDefinitions.EVENT_WORKER_THREAD_FAILED, this);
	}

}
