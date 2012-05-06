package com.rgsinfotech.eventbus.event;

public abstract class AbstractEvent {
	public String createdBy;
	public Object workKey;
	
	abstract public String getCreatedBy();
	abstract public Object getKey();

}
