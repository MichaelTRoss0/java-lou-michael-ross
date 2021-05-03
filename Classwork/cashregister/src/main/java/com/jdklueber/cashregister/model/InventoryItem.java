package com.jdklueber.cashregister.model;

import java.math.BigDecimal;

public class InventoryItem {
	private int id;
	private BigDecimal price;
	private String description;
	private boolean taxable;

	public InventoryItem() {
		
	}
	
	public InventoryItem(int id, BigDecimal price, String description, boolean taxable) {
		this.id = id;
		this.price = price;
		this.description = description;
		this.taxable = taxable;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isTaxable() {
		return taxable;
	}
	public void setTaxable(boolean taxable) {
		this.taxable = taxable;
	}

	@Override
	public String toString() {
		return "InventoryItem [id=" + id + ", price=" + price + ", description=" + description + ", taxable=" + taxable
				+ "]";
	}
}
