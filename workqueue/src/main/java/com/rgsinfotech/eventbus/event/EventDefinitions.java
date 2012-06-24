package com.rgsinfotech.eventbus.event;

public class EventDefinitions {
	
	public static final String EVENT_CLIENT_REQUEST_SENT = "com/rgsinfotech/event/clientRequest/sent";
	
	public static final String EVENT_WORKER_THREAD_COMPLETED = "com/rgsinfotech/event/workerThread/completed";
	public static final String EVENT_WORKER_THREAD_FAILED = "com/rgsinfotech/event/workerThread/failed";
	
	public static final String EVENT_WORK_QUEUE_SERVER_RESULT_FAILED = "com/rgsinfotech/event/workQueueServerResult/failed";
	public static final String EVENT_WORK_QUEUE_SERVER_RESULT_COMPLETED = "com/rgsinfotech/event/workQueueServerResult/completed";
	public static final String EVENT_WORK_QUEUE_SERVER_RESULT_PENDING = "com/rgsinfotech/event/workQueueServerResult/pending";
	public static final String EVENT_WORK_QUEUE_SERVER_RESULT_RETRYING = "com/rgsinfotech/event/workQueueServerResult/retrying";
	public static final String EVENT_WORK_QUEUE_SERVER_RESULT_CANCELLED = "com/rgsinfotech/event/workQueueServerResult/cancelled";

	public static final String EVENT_WORK_QUEUE_SERVER_SENDER = "com/rgsinfotech/event/workQueueServerSender";
	public static final String EVENT_WORK_QUEUE_SERVER_START = "com/rgsinfotech/event/workQueueServerStart";
}
