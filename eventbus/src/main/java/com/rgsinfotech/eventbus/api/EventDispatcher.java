package com.rgsinfotech.eventbus.api;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Assert;

import com.rgsinfotech.eventbus.event.AbstractEvent;
import com.rgsinfotech.eventbus.listener.Listener;

public class EventDispatcher<T extends AbstractEvent> {
	// private Class<T> type;

	private Map<String, Listener<? super T>> listeners;

	private EventDispatcher() {

	}
	
	private EventDispatcher(Map<String, Listener<? super T>> listeners) {
		this.listeners = listeners;
	}

	private static class EventDispatcherHolder<T extends AbstractEvent> {
		@SuppressWarnings("unchecked")
		public static final EventDispatcher<? extends AbstractEvent> instance = new EventDispatcher();
	}

	@SuppressWarnings("rawtypes")
	public static EventDispatcher getInstance() {
		return EventDispatcherHolder.instance;
	}
	
	public Map<String, Listener<? super T>> getListeners() {
		return listeners;
	}

	public void setListeners(Map<String, Listener<? super T>> listeners) {
		this.listeners = listeners;
	}

	public void addListener(String eventDef, Listener<? super T> listener) {
		Assert.assertNotNull("Cannot remove a listener from a non-existing collection", listeners);
		if (!listeners.containsKey(eventDef)) {
			listeners.put(eventDef, listener);
		}
	}

	public void removeListener(String eventDef) {
        initListenersCollection();
		if (listeners.containsKey(eventDef)) {
			listeners.remove(eventDef);
		}

	}
	
	private void initListenersCollection() {
		if (listeners == null) {
			setListeners(new ConcurrentHashMap<String, Listener<? super T>>());
		}
	}
	public void dispatchEvent(T event) {
		for (Map.Entry<String, Listener<? super T>> entry : listeners
				.entrySet()) {
			String eventDef = entry.getKey();
			Listener<? super T> listener = entry.getValue();
			if (event.getKey().equalsIgnoreCase(eventDef)) {
				listener.process(event);
			}
		}
	}

}
