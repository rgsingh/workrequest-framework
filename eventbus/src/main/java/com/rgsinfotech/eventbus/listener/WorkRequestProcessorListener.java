package com.rgsinfotech.eventbus.listener;

import com.rgsinfotech.eventbus.event.WorkRequestEvent;

public class WorkRequestProcessorListener implements Listener<WorkRequestEvent> {

	public void process(WorkRequestEvent event){
		System.out.println("WorkRequestEvent occurred: " + event + ", WorkRequestEvent.workKey: " + event.getKey());
	}

}
