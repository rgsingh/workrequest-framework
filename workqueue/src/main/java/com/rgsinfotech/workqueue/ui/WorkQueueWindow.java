package com.rgsinfotech.workqueue.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.concurrent.BlockingQueue;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.rgsinfotech.eventbus.api.EventDispatcher;
import com.rgsinfotech.eventbus.event.WorkRequestEvent;
import com.rgsinfotech.eventbus.listener.WorkRequestProcessorListener;

public class WorkQueueWindow {
	
	public WorkQueueWindow() {}
	
	public void show(final BlockingQueue<Integer> queue) {
		String nativeLF = UIManager.getSystemLookAndFeelClassName();

		// Install the look and feel
		try {
		    UIManager.setLookAndFeel(nativeLF);
		} catch (InstantiationException e) {
		} catch (ClassNotFoundException e) {
		} catch (UnsupportedLookAndFeelException e) {
		} catch (IllegalAccessException e) {
		}
		
		String title = "Work Queue Injector";
		JFrame frame = new JFrame(title);

		// Create a component to add to the frame
		final JTextArea textArea = new JTextArea("200,201,202,203,204,205,206,207,208");
    	textArea.setLineWrap(true);

		// Create an action
		Action action = new AbstractAction("Inject Data") {
			private static final long serialVersionUID = 1273652926718987406L;

			// This method is called when the button is pressed
		    public void actionPerformed(ActionEvent evt) {
            	EventDispatcher<WorkRequestEvent> dispatcher = new EventDispatcher<WorkRequestEvent>();
            	dispatcher.addListener(new WorkRequestProcessorListener());
            	
            	String[] values = textArea.getText().split(",");
            	try {
            	    // Add some work to the queue; block if the queue is full.
            	    // Note that null cannot be added to a blocking queue.
            		for (int i = 0; i < values.length; i++) {
            	        queue.put(Integer.parseInt(values[i]));
            	    }
            	} catch (InterruptedException e) {
            	} 

		    }
		};

		// Create the button
		JButton button = new JButton(action);
		
		// Add the component to the frame's content pane;
		// by default, the content pane has a border layout
		frame.getContentPane().add(textArea, BorderLayout.CENTER);
		frame.getContentPane().add(button, BorderLayout.WEST);
		// Show the frame
		int width = 300;
		int height = 300;
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
	}
}
