package com.rgsinfotech.workqueue;

import java.util.concurrent.BlockingQueue;

import javax.naming.ServiceUnavailableException;

import com.rgsinfotech.eventbus.api.EventDispatcher;
import com.rgsinfotech.eventbus.event.Event;
import com.rgsinfotech.eventbus.event.EventDefinitions;
import com.rgsinfotech.eventbus.listener.WorkerThreadCompletedListener;
import com.rgsinfotech.eventbus.listener.WorkerThreadFailedListener;
import com.rgsinfotech.workqueue.service.Service;
import com.rgsinfotech.workqueue.service.SomeService;

public class Worker<T> implements Runnable {

	private String name;
	BlockingQueue<T> q;

	public Worker(String name, BlockingQueue<T> q) {
		this.name = name;
		this.q = q;
	}

	public String getName() {
		return name;
	}

	@SuppressWarnings("unchecked")
	public void run() {
		try {
			while (true) {
				// Retrieve a value and block if the queue is empty
				T x = q.take();

				// Delegate to a Service that does something with x;
				Service<T> someService = new SomeService<T>();
				try {
					someService.send(x);
				} catch (ServiceUnavailableException e) {
					
					//TODO Move these out of the this class. This should be instantiated once and does not belong within this class.
					WorkerThreadFailedListener workerFailedlistener = new WorkerThreadFailedListener();
					
					
					EventDispatcher.getInstance().dispatchEvent(new Event(EventDefinitions.EVENT_WORKER_THREAD_FAILED, e.getMessage()));
				}

				//TODO Move these out of the this class. This should be instantiated once and does not belong within this class.
				WorkerThreadCompletedListener workerCompletedListener = new WorkerThreadCompletedListener();
				
				EventDispatcher.getInstance().dispatchEvent(new Event(EventDefinitions.EVENT_WORKER_THREAD_COMPLETED, this.getClass().getName()));

				Thread.sleep(500);

			}
		} catch (InterruptedException e) {
			
			//TODO Move these out of the this class. This should be instantiated once and does not belong within this class.
			WorkerThreadFailedListener workerFailedListener = new WorkerThreadFailedListener();			
			
			EventDispatcher.getInstance().dispatchEvent(new Event(EventDefinitions.EVENT_WORKER_THREAD_FAILED, e
					.getMessage()));
		}
	}
}