/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.vendingmachine.dao;

import com.mtross.vendingmachine.daoexception.VendingPersistenceException;
import com.mtross.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author mike
 */
public class VendingMachineDaoFileImplTest {
    
    VendingMachineDao testDao;
    
    public VendingMachineDaoFileImplTest() {
        testDao = new VendingMachineDaoFileImpl("test.txt");
    }
    
    @Test
    public void testAddGetAnItem() {
        // ARRANGE
        String itemId = "A1";
        Item itemIn = new Item(itemId, "Snickers", BigDecimal.TEN, 99);
        
        // ACT
        Item itemOut = testDao.addItem(itemIn);
        Item takenOut = testDao.getAnItem(itemId);
        
        // ASSERT
        Assertions.assertEquals(takenOut.getSlotId(), itemIn.getSlotId(), "IDs should match");
        Assertions.assertEquals(takenOut.getName(), itemIn.getName(), "Names should match");
        Assertions.assertEquals(takenOut.getCost(), itemIn.getCost(), "Costs should match");
        Assertions.assertEquals(takenOut.getQuantity(), itemIn.getQuantity(), "Quantities should match");
        
        Assertions.assertNull(itemOut, "There should not have been an item here");
        
    }
    
    @Test
    public void testAddGetAllItems() {
        // ARRANGE
        String itemId1 = "A1";
        Item itemIn1 = new Item(itemId1, "Snickers", BigDecimal.TEN, 99);
        
        String itemId2 = "B2";
        Item itemIn2 = new Item(itemId2, "Pencil", BigDecimal.ONE, 9001);
        
        // ACT
        Item firstItemOut = testDao.addItem(itemIn1);
        Item secondItemOut = testDao.addItem(itemIn2);
        
        List<Item> itemBox = testDao.getAllItems();
        
        // ASSERT
        Assertions.assertNotNull(itemBox, "The list of items should not be null");
        Assertions.assertEquals(2, itemBox.size(), "There should be 2 items in the list");
        Assertions.assertTrue(itemBox.contains(itemIn1),
                "Item list should have the first item stored");
        Assertions.assertTrue(itemBox.contains(itemIn2),
                "Item list should have the second item stored");
        
        Assertions.assertNull(firstItemOut,
                "There shouldn't be an item returned when storing in an empty dao");
        Assertions.assertNull(secondItemOut,
                "There shouldn't be an item returned when storing in the dao");
        
    }
    
    @Test
    public void testAddUpdateAnItem() {
        // ARRANGE
        String itemId = "A1";
        Item firstItem = new Item(itemId, "Snickers", BigDecimal.TEN, 99);
        Item secondItem = new Item(itemId, "screwdriver", BigDecimal.ONE, 42);
        
        // ACT
        testDao.addItem(firstItem);
        testDao.updateAnItem(itemId, secondItem);
        
        Item replacedItem = testDao.getAnItem(itemId);
        List<Item> itemBox = testDao.getAllItems();
        
        // ASSERT    
        Assertions.assertFalse(itemBox.contains(firstItem), "First item should not be in list");
        Assertions.assertEquals(secondItem, replacedItem, "Item should have been replaced by second");
        Assertions.assertEquals(1, itemBox.size(), "There should be only one item");
        Assertions.assertTrue(itemBox.contains(secondItem), "Only item left should be the second one");
        
    }
    
    @Test
    public void testAddRemoveAnItem() {
        // ARRANGE
        String itemId = "A1";
        Item itemIn = new Item(itemId, "Snickers", BigDecimal.TEN, 99);
        
        // ACT
        testDao.addItem(itemIn);
        Item removedItem = testDao.removeAnItem(itemId);
        Item shouldBeNull = testDao.getAnItem(itemId);
        
        List<Item> itemBox = testDao.getAllItems();
        
        // ASSERT
        Assertions.assertEquals(itemIn, removedItem, "Stored item and removed item should match");
        Assertions.assertNull(shouldBeNull, "Item should no longer be in DAO");
        Assertions.assertEquals(0, itemBox.size(), "List should have 0 items in it");
        
    }
    
