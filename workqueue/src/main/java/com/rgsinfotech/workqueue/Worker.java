package com.rgsinfotech.workqueue;

import java.util.concurrent.BlockingQueue;

import com.rgsinfotech.eventbus.api.EventDispatcher;
import com.rgsinfotech.eventbus.event.WorkRequestEvent;
import com.rgsinfotech.eventbus.listener.WorkRequestProcessorListener;

class Worker implements Runnable {
    // Special end-of-stream marker. If a worker retrieves
    // an Integer that equals this marker, the worker will terminate.
    static final Integer NO_MORE_WORK = new Integer(0);
    private String name;
    BlockingQueue<Integer> q;

	Worker(String name, BlockingQueue<Integer> q) {
		this.name = name;
        this.q = q;
    }
    public void run() {
        try {
            while (true) {
                // Retrieve an integer; block if the queue is empty
                Integer x = q.take();

                // Terminate if the end-of-stream marker was retrieved
                if (x.intValue() == NO_MORE_WORK.intValue()) {
                	System.out.println(name + " being shutdown!");
                    break;
                }

                // Compute the square of x plus 1
                int y = x * x;
                y++;
            	EventDispatcher<WorkRequestEvent> dispatcher = new EventDispatcher<WorkRequestEvent>();
            	dispatcher.addListener(new WorkRequestProcessorListener());
            	dispatcher.dispatchEvent(new WorkRequestEvent(new Integer(y)));
                
            }
        } catch (InterruptedException e) {
        }
    }
}