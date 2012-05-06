package com.rgsinfotech.eventbus.event;

public abstract class AbstractEvent {
	public String servicedBy;
	public Object workKey;
	
	abstract public String getServicedBy();
	abstract public Object getKey();

}
