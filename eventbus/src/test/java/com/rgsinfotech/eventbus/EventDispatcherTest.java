package com.rgsinfotech.eventbus;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.ConcurrentHashMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.rgsinfotech.eventbus.api.EventDispatcher;
import com.rgsinfotech.eventbus.event.Event;
import com.rgsinfotech.eventbus.event.EventDefinitions;
import com.rgsinfotech.eventbus.listener.Listener;

/**
 * TODO Integrate with Spring Framework, Mockito and Powermock
 * Unit test for EventDispatcher.
 */
@RunWith(MockitoJUnitRunner.class)
public class EventDispatcherTest extends TestCase {
	
	@Mock
	ConcurrentHashMap<String, Listener<Event>> mockEventListeners;
	
	@Mock
	Listener<Event> mockEventListener;	
	
	public EventDispatcherTest() {
		
	}

	public static Test suite() {
		return new TestSuite(EventDispatcherTest.class);
	}

	private String addedKey() { return "MY_LISTENER_KEY"; }
 	
	
	@SuppressWarnings("unchecked")
	@org.junit.Test
	public void testAddValidListener(){
		
		// given (the put method will return void. We are asserting in the callback of Answer which is the "then" part of out given-when-then template)
		when(mockEventListeners.put(addedKey(), mockEventListener)).thenAnswer(new Answer<Void>(){
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				// then
				Object[] args = invocation.getArguments();
				assertEquals(addedKey(), (String)args[0]);
				assertEquals("mockEventListener", ((Listener<Event>)args[1]).toString());
	            return null;
			}});
		EventDispatcher.getInstance().setListeners(mockEventListeners);
		
		// when 
		EventDispatcher.getInstance().addListener(
				addedKey(), mockEventListener);
		

	}
	
	@SuppressWarnings("unchecked")
	@org.junit.Test
	public void testRemoveValidListener(){
		
		// given
		EventDispatcher.getInstance().setListeners(mockEventListeners);
		EventDispatcher.getInstance().addListener(
				addedKey(), mockEventListener);
		int expectedListenersSize = 0;
		
		// when
		EventDispatcher.getInstance().removeListener(addedKey());
		
		// then
		assertEquals(expectedListenersSize, mockEventListeners.size());
		

	}
	
	@SuppressWarnings("unchecked")
	@org.junit.Test
	public void testDispatchEvent() {

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
