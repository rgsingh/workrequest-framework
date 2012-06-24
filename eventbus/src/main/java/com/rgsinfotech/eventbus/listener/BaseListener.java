package com.rgsinfotech.eventbus.listener;

import com.rgsinfotech.eventbus.event.Event;

public abstract class BaseListener implements Listener<Event>{
	
	public BaseListener() {
		init();
	}	

	
}
