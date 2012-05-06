package com.rgsinfotech.workqueue.ui;


public class Main {

	public static void main(String[] args) {


		Thread consumerThread = new Thread(new Runnable() {
			
			public void run() {
				
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
