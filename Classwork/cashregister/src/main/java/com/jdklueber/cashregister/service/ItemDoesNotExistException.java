package com.jdklueber.cashregister.service;

public class ItemDoesNotExistException extends Exception {

	public ItemDoesNotExistException(String string) {
		super(string);
	}
}
