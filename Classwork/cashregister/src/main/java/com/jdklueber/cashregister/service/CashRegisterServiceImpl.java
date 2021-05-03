package com.jdklueber.cashregister.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.jdklueber.cashregister.dao.InventoryDAO;
import com.jdklueber.cashregister.model.InventoryItem;

public class CashRegisterServiceImpl implements CashRegisterService {
	private InventoryDAO dao;
	private LoggingService logger;
	private SalesTaxCalculator taxCalc;
	
	public CashRegisterServiceImpl(InventoryDAO dao, LoggingService logger, SalesTaxCalculator taxCalc) {
		super();
		this.dao = dao;
		this.logger = logger;
		this.taxCalc = taxCalc;
	}

	@Override
	public List<InventoryItem> add(int itemId, List<InventoryItem> cart) throws ItemDoesNotExistException {
		InventoryItem item = dao.get(itemId);

		if (item == null) {
			throw new ItemDoesNotExistException(itemId + " is not a valid item");
		}
		
		if (cart == null) {
			cart = new ArrayList<>();
		}
		
		cart.add(item);
		return cart;
	}

	@Override
	public BigDecimal getTotal(List<InventoryItem> cart) {	
		BigDecimal result = new BigDecimal("0.00");
		
		for (InventoryItem item : cart) {
			result = result.add(item.getPrice()).add(taxCalc.getTax(item));
		}
		
		return result;
	}

	@Override
	public BigDecimal makeChange(List<InventoryItem> cart, BigDecimal amt) {
		// TODO Auto-generated method stub
		return null;
	}

}
