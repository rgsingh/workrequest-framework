package com.rgsinfotech.eventbus.event;

import java.util.Collection;

public interface CollectionAwareEvent {

	public Collection getCollection();
}
