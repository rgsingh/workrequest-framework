package com.rgsinfotech.workqueue.concurrent;

import java.rmi.RemoteException;
import java.util.concurrent.BlockingQueue;

import javax.naming.ServiceUnavailableException;

import com.rgsinfotech.eventbus.api.EventDispatcher;
import com.rgsinfotech.eventbus.event.Event;
import com.rgsinfotech.workqueue.event.EventDefinitions;
import com.rgsinfotech.workqueue.remote.service.Service;
import com.rgsinfotech.workqueue.service.SimpleMultiplierService;

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
				Service<T> multiplierService = new SimpleMultiplierService<T>();
				try {
					multiplierService.send(x);

				} catch (ServiceUnavailableException e) {
					
					EventDispatcher.getInstance().dispatchEvent(new Event(EventDefinitions.EVENT_WORKER_THREAD_FAILED, e.getMessage()));
				}
			
				EventDispatcher.getInstance().dispatchEvent(new Event(EventDefinitions.EVENT_WORKER_THREAD_COMPLETED, this.getClass().getName()));

				Thread.sleep(500);

			}
		} catch (InterruptedException e) {
	
			EventDispatcher.getInstance().dispatchEvent(new Event(EventDefinitions.EVENT_WORKER_THREAD_FAILED, e
					.getMessage()));
		}
	}
}