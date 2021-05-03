package com.jdklueber.cashregister.service;

import java.util.Date;

public class LoggingServiceConsoleImpl implements LoggingService {

	@Override
	public void log(String message) {
		Date now = new Date();
		System.out.println(now + ": " + message);
	}

	@Override
	public void log(Exception exception) {
		Date now = new Date();
		System.err.println(now + ": " + exception.getMessage());
	}

}
