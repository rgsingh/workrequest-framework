package com.rgsinfotech.eventbus.listener;

import org.slf4j.LoggerFactory;

import com.rgsinfotech.eventbus.api.EventDispatcher;
import com.rgsinfotech.eventbus.event.Event;
import com.rgsinfotech.eventbus.event.EventDefinitions;

public class EventListener extends BaseListener {
	
	public EventListener() {
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() {
		EventDispatcher.getInstance().addListener(EventDefinitions.INTERNAL_EVENT_LISTENER_STARTED, this);
	}

	@Override
	public void process(Event event) {

		 LoggerFactory.getLogger(getClass().getName()).debug("");
		
	}

}
