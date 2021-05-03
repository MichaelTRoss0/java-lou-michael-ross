/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.vendingmachine.servicelayer;

import com.mtross.vendingmachine.dto.Change.Coin;
import com.mtross.vendingmachine.dto.ChangePurse;
import com.mtross.vendingmachine.dto.Item;
import com.mtross.vendingmachine.serviceexception.VendingInsufficientFundsException;
import com.mtross.vendingmachine.serviceexception.VendingNoItemInventoryException;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author mike
 */
public class VendingServiceFileImplTest {
    
    private VendingService service;
    
    public VendingServiceFileImplTest() {
        ApplicationContext ctx =
            new ClassPathXmlApplicationContext("applicationContext.xml");
        service =
            ctx.getBean("serviceLayer", VendingService.class);
    }

    @Test
    public void testLoadMachine() throws Exception {
        // Assert that just calling this method will not cause an error
        service.loadMachine();
    }

    @Test
    public void testSaveMachineState() throws Exception {
        // Assert that just calling this method will not cause an error
        service.saveMachineState();
    }
    
    @Test
    public void testWriteExceptionToFile() throws Exception {
        // Assert that just calling this method will not cause an error
        service.writeExceptionToFile();
    }

    @Test
    public void testGetAllItemsInMachine() {
        // ARRANGE
        List<Item> listOfOneItem;
        
        // ACT
        listOfOneItem = service.getAllItemsInMachine();
        
        // ASSERT
        Assertions.assertEquals(1, listOfOneItem.size(),
                "There should be exactly one item in the list");
        
    }

    @Test
    public void testGetOneItem() {
        // ARRANGE
        String rightId = "A1";
        String wrongId = "yeet";
        
        // ACT
        Item sholdNotBeNull = service.getOneItem(rightId);
        Item shouldBeNull = service.getOneItem(wrongId);
        
        // ASSERT
        Assertions.assertNotNull(sholdNotBeNull,
                "The item with the correct ID should not be null.");
        Assertions.assertNull(shouldBeNull,
                "The item with the incorrect ID should be null.");
        
    }
    
    @Test
    public void testAddMoney() throws Exception {
        // ARRANGE
        Coin coinP =  Coin.PENNY;
        Coin coinN =  Coin.NICKEL;
        Coin coinD =  Coin.DIME;
        Coin coinQ =  Coin.QUARTER;
        
        BigDecimal money = new BigDecimal("0.00");
        
        // ACT
        BigDecimal valueP = service.addMoney(coinP);
        BigDecimal valueN = service.addMoney(coinN);
        BigDecimal valueD = service.addMoney(coinD);
        BigDecimal valueQ = service.addMoney(coinQ);
        
        // ASSERT
        Assertions.assertEquals(new BigDecimal("0.01"), valueP, 
                "A penny should have a value of $0.01");
        Assertions.assertEquals(new BigDecimal("0.05"), valueN, 
                "A nickel should have a value of $0.05");
        Assertions.assertEquals(new BigDecimal("0.10"), valueD, 
                "A dime should have a value of $0.10");
        Assertions.assertEquals(new BigDecimal("0.25"), valueQ, 
                "A quarter should have a value of $0.25");
        
    }
    
    @Test
    public void testPurchaseItem() throws Exception {
        // ARRANGE
        String vendingSlot = "A1";
        
        BigDecimal walletExact = new BigDecimal("1.00");
        BigDecimal walletOver  = new BigDecimal("1.41");
        
        ChangePurse emptyPurse = new ChangePurse(0, 0, 0, 0);
        ChangePurse oneEach  = new ChangePurse(1, 1, 1, 1);
        
        // ACT
        ChangePurse shouldBeEmpty = service.purchaseItem(vendingSlot, walletExact);
        ChangePurse shouldBeOneEach = service.purchaseItem(vendingSlot, walletOver);
        
        // ASSERT
        Assertions.assertEquals(emptyPurse, shouldBeEmpty,
                "No change should have been returned.");
        Assertions.assertEquals(oneEach, shouldBeOneEach,
                "One of each coin should have been returned.");
        
    }
    
    @Test
    public void testPurchaseItemInsufficientFunds() throws Exception {
        // ARRANGE
        String vendingSlot = "A1";
        
        BigDecimal walletUnder = new BigDecimal("0.99");
        
        // ACT & ASSERT
        try {
            service.purchaseItem(vendingSlot, walletUnder);
            fail("Expected VendingInsufficientFundsException was not thrown.");
        } catch (VendingInsufficientFundsException e) {
            return;
            // This return statement is unnecessary, bit it is good practice to
              // always put something in the catch block of a try/catch statement
        }
        
    }
    
    @Test
    public void testPurchaseItemNoItemInventory() throws Exception {
        // ARRANGE
        String vendingSlot = "A1";
        
        BigDecimal walletExact = new BigDecimal("1.00");
        
        // ACT & ASSERT
        try {
            service.purchaseItem(vendingSlot, walletExact);
            service.purchaseItem(vendingSlot, walletExact);
            service.purchaseItem(vendingSlot, walletExact);
            fail("Expected VendingNoItemInventoryException was not thrown.");
        } catch (VendingNoItemInventoryException e) {
            return;
            // This return statement is unnecessary, bit it is good practice to
              // always put something in the catch block of a try/catch statement
        }
        
    }
    
}
