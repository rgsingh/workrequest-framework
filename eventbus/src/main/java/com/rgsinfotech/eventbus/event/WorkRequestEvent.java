package com.rgsinfotech.eventbus.event;

public class WorkRequestEvent extends AbstractEvent {

	public WorkRequestEvent() {}
	
	public WorkRequestEvent(Object workKey) {
		this.workKey = workKey;
	}
	
	@Override
	public Object getKey() {
		return workKey;
	}
}
