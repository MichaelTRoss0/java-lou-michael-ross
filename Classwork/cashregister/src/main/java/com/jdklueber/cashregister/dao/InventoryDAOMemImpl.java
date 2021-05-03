package com.jdklueber.cashregister.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jdklueber.cashregister.model.InventoryItem;

public class InventoryDAOMemImpl implements InventoryDAO {
	private Map<Integer, InventoryItem> items = new HashMap<>();
	private int nextId = 0;
	
	public InventoryDAOMemImpl() {
		InventoryItem item = new InventoryItem(0, new BigDecimal("100.00"), "Brick Wall", true);
		this.add(item);
		item = new InventoryItem(0, new BigDecimal("200.00"), "Concrete Wall", false);
		this.add(item);
		item = new InventoryItem(0, new BigDecimal("666.66"), "Build The Wall", true);
		this.add(item);
		item = new InventoryItem(0, new BigDecimal("10.00"), "Paper Wall", false);
		this.add(item);
	}
	
	public InventoryItem get(int id) {
		return items.get(id);
	}

	public List<InventoryItem> getAll() {
		return new ArrayList<>(items.values());
	}

	public InventoryItem add(InventoryItem item) {
		item.setId(nextId);
		items.put(item.getId(), item);
		nextId++;
		return item;
	}

	public void update(InventoryItem item) {
		items.put(item.getId(), item);
	}

	public InventoryItem remove(int id) {
		return items.remove(id);
	}

}
