package com.rgsinfotech.eventbus.event;

import java.util.Collection;

public class WorkQueueIntegerPopulatorEvent extends AbstractEvent implements CollectionAwareEvent {
	
	@SuppressWarnings("rawtypes")
	private Collection data;
	
	public WorkQueueIntegerPopulatorEvent() {}
	
	@SuppressWarnings("rawtypes")
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

	@SuppressWarnings("rawtypes")
	public Collection getCollection() {
		return data;
	}	
}
