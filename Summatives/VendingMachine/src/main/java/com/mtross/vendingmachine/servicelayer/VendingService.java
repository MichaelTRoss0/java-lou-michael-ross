/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.vendingmachine.servicelayer;

import com.mtross.vendingmachine.daoexception.VendingPersistenceException;
import com.mtross.vendingmachine.dto.Change;
import com.mtross.vendingmachine.serviceexception.VendingNoItemInventoryException;
import com.mtross.vendingmachine.serviceexception.VendingInsufficientFundsException;
import com.mtross.vendingmachine.dto.ChangePurse;
import com.mtross.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author mike
 */
public interface VendingService {
    
    public void loadMachine()
        throws VendingPersistenceException;
    public void saveMachineState()
        throws VendingPersistenceException;
    
    public List<Item> getAllItemsInMachine();
    public Item getOneItem(String vendingSlot);
    
    public ChangePurse purchaseItem(String vendingSlot, BigDecimal money)
        throws VendingInsufficientFundsException,
               VendingNoItemInventoryException,
               VendingPersistenceException;

    public BigDecimal addMoney(Change.Coin coin)
        throws VendingPersistenceException;

    public void writeExceptionToFile()
        throws VendingPersistenceException;;
    
}
