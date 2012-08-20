package com.rgsinfotech.workqueue.service;

import java.rmi.RemoteException;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.naming.ServiceUnavailableException;

import org.slf4j.LoggerFactory;

import com.rgsinfotech.workqueue.LoggingThreadFactory;
import com.rgsinfotech.workqueue.Worker;

public class WorkQueueServerService<T> implements Service<T> {

	
	private T result;
	private static final int CAPACITY = 10;
	private static final int NUM_WORKERS = 2;
	private static String THREAD_PREFIX = "workqueue";

	private BlockingQueue<T> queue = new ArrayBlockingQueue<T>(CAPACITY);

	private ExecutorService executor = Executors.newFixedThreadPool(
			NUM_WORKERS, new LoggingThreadFactory(THREAD_PREFIX));

	private static final Integer DEFAULT_NUM_WORKERS = 2;
	private Integer numWorkers = new Integer(DEFAULT_NUM_WORKERS);
	
	
	public WorkQueueServerService() {
	}

	public Integer getNumWorkers() {
		return numWorkers;
	}

	public void setNumWorkers(Integer numWorkers) {
		this.numWorkers = numWorkers;
	}

	public void send(T data) throws ServiceUnavailableException {
		throw new UnsupportedOperationException("Only a List<T> is supported.");
	}

	public void send(List<T> data) throws ServiceUnavailableException {
		LoggerFactory.getLogger(getClass().getName()).debug(
				"WorkQueueServerService instance executing: " + this);
		if (data instanceof List) {
			for (T value : data) {
				try {
					queue.put(value);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@SuppressWarnings("unchecked")
	public void init() {
		int numWorkers = getNumWorkers();
		// Create a set of worker threads
		Worker<T>[] workers = new Worker[numWorkers];
		for (int i = 0; i < workers.length; i++) {
			workers[i] = new Worker<T>("worker-" + i, queue);
			executor.submit(workers[i]);
		}

		LoggerFactory.getLogger(getClass().getName()).debug(
				"WorkQueueServerService initialized.");
	}

	public void shutdown() {
		executor.shutdown();
		queue.clear();
	}

	public void afterPropertiesSet() throws Exception {
	}

	@Override
	public T getResult() throws ServiceUnavailableException, RemoteException {
		return result;
	}

}
