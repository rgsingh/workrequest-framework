package com.rgsinfotech.eventbus.event;

import java.util.Collection;

public class WorkQueueServerSenderEvent extends AbstractEvent implements CollectionAwareEvent {
	
	@SuppressWarnings("rawtypes")
	private Collection data;
	
	public WorkQueueServerSenderEvent() {}
	
	@SuppressWarnings("rawtypes")
	public WorkQueueServerSenderEvent(Collection data, String createdBy, Object workKey) {
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
