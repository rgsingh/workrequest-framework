package com.rgsinfotech.eventbus;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.rgsinfotech.eventbus.api.EventDispatcher;
import com.rgsinfotech.eventbus.event.WorkRequestEvent;
import com.rgsinfotech.eventbus.listener.WorkRequestProcessorListener;

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
    
    	
    	EventDispatcher<WorkRequestEvent> dispatcher = new EventDispatcher<WorkRequestEvent>();
    	dispatcher.addListener(new WorkRequestProcessorListener());
    	dispatcher.dispatchEvent(new WorkRequestEvent());

    	    	
    	assertTrue( true );

    
    }
}
