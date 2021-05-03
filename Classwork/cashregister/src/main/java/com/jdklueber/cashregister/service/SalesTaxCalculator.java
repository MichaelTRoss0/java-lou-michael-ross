package com.jdklueber.cashregister.service;

import java.math.BigDecimal;

import com.jdklueber.cashregister.model.InventoryItem;

public interface SalesTaxCalculator {
	BigDecimal getTax(InventoryItem item);
}
