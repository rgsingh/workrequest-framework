package com.rgsinfotech.eventbus.event;

import java.util.Collection;

public interface CollectionAwareEvent {

	@SuppressWarnings("rawtypes")
	public Collection getCollection();
}
