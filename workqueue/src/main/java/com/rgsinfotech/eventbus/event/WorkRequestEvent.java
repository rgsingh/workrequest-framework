package com.rgsinfotech.eventbus.event;

public class WorkRequestEvent extends AbstractEvent {

	public WorkRequestEvent() {}
	
	public WorkRequestEvent(String createdBy, Object workKey) {
		this.createdBy = createdBy;
		this.workKey = workKey;
	}
	
	@Override
	public Object getKey() {
		return workKey;
	}

	@Override
	public String getCreatedBy() {
		return createdBy;
	}	
	
}
