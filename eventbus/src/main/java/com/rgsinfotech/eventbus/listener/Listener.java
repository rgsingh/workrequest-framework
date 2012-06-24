package com.rgsinfotech.eventbus.listener;

import com.rgsinfotech.eventbus.event.AbstractEvent;

public interface Listener<T extends AbstractEvent> {
    void init();
	void process(T event);

}
