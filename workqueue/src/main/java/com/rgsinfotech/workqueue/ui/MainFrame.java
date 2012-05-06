package com.rgsinfotech.workqueue.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.rgsinfotech.eventbus.api.EventDispatcher;
import com.rgsinfotech.eventbus.event.WorkQueuePopulatorEvent;
import com.rgsinfotech.eventbus.listener.WorkQueuePopulatorListener;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -3809276714057325720L;

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

				populateWorkQueue(textArea);

			}

			private void populateWorkQueue(final JTextArea textArea) {

				String[] values = textArea.getText().trim().split(",");
				List<Integer> data = new ArrayList<Integer>();
				for (int i = 0; i < values.length; i++) {
					data.add(Integer.parseInt(values[i]));
				}

				EventDispatcher<WorkQueuePopulatorEvent> dispatcher = new EventDispatcher<WorkQueuePopulatorEvent>();
				dispatcher
						.addListener(new WorkQueuePopulatorListener());
				dispatcher.dispatchEvent(new WorkQueuePopulatorEvent(data,
						getName(), "<some transaction id>"));

			}
		});

	}
}
