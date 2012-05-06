package com.rgsinfotech.eventbus.event;

public class WorkerThreadFailedEvent extends AbstractEvent {

	public WorkerThreadFailedEvent() {}
	
	public WorkerThreadFailedEvent(String servicedBy, Object workKey) {
		this.servicedBy = servicedBy;
		this.workKey = workKey;
	}
	
	@Override
	public Object getKey() {
		return workKey;
	}

	@Override
	public String getServicedBy() {
		return servicedBy;
	}	

}
