package com.rgsinfotech.eventbus.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Event extends AbstractEvent {

	private Map<String, String> attributes = new HashMap<String, String>();
	
	/**
	 * Used to store a key sequence of numeric IDs used for lookup of delegation 
	 * to some other downstream system. For example, a simple list of numeric IDs
	 * originally comma-delimited may have been place in a List<Integer>.
	 */
	private List<Integer> payloadIdSequence = new ArrayList<Integer>();
	
	public Event(String key, String detail) {
		this.key = key;
		this.detail = detail;
	}
	
	@Override
	public String getDetail() {
		return detail;
	}

	@Override
	public String getKey() {
		return key;
	}
	
	public void addAttribute(String name, String value) {
		attributes.put(name, value);
	}
	
	public String removeAttribute(String name) {
		return attributes.remove(name);
	}
	
	public String getAttribute(String name) {
		return attributes.get(name);
	}

	public List<Integer> getPayloadIdSequence() {
		return payloadIdSequence;
	}
}
