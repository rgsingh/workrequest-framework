package com.rgsinfotech.workqueue.client;

public interface WorkerLifecycleProxy {
	
	public void init();
	public void execute();
	public void cancel();	
	public void pause();	
}
