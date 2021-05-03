package com.jdklueber.cashregister.service;

import java.math.BigDecimal;

import com.jdklueber.cashregister.model.InventoryItem;

public class SalesTaxCalculatorImpl implements SalesTaxCalculator {
	private BigDecimal taxPct;
	
	public SalesTaxCalculatorImpl(String taxPct) {
		this.taxPct = new BigDecimal(taxPct);
	}
	
	@Override
	public BigDecimal getTax(InventoryItem item) {
		if (item.isTaxable()) {
			return item.getPrice().multiply(taxPct);
		} else {
			return new BigDecimal("0.00");
		}
	}

}
