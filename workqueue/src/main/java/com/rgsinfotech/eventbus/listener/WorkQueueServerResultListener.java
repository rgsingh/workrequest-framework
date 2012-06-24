package com.rgsinfotech.eventbus.listener;

import org.springframework.beans.factory.InitializingBean;

import com.rgsinfotech.eventbus.event.Event;

public class WorkQueueServerResultListener extends
		EventListener implements InitializingBean {


	public WorkQueueServerResultListener() {
	}

	public void afterPropertiesSet() throws Exception {

	}

	@Override
	public void process(Event event) {
		
	}


}
