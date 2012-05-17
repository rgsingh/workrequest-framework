package com.rgsinfotech.eventbus.event;

import java.util.Collection;

public class WorkQueueIntegerPopulatorEvent extends AbstractEvent implements CollectionAwareEvent {
	private Collection data;
	
	public WorkQueueIntegerPopulatorEvent() {}
	
	public WorkQueueIntegerPopulatorEvent(Collection data, String createdBy, Object workKey) {
		this.data = data;
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

	public Collection getCollection() {
		return data;
	}	
}
