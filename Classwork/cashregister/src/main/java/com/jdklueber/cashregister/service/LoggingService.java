package com.jdklueber.cashregister.service;

public interface LoggingService {
	public void log(String message);
	public void log(Exception exception);
}
