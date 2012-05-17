package com.rgsinfotech.workqueue.ui;

import org.springframework.beans.factory.InitializingBean;

import com.rgsinfotech.eventbus.api.EventDispatcher;
import com.rgsinfotech.eventbus.listener.WorkQueueIntegerPopulatorListener;

public class SimpleEventProducer implements InitializingBean {

	WorkQueueIntegerPopulatorListener integerPopulatorListener;
	EventDispatcher eventDispatcher;

	public SimpleEventProducer() {
	}

	public WorkQueueIntegerPopulatorListener getIntegerPopulatorListener() {
		return integerPopulatorListener;
	}

	public EventDispatcher getEventDispatcher() {
		return eventDispatcher;
	}

	public void setIntegerPopulatorListener(
			WorkQueueIntegerPopulatorListener integerPopulatorListener) {
		this.integerPopulatorListener = integerPopulatorListener;
	}

	public void setEventDispatcher(EventDispatcher eventDispatcher) {
		this.eventDispatcher = eventDispatcher;
	}

	public void afterPropertiesSet() throws Exception {
		eventDispatcher.addListener(integerPopulatorListener);
	}

}
