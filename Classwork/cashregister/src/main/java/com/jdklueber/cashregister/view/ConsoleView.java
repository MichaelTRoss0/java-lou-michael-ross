package com.jdklueber.cashregister.view;

import java.math.BigDecimal;
import java.util.List;

import com.jdklueber.cashregister.model.InventoryItem;

public class ConsoleView {
	private UserIO io;
	
	public ConsoleView(UserIO io) {
		this.io = io;
	}
	
	public int displayMainMenu() {
		io.print("");
		io.print("Select from the following options:");
		io.print("1: List Items For Sale");
		io.print("2: Add Item to Cart");
		io.print("3: Display Cart");
		io.print("4: Pay");
		return io.readInt("", 1, 4);
	}
	
	public void displayExitMessage() {
		io.print("Thank you for shopping at Wall Mart");
	}

	public void displayInventory(List<InventoryItem> inventory) {
		io.print("\n\nInventory:");
		displayItemList(inventory);
	}
	
	public int getItem() {
		return io.readInt("What item would you like?");
	}

	public void displayCart(List<InventoryItem> cart) {
		io.print("\n\nCart Contents:");
		displayItemList(cart);
	}
	
	private void displayItemList(List<InventoryItem> list) {
		for (InventoryItem item : list) {
			io.print(item.toString());
		}
	}

	public void displayTotal(BigDecimal total) {
		io.print("TOTAL: " + total);
	}
}
