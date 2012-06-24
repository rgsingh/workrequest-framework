package com.rgsinfotech.eventbus.api;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.rgsinfotech.eventbus.event.AbstractEvent;
import com.rgsinfotech.eventbus.listener.Listener;

public class EventDispatcher<T extends AbstractEvent> {
	// private Class<T> type;

	private final Map<String, Listener<? super T>> listeners = new ConcurrentHashMap<String, Listener<? super T>>();

	private EventDispatcher() {

	}

	private static class EventDispatcherHolder {
		@SuppressWarnings("unchecked")
		public static final EventDispatcher<? extends AbstractEvent> instance = new EventDispatcher();
	}

	@SuppressWarnings("rawtypes")
	public static EventDispatcher getInstance() {
		return EventDispatcherHolder.instance;
	}

	// public EventDispatcher(Class<T> type) {
	// this.type = type;
	// }
	//
	// public Class<T> getType() {
	// return type;
	// }

	public void addListener(String eventDef, Listener<? super T> listener) {

		if (!listeners.containsKey(eventDef)) {
			listeners.put(eventDef, listener);
		}
	}

	public void removeListener(String eventDef) {

		if (listeners.containsKey(eventDef)) {
			listeners.remove(eventDef);
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
