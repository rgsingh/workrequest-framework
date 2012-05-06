package com.rgsinfotech.eventbus.event;

public class WorkRequestEvent extends AbstractEvent {

	public WorkRequestEvent() {}
	
	public WorkRequestEvent(String servicedBy, Object workKey) {
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
