package com.rgsinfotech.workqueue.client.swing;

public interface WorkerLifecycleProxy {
	
	public void init();
	public void execute();
	public void cancel();	
	public void pause();	
}
