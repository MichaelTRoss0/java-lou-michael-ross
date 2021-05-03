/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.vendingmachine.dao;

import com.mtross.vendingmachine.daoexception.VendingPersistenceException;
import com.mtross.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mike
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {
    
    private Item onlyItem;
    private List<Item> itemList = new ArrayList<>();

    public VendingMachineDaoStubImpl() {
        onlyItem = new Item();
        onlyItem.setSlotId("A1");
        onlyItem.setName("Swedish Fish");
        onlyItem.setCost(new BigDecimal("1.00"));
        onlyItem.setQuantity(2);
        
        itemList.add(onlyItem);
    }

    @Override
    public void loadAllItems() throws VendingPersistenceException {
        // do nothing by design
    }

    @Override
    public void saveAllChanges() throws VendingPersistenceException {
        // do nothing by design
    }

    @Override
    public Item addItem(Item anItem) {
        if (anItem.equals(onlyItem)) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public List<Item> getAllItems() {
        return itemList;
    }

    @Override
    public Item getAnItem(String slotId) {
        if (slotId.equals(onlyItem.getSlotId())) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public void updateAnItem(String slotId, Item changedItem) {
        if (slotId.equals(onlyItem.getSlotId())) {
            onlyItem = changedItem;
        }
    }

    @Override
    public Item removeAnItem(String slotId) {
        if (slotId.equals(onlyItem.getSlotId())) {
            return onlyItem;
        } else {
            return null;
        }
    }
    
}
