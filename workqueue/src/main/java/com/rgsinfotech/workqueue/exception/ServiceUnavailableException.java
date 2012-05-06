package com.rgsinfotech.workqueue.exception;

class ServiceUnavailableException extends Exception {

	private static final long serialVersionUID = 1770280811471785801L;
	String problem;

	public ServiceUnavailableException() {
		super();
		problem = "unknown";
	}

	public ServiceUnavailableException(String err) {
		super(err);
		problem = err;
	}

	public String getError() {
		return problem;
	}
}
