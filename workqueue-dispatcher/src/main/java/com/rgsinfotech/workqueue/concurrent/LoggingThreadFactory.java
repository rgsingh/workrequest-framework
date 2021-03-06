package com.rgsinfotech.workqueue.concurrent;

import java.util.concurrent.ThreadFactory;

import org.slf4j.LoggerFactory;

public class LoggingThreadFactory implements ThreadFactory
{
    private int counter = 0;
    private String prefix = "";

	   
    public LoggingThreadFactory(String prefix) {
    	this.prefix = prefix;
	}
    
    public Thread newThread(Runnable r)
    {
        Thread t = new Thread(r, prefix + "-" + counter++);

        t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler()
        {
            public void uncaughtException(Thread t, Throwable e)
            {
                LoggerFactory.getLogger(t.getName()).error(e.getMessage(), e);
            }
        });

        return t;
    }
}
