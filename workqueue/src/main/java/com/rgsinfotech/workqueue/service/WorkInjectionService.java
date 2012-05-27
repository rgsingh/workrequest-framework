package com.rgsinfotech.workqueue.service;

import java.util.ArrayList;
import java.util.List;

import javax.naming.ServiceUnavailableException;

import com.rgsinfotech.eventbus.api.EventDispatcher;
import com.rgsinfotech.eventbus.event.WorkQueueIntegerPopulatorEvent;
import com.rgsinfotech.eventbus.listener.Listener;

public class WorkInjectionService<T> implements Service<String> {

	private EventDispatcher<WorkQueueIntegerPopulatorEvent> integerPopulatorEventDispatcher;
	private Listener<WorkQueueIntegerPopulatorEvent> integerPopulatorListener;

	public void init() {

	}

	public void send(String data) throws ServiceUnavailableException {

		String[] values = data.split(",");
		List<Integer> dataSplit = new ArrayList<Integer>();
		for (int i = 0; i < values.length; i++) {
			dataSplit.add(Integer.parseInt(values[i]));
		}

		getIntegerPopulatorEventDispatcher().addListener(
				getIntegerPopulatorListener());

		getIntegerPopulatorEventDispatcher().dispatchEvent(
				new WorkQueueIntegerPopulatorEvent(dataSplit, getClass()
						.getName(), "<some transaction id>"));

	}

	public void send(List<String> data) throws ServiceUnavailableException {

	}

	public void shutdown() {

	}

	public void afterPropertiesSet() throws Exception {

	}

	public EventDispatcher<WorkQueueIntegerPopulatorEvent> getIntegerPopulatorEventDispatcher() {
		return integerPopulatorEventDispatcher;
	}

	public void setIntegerPopulatorEventDispatcher(
			EventDispatcher<WorkQueueIntegerPopulatorEvent> integerPopulatorEventDispatcher) {
		this.integerPopulatorEventDispatcher = integerPopulatorEventDispatcher;
	}

	public Listener<WorkQueueIntegerPopulatorEvent> getIntegerPopulatorListener() {
		return integerPopulatorListener;
	}

	public void setIntegerPopulatorListener(
			Listener<WorkQueueIntegerPopulatorEvent> integerPopulatorListener) {
		this.integerPopulatorListener = integerPopulatorListener;
	}

}
