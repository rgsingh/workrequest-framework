package com.rgsinfotech.workqueue.ui;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class WorkQueueWindow implements ApplicationContextAware {

	private ApplicationContext context;
	private JFrame mainFrame;

	public WorkQueueWindow() {
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void show() {
		String nativeLF = UIManager.getSystemLookAndFeelClassName();

		// Install the look and feel
		try {
			UIManager.setLookAndFeel(nativeLF);
		} catch (InstantiationException e) {
		} catch (ClassNotFoundException e) {
		} catch (UnsupportedLookAndFeelException e) {
		} catch (IllegalAccessException e) {
		}

		// Show the frame
		int width = 300;
		int height = 300;
		getMainFrame().setSize(width, height);
		getMainFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getMainFrame().setVisible(true);

	}

	public ApplicationContext getApplicationContext() {
		return context;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = applicationContext;

	}
}
