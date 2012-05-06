package com.rgsinfotech.eventbus.event;

public class WorkQueueServerStartEvent extends AbstractEvent {

	public WorkQueueServerStartEvent() {}
	
	public WorkQueueServerStartEvent(String createdBy, Object workKey) {
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
