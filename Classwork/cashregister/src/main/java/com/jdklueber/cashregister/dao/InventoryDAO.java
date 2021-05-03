package com.jdklueber.cashregister.dao;

import java.util.List;

import com.jdklueber.cashregister.model.InventoryItem;

public interface InventoryDAO {
	public InventoryItem get(int id);
	public List<InventoryItem> getAll();
	public InventoryItem add(InventoryItem item);
	public void update(InventoryItem item);
	public InventoryItem remove(int id);
}
