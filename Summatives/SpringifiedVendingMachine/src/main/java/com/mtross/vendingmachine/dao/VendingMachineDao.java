/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.vendingmachine.dao;

import com.mtross.vendingmachine.daoexception.VendingPersistenceException;
import com.mtross.vendingmachine.dto.Item;
import java.util.List;

/**
 *
 * @author mike
 */
public interface VendingMachineDao {
    
    public void loadAllItems()
            throws VendingPersistenceException;
    
    public void saveAllChanges()
            throws VendingPersistenceException;
    
    public Item addItem(Item anItem);
    
    public List<Item> getAllItems();
    
    public Item getAnItem(String slotId);
    
    public void updateAnItem(String slotId, Item changedItem);
    
    public Item removeAnItem(String slotId);
    
}
