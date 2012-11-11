package com.rgsinfotech.workqueue.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.slf4j.LoggerFactory;

import com.rgsinfotech.eventbus.event.Event;
import com.rgsinfotech.eventbus.listener.Listener;

public class MainFrame extends JFrame implements Listener<Event> {

	private static final long serialVersionUID = -3809276714057325720L;

	private JButton button;
	private WorkInjectionWorkerProxy injectionWorkerProxy;

	public MainFrame(String title) {
		super(title);

		// Set layout manager
		setLayout(new BorderLayout());

		// Create components
		final JTextArea textArea = new JTextArea("200,201,202");
		textArea.setLineWrap(true);

		button = new JButton("Inject Work");

		// Add components
		getContentPane().add(textArea, BorderLayout.CENTER);
		getContentPane().add(button, BorderLayout.SOUTH);

		// Add behavior
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String rawValues = textArea.getText().trim();
				getInjectionWorkerProxy().init();
				getInjectionWorkerProxy().setInjectedWork(rawValues);
				getInjectionWorkerProxy().execute();
			}

		});

	}

	public WorkInjectionWorkerProxy getInjectionWorkerProxy() {
		return injectionWorkerProxy;
	}

	public void setInjectionWorkerProxy(
			WorkInjectionWorkerProxy injectionWorkerProxy) {
		this.injectionWorkerProxy = injectionWorkerProxy;
	}

	@Override
	public void process(Event event) {
		LoggerFactory.getLogger(getClass().getName()).debug(
				"Received WorkQueueServerResultEvent");
	}

	@Override
	public void init() {

	}

}
