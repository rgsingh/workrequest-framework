package com.rgsinfotech.workqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.rgsinfotech.workqueue.ui.WorkQueueWindow;

/**
 * Hello world!
 *
 */
public class Main 
{
	private static String THREAD_PREFIX = "workqueue";
	
    public static void main( String[] args )
    {
    	ExecutorService executor = Executors.newSingleThreadExecutor(new LoggingThreadFactory(THREAD_PREFIX));
    	
    	
    	BlockingQueue<Integer> queue = init(executor);

    	try {
    	    // Add some work to the queue; block if the queue is full.
    	    // Note that null cannot be added to a blocking queue.
    	    for (int i=1; i<100; i++) {
    	        queue.put(i);
    	    }

//    	    // Add special end-of-stream markers to terminate the workers
//    	    for (int i=0; i<workers.length; i++) {
//    	        queue.put(Worker.NO_MORE_WORK);
//    	    }
    	} catch (InterruptedException e) {
    	}    	
    	
    	WorkQueueWindow window = new WorkQueueWindow();
    	
    	window.show(queue);


    }

	private static BlockingQueue<Integer> init(ExecutorService executor) {
		// Create a bounded blocking queue of integers
    	final int capacity = 10;
    	BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(capacity);

    	
    	// Create a set of worker threads
    	final int numWorkers = 2;
    	Worker[] workers = new Worker[numWorkers];
    	for (int i=0; i<workers.length; i++) {
    	    workers[i] = new Worker("worker-" + i,queue);
        	executor.submit(workers[i]);   
    	}
		return queue;
	}
}
