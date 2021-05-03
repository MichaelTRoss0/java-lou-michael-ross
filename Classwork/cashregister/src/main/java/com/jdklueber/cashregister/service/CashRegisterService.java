package com.jdklueber.cashregister.service;

import java.math.BigDecimal;
import java.util.List;

import com.jdklueber.cashregister.model.InventoryItem;

public interface CashRegisterService {
	public List<InventoryItem> add(int item, List<InventoryItem> cart) throws ItemDoesNotExistException;
	public BigDecimal makeChange(List<InventoryItem> cart, BigDecimal amt);
	public BigDecimal getTotal(List<InventoryItem> cart);
}
