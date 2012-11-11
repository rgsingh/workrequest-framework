package com.rgsinfotech.eventbus;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.rgsinfotech.eventbus.api.EventDispatcher;
import com.rgsinfotech.eventbus.event.Event;
import com.rgsinfotech.eventbus.event.EventDefinitions;
import com.rgsinfotech.eventbus.listener.Listener;

/**
 * Unit test for EventDispatcher.
 */
public class EventDispatcherTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public EventDispatcherTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(EventDispatcherTest.class);
	}

	@SuppressWarnings("unchecked")
	public void testMainSuccess() {

		// Typically instantiated once upon system initialization and maintained
		// until termination of the system.
		Listener<Event> eventListener = new TestInternalSystemListener();

		EventDispatcher.getInstance().dispatchEvent(new Event(
				EventDefinitions.INTERNAL_EVENT_LISTENER_STARTED, getClass()
						.getName()));

		// Typically instantiated once upon system initialization and maintained
		// until termination of the system.
		Listener<Event> scanJobProcessingEventListener = new ScanJobProcessingListener();

		EventDispatcher.getInstance().dispatchEvent(new Event(
				ScanJobProcessingEventDefinitions.SCAN_JOB_PROCESSING_RECEIVED,
				getClass().getName()));

	}

	/*
	 * Name the Listener based upon the service under test. In this case, an
	 * "internal system listener" can be both a producer and consumer
	 */
	private class TestInternalSystemListener implements Listener<Event> {

		public TestInternalSystemListener() {
			init();
		}

		@Override
		public void init() {
			EventDispatcher.getInstance().addListener(
					EventDefinitions.INTERNAL_EVENT_LISTENER_STARTED, this);
		}

		public void process(Event event) {
			assertNotNull(event);
			assertEquals(EventDefinitions.INTERNAL_EVENT_LISTENER_STARTED, event.getKey());
			System.out.println("Executed TestEvent.process()");

		}
	}

	/*
	 * Another more practical example that dispatches each consumed event to a
	 * handler method. Name each Listener class in a coarse-grained fashion.
	 * This is so that events can be grouped under a general category (e.g.
	 * ScanJobProcessing) and specific handlers can be invoked when this class
	 * consumes any event it is registered for (e.g.
	 * ScanJobProcessingEventDefinitions.RECEIVED_NEW_JOB).
	 */
	private class ScanJobProcessingListener implements Listener<Event> {

		public ScanJobProcessingListener() {
			init();
		}

		@Override
		public void init() {
			EventDispatcher.getInstance().addListener(
					ScanJobProcessingEventDefinitions.SCAN_JOB_PROCESSING_RECEIVED, this);
		}

		public void process(Event event) {
			assertNotNull(event);

			assertEquals(ScanJobProcessingEventDefinitions.SCAN_JOB_PROCESSING_RECEIVED,
					event.getKey());
			if (event.getKey().equalsIgnoreCase(
					ScanJobProcessingEventDefinitions.SCAN_JOB_PROCESSING_RECEIVED)) {
				handleReceivedEvent();
			} else {
				System.out.println("Ignoring consumed event");
			}

		}

		private void handleReceivedEvent() {
			System.out
					.println("A new scan job has been received! Delegating to the appropriate processing service that will actually do the work!");
		}
	}

	private class ScanJobProcessingEventDefinitions {
		public static final String SCAN_JOB_PROCESSING_RECEIVED = "com/rgsinfotech/event/listener/scanjobprocessing/received";
	}
}
