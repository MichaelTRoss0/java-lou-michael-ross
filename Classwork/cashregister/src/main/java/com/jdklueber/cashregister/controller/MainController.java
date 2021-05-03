package com.jdklueber.cashregister.controller;

import java.util.ArrayList;
import java.util.List;

import com.jdklueber.cashregister.dao.InventoryDAO;
import com.jdklueber.cashregister.model.InventoryItem;
import com.jdklueber.cashregister.service.CashRegisterService;
import com.jdklueber.cashregister.service.ItemDoesNotExistException;
import com.jdklueber.cashregister.service.LoggingService;
import com.jdklueber.cashregister.view.ConsoleView;

public class MainController {
	private ConsoleView consoleView;
	private LoggingService log;
	private InventoryDAO dao;
	private CashRegisterService cashRegister;
	
	public MainController(ConsoleView consoleView, LoggingService log, InventoryDAO dao, 
			CashRegisterService cashRegister) {
		this.consoleView = consoleView;
		this.cashRegister = cashRegister;
		this.log = log;
		this.dao = dao;
	}
	
	public void setConsoleView(ConsoleView consoleView) {
		this.consoleView = consoleView;
	}
	
	public void run() {
		boolean run = true;
		List<InventoryItem> cart = new ArrayList<>();
		
		log.log("System started");
		
		while(run) {
			int choice = consoleView.displayMainMenu();
			switch(choice) {
			case 1:
				consoleView.displayInventory(dao.getAll());
				break;
			case 2: 
				int itemId = consoleView.getItem();
				try {
					cashRegister.add(itemId, cart);
				} catch (ItemDoesNotExistException e) {
					log.log(e);
				}
				break;
			case 3: 
				consoleView.displayCart(cart);
				consoleView.displayTotal(cashRegister.getTotal(cart));
				break;
			case 4:
				//pay
				consoleView.displayExitMessage();
				run = false;
				break;
			}
		}
		
		log.log("System shut down");
	}
}
