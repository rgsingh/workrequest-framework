package com.rgsinfotech.eventbus.listener;

import org.slf4j.LoggerFactory;

import com.rgsinfotech.eventbus.api.EventDispatcher;
import com.rgsinfotech.eventbus.event.Event;
import com.rgsinfotech.workqueue.event.EventDefinitions;

public class WorkQueueServerStartListener extends EventListener {

	public WorkQueueServerStartListener() {
	}

	public void process(Event event) {

		System.out.println("WorkQueueServerStartEvent.detail: "
				+ event.getDetail() + ", WorkQueueServerStartEvent.workKey: "
				+ event.getKey());

		LoggerFactory.getLogger(getClass().getName()).debug(
				"WorkQueueServerStartEvent.detail: " + event.getDetail()
						+ ", WorkQueueServerStartEvent.workKey: "
						+ event.getKey());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void init() {
		EventDispatcher.getInstance().addListener(
				EventDefinitions.EVENT_WORK_QUEUE_SERVER_START, this);
	}

}
