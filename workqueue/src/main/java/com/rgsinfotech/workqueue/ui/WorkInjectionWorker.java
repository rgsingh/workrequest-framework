package com.rgsinfotech.workqueue.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;

import com.rgsinfotech.eventbus.api.EventDispatcher;
import com.rgsinfotech.eventbus.event.WorkQueueIntegerPopulatorEvent;
import com.rgsinfotech.eventbus.listener.Listener;

public class WorkInjectionWorker extends SwingWorker<Void, Void> {

	private String injectedWork;
	private EventDispatcher<WorkQueueIntegerPopulatorEvent> integerPopulatorEventDispatcher;
	private Listener<WorkQueueIntegerPopulatorEvent> integerPopulatorListener;


	@Override
	protected Void doInBackground() throws Exception {

		populateWorkQueue();
		
		return null;
	}

	private void populateWorkQueue() {

		String[] values = injectedWork.split(",");
		List<Integer> data = new ArrayList<Integer>();
		for (int i = 0; i < values.length; i++) {
			data.add(Integer.parseInt(values[i]));
		}

		getIntegerPopulatorEventDispatcher().addListener(
				getIntegerPopulatorListener());

		getIntegerPopulatorEventDispatcher().dispatchEvent(
				new WorkQueueIntegerPopulatorEvent(data, getClass().getName(),
						"<some transaction id>"));

	}
	
	public void setInjectedWork(String injectedWork) {
		this.injectedWork = injectedWork;
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
