package com.rgsinfotech.eventbus.event;

public abstract class AbstractEvent {
	public String detail;
	public String key;
	
	abstract public String getDetail();
	abstract public String getKey();

}