    @Test
    public void emptyDaoTest() {
        // ARRANGE
        
        // ACT
        List<Item> emptyInventory = testDao.getAllItems();
        
        // ASSERT
        Assertions.assertNotNull(emptyInventory, "Should be an empty list, not null");
        Assertions.assertEquals(0, emptyInventory.size(), "Should be an empty list");
        
    }
    
    /*
    For reference, here is what test.txt is supposed to look like:
    
    A1::Snickers::2.00::20
    B2::Anime Mouse Pad:::5.00::10
    C3::Pencil::0.50::50
    D4::iPhone::10.00::5
    E5::Cat::15.00::1
    
    */
    
    @Test
    public void testLoadAllItems() throws VendingPersistenceException {
        // ARRANGE
//        List<Item> arrangedList = new ArrayList<>();
        
        String itemId1 = "A1";
        Item item1 = new Item(itemId1, "Snickers", new BigDecimal("2.00"), 20);
//        arrangedList.add(item1);
        
        String itemId2 = "B2";
        Item item2 = new Item(itemId2, "Anime Mouse Pad", new BigDecimal("5.00"), 10);
//        arrangedList.add(item2);
        
        String itemId3 = "C3";
        Item item3 = new Item(itemId3, "Pencil", new BigDecimal("0.50"), 50);
//        arrangedList.add(item3);
        
        String itemId4 = "D4";
        Item item4 = new Item(itemId4, "iPhone", new BigDecimal("10.00"), 5);
//        arrangedList.add(item4);
        
        String itemId5 = "E5";
        Item item5 = new Item(itemId5, "Cat", new BigDecimal("15.00"), 1);
//        arrangedList.add(item5);
        
        // ACT
        testDao.loadAllItems();
        
        List<Item> loadedList = testDao.getAllItems();
        
        // ASSERT
        Assertions.assertEquals(5, loadedList.size(),
                "Loaded list should have 5 items");
        Assertions.assertTrue(loadedList.contains(item1),
                "First item should be in the loaded list");
        Assertions.assertTrue(loadedList.contains(item2),
                "Second item should be in the loaded list");
        Assertions.assertTrue(loadedList.contains(item3),
                "Third item should be in the loaded list");
        Assertions.assertTrue(loadedList.contains(item4),
                "Fourth item should be in the loaded list");
        Assertions.assertTrue(loadedList.contains(item5),
                "Fifth item should be in the loaded list");
         
    }
    
    @Test
    public void testSaveAllChangesAndLoadAllItems() throws VendingPersistenceException {
        // ARRANGE
        String itemId1 = "A1";
        Item item1 = new Item(itemId1, "Snickers", new BigDecimal("2.00"), 20);
        
        String itemId2 = "B2";
        Item item2 = new Item(itemId2, "Anime Mouse Pad", new BigDecimal("5.00"), 10);
        
        String itemId3 = "C3";
        Item item3 = new Item(itemId3, "Pencil", new BigDecimal("0.50"), 50);
        
        String itemId4 = "D4";
        Item item4 = new Item(itemId4, "iPhone", new BigDecimal("10.00"), 5);
        
        String itemId5 = "E5";
        Item item5 = new Item(itemId5, "Cat", new BigDecimal("15.00"), 1);
        
        // ACT
        testDao.addItem(item1);
        testDao.addItem(item2);
        testDao.addItem(item3);
        testDao.addItem(item4);
        testDao.addItem(item5);
        
//        List<Item> beforeWAR = testDao.getAllItems();
        
        testDao.saveAllChanges();
        testDao.loadAllItems();
        
        List<Item> afterWAR = testDao.getAllItems();
        
        // ASSERT
        Assertions.assertEquals(5, afterWAR.size(),
                "Inventory after write & read should have 5 items");
        Assertions.assertTrue(afterWAR.contains(item1),
                "First item should be in the list after write & read");
        Assertions.assertTrue(afterWAR.contains(item2),
                "Second item should be in the list after write & read");
        Assertions.assertTrue(afterWAR.contains(item3),
                "Third item should be in the list after write & read");
        Assertions.assertTrue(afterWAR.contains(item4),
                "Fourth item should be in the list after write & read");
        Assertions.assertTrue(afterWAR.contains(item5),
                "Fifth item should be in the list after write & read");
        
    }
    
}
