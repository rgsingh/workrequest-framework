package com.rgsinfotech.eventbus;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.rgsinfotech.eventbus.api.EventDispatcher;
import com.rgsinfotech.eventbus.event.AbstractEvent;
import com.rgsinfotech.eventbus.listener.Listener;

/**
 * Unit test for EventDispatcher.
 */
public class EventDispatcherTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public EventDispatcherTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( EventDispatcherTest.class );
    }

    public void testMainSuccess()
    {
    	EventDispatcher<TestEvent> dispatcher = new EventDispatcher<TestEvent>(TestEvent.class);
    	dispatcher.addListener(new TestProcessorListener());
    	dispatcher.dispatchEvent(new TestEvent());
    }
    
    private class TestEvent extends AbstractEvent {

		@Override
		public String getCreatedBy() {
			return "EventDispatcherTest";
		}

		@Override
		public Object getKey() {
			return "bogus-key";
		}
    	
    }
    private class TestProcessorListener implements Listener<TestEvent> {

		public void process(TestEvent event) {
			assertNotNull(event);
			assertEquals("bogus-key", event.getKey());
			System.out.println("Executed TestEvent.process()");
			
		}
    	
    }
}
