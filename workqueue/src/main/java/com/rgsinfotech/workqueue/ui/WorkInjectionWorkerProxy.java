package com.rgsinfotech.workqueue.ui;

import com.rgsinfotech.workqueue.service.WorkInjectionService;

/*
 * Wraps a SwingWorker (WorkInjectionWorker) to handle UI event handling and dispatch.
 */
public class WorkInjectionWorkerProxy implements WorkerLifecycleProxy {

	private WorkInjectionWorker injectionWorker;
	private WorkInjectionService<String> workInjectionService;

	public WorkInjectionWorkerProxy() {

	}

	public void setInjectedWork(String injectedWork) {
		injectionWorker.setInjectedWork(injectedWork);
	}

	public WorkInjectionService<String> getWorkInjectionService() {
		return workInjectionService;
	}

	public void setWorkInjectionService(
			WorkInjectionService<String> workInjectionService) {
		this.workInjectionService = workInjectionService;
	}

	public void init() {
		injectionWorker = new WorkInjectionWorker();
		injectionWorker.setWorkInjectionService(workInjectionService);
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
