package com.rgsinfotech.workqueue.ui;

import com.rgsinfotech.eventbus.api.EventDispatcher;
import com.rgsinfotech.eventbus.event.WorkQueueIntegerPopulatorEvent;
import com.rgsinfotech.eventbus.listener.Listener;

public class WorkInjectionWorkerProxy implements WorkerLifecycleProxy {

	private WorkInjectionWorker injectionWorker;
	private EventDispatcher<WorkQueueIntegerPopulatorEvent> integerPopulatorEventDispatcher;
	private Listener<WorkQueueIntegerPopulatorEvent> integerPopulatorListener;
	
	public WorkInjectionWorkerProxy() {
		
	}

	public void setInjectedWork(String injectedWork) {
		injectionWorker.setInjectedWork(injectedWork);
	}

	public void setIntegerPopulatorEventDispatcher(
			EventDispatcher<WorkQueueIntegerPopulatorEvent> integerPopulatorEventDispatcher) {
		this.integerPopulatorEventDispatcher = integerPopulatorEventDispatcher;
		
	}


	public void setIntegerPopulatorListener(
			Listener<WorkQueueIntegerPopulatorEvent> integerPopulatorListener) {
		this.integerPopulatorListener = integerPopulatorListener;
		
	}	

	public void init() {
		injectionWorker = new WorkInjectionWorker();
		injectionWorker.setIntegerPopulatorEventDispatcher(integerPopulatorEventDispatcher); 
		injectionWorker.setIntegerPopulatorListener(integerPopulatorListener);

	}

	public void execute() {
		injectionWorker.execute();
	}

	public void cancel() {
		injectionWorker.cancel(false);
	}

	public void pause() {
	}

	public WorkInjectionWorker getInjectionWorker() {
		return injectionWorker;
	}

	public void setInjectionWorker(WorkInjectionWorker injectionWorker) {
		this.injectionWorker = injectionWorker;
	}

}
