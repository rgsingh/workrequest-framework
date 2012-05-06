package com.rgsinfotech.workqueue.ui;

import com.rgsinfotech.eventbus.api.EventDispatcher;
import com.rgsinfotech.eventbus.event.WorkQueueServerStartEvent;
import com.rgsinfotech.eventbus.listener.WorkQueueServerStartListener;


public class Main {

	public static void main(String[] args) {


		Thread consumerThread = new Thread(new Runnable() {
			
			public void run() {
	        	EventDispatcher<WorkQueueServerStartEvent> dispatcher = new EventDispatcher<WorkQueueServerStartEvent>();
	        	dispatcher.addListener(new WorkQueueServerStartListener());
	        	dispatcher.dispatchEvent(new WorkQueueServerStartEvent(toString(), "Started WorkQueueServerService"));
			}
		});
		
		
		Thread producerThread = new Thread(new Runnable() {
			
			public void run() {
				WorkQueueWindow window = new WorkQueueWindow();

				window.show();
			}
		});

		consumerThread.start();
		producerThread.start();
		

		try {
			consumerThread.join();
			producerThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
