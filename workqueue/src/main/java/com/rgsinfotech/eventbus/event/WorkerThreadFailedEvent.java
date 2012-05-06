package com.rgsinfotech.eventbus.event;

public class WorkerThreadFailedEvent extends AbstractEvent {

	public WorkerThreadFailedEvent() {}
	
	public WorkerThreadFailedEvent(String createdBy, Object workKey) {
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
