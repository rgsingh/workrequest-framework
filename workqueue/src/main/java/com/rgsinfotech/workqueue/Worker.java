package com.rgsinfotech.workqueue;

import java.util.concurrent.BlockingQueue;

import com.rgsinfotech.eventbus.api.EventDispatcher;
import com.rgsinfotech.eventbus.event.WorkRequestEvent;
import com.rgsinfotech.eventbus.listener.WorkRequestProcessorListener;

class Worker implements Runnable {
    // Special end-of-stream marker. If a worker retrieves
    // an Integer that equals this marker, the worker will terminate.
    static final Integer NO_MORE_WORK = new Integer(0);

    BlockingQueue<Integer> q;

	Worker(BlockingQueue<Integer> q) {
        this.q = q;
    }
    public void run() {
        try {
            while (true) {
                // Retrieve an integer; block if the queue is empty
                Integer x = q.take();

                // Terminate if the end-of-stream marker was retrieved
                if (x == NO_MORE_WORK) {
                    break;
                }

                // Compute the square of x
                int y = x * x;
            	EventDispatcher<WorkRequestEvent> dispatcher = new EventDispatcher<WorkRequestEvent>();
            	dispatcher.addListener(new WorkRequestProcessorListener());
            	dispatcher.dispatchEvent(new WorkRequestEvent(new Integer(y)));
                
            }
        } catch (InterruptedException e) {
        }
    }
}