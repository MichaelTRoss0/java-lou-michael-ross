/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.vendingmachine.dao;

import com.mtross.vendingmachine.daoexception.VendingPersistenceException;
import com.mtross.vendingmachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author mike
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {
    
    public static String INVENTORY_FILE;
    public static final String DELIMITER = "::";
    
    private Map<String, Item> items = new HashMap<>();

    public VendingMachineDaoFileImpl() {
        INVENTORY_FILE = "inventory.txt";
    }
    
    public VendingMachineDaoFileImpl(String fileName) {
        INVENTORY_FILE = fileName;
    }

    @Override
    public void loadAllItems() throws VendingPersistenceException {
        loadInventory();
    }

    @Override
    public void saveAllChanges() throws VendingPersistenceException {
        writeInventory();
    }

    @Override
    public Item addItem(Item anItem) {
        String itemId = anItem.getSlotId();
        Item oldItem = items.put(itemId, anItem);
        return oldItem;
    }

    @Override
    public List<Item> getAllItems() {
        return new ArrayList<>(items.values());
    }

    @Override
    public Item getAnItem(String slotId) {
        return items.get(slotId);
    }

    @Override
    public void updateAnItem(String slotId, Item changedItem) {
        items.replace(slotId, changedItem);
    }

    @Override
    public Item removeAnItem(String slotId) {
        Item removedItem = items.remove(slotId);
        return removedItem;
    }
    
    private void loadInventory() throws VendingPersistenceException {
        Scanner scanner;
        
        try {
			scanner = new Scanner(
                      new BufferedReader(
                      new FileReader(INVENTORY_FILE)));
            
        } catch (FileNotFoundException e) {
			throw new VendingPersistenceException(
                    "\"-_- Could not load inventory data into memory.", e);
        }
        
        String currentLine;
        Item currentItem;
        
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);
            items.put(currentItem.getSlotId(), currentItem);
        }
        
        scanner.close();
    }
    
    
    private void writeInventory() throws VendingPersistenceException {
        
        PrintWriter out;
        
        try {
            out = new PrintWriter(
                  new FileWriter(INVENTORY_FILE));
            
        } catch (IOException e) {
            throw new VendingPersistenceException(
                    "could not save inventory data", e);
        }
        
        String itemAsText;
        List<Item> itemList = this.getAllItems();
        
        for (Item currentItem : itemList) {
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        
        out.close();
    }
    
    private Item unmarshallItem(String itemAsText) {
        String[] itemTokens = itemAsText.split(DELIMITER);
        
        String slotId = itemTokens[0];
        String name = itemTokens[1];
        BigDecimal cost = new BigDecimal(itemTokens[2]);
        int quantity = Integer.parseInt(itemTokens[3]);
        
        Item currentItem = new Item(slotId, name, cost, quantity);
        return currentItem;
    }
    
    private String marshallItem(Item anItem) {
        String itemAsText = anItem.getSlotId() + DELIMITER;
        itemAsText += anItem.getName() + DELIMITER;
        itemAsText += anItem.getCost().toString() + DELIMITER;
        itemAsText += Integer.toString(anItem.getQuantity());
        
        return itemAsText;
    }

}
