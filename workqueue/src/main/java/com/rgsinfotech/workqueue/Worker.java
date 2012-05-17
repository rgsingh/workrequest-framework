package com.rgsinfotech.workqueue;

import java.util.concurrent.BlockingQueue;

import javax.naming.ServiceUnavailableException;

import com.rgsinfotech.eventbus.api.EventDispatcher;
import com.rgsinfotech.eventbus.event.WorkRequestEvent;
import com.rgsinfotech.eventbus.event.WorkerThreadFailedEvent;
import com.rgsinfotech.eventbus.listener.WorkRequestListener;
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
		        	EventDispatcher<WorkerThreadFailedEvent> dispatcher = new EventDispatcher<WorkerThreadFailedEvent>(WorkerThreadFailedEvent.class);
		        	dispatcher.addListener(new WorkerThreadFailedListener());
		        	dispatcher.dispatchEvent(new WorkerThreadFailedEvent(toString(), e.getMessage()));
				}
                
                
            	EventDispatcher<WorkRequestEvent> dispatcher = new EventDispatcher<WorkRequestEvent>(WorkRequestEvent.class);
            	dispatcher.addListener(new WorkRequestListener());
            	dispatcher.dispatchEvent(new WorkRequestEvent(toString(), x));
            	
            	Thread.sleep(500);
                
            }
        } catch (InterruptedException e) {
        	EventDispatcher<WorkerThreadFailedEvent> dispatcher = new EventDispatcher<WorkerThreadFailedEvent>(WorkerThreadFailedEvent.class);
        	dispatcher.addListener(new WorkerThreadFailedListener());
        	dispatcher.dispatchEvent(new WorkerThreadFailedEvent(toString(), e.getMessage()));
        }
    }
}