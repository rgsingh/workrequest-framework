package com.rgsinfotech.workqueue.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.rgsinfotech.eventbus.api.EventDispatcher;
import com.rgsinfotech.eventbus.event.WorkRequestEvent;
import com.rgsinfotech.eventbus.listener.WorkRequestProcessorListener;
import com.rgsinfotech.workqueue.LoggingThreadFactory;
import com.rgsinfotech.workqueue.Worker;

public class MainFrame extends JFrame {

	private static String THREAD_PREFIX = "workqueue";

	public MainFrame(String title) {
		super(title);

		// Set layout manager
		setLayout(new BorderLayout());

		// Create components
		final JTextArea textArea = new JTextArea(
				"200,201,202,203,204,205,206,207,208");
		textArea.setLineWrap(true);

		JButton button = new JButton("Inject Work");

		// Add components
		getContentPane().add(textArea, BorderLayout.CENTER);
		getContentPane().add(button, BorderLayout.SOUTH);

		// Add behavior
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int numWorkers = 2;
				
				/*
				 * The effectiveness of LoggingThreadFactory as a catch all for thrown
				 * Thread exceptions is questionable. I have been unable to force
				 * its implementation to catch exceptions thrown from any Worker instance.
				 * The only logical way to inform another thread that a Worker has
				 * failed is via a listener.
				 */
				ExecutorService executor = Executors
						.newFixedThreadPool(numWorkers, new LoggingThreadFactory(
								THREAD_PREFIX));
				BlockingQueue<Integer> queue = init(executor, numWorkers);

				String[] values = textArea.getText().trim().split(",");
				try {
					// Add some work to the queue; block if the queue is full.
					// Note that null cannot be added to a blocking queue.
					for (int i = 0; i < values.length; i++) {
						queue.put(Integer.parseInt(values[i]));
					}
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}

			}
		});

	}

	private static BlockingQueue<Integer> init(ExecutorService executor, int numWorkers) {
		// Create a bounded blocking queue of integers
		final int capacity = 10;
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(capacity);

		// Create a set of worker threads
		Worker[] workers = new Worker[numWorkers];
		for (int i = 0; i < workers.length; i++) {
			workers[i] = new Worker("worker-" + i, queue);
			executor.submit(workers[i]);
		}
		return queue;
	}
}
