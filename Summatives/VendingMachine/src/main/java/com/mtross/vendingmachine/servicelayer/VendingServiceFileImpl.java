/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.vendingmachine.servicelayer;

import com.mtross.vendingmachine.dao.VendingMachineAuditDao;
import com.mtross.vendingmachine.dao.VendingMachineDao;
import com.mtross.vendingmachine.daoexception.VendingPersistenceException;
import com.mtross.vendingmachine.dto.Change;
import com.mtross.vendingmachine.dto.ChangePurse;
import com.mtross.vendingmachine.dto.Item;
import com.mtross.vendingmachine.serviceexception.VendingInsufficientFundsException;
import com.mtross.vendingmachine.serviceexception.VendingNoItemInventoryException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author mike
 */
public class VendingServiceFileImpl implements VendingService {

    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;

    public VendingServiceFileImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    @Override
    public void loadMachine() throws VendingPersistenceException {
        dao.loadAllItems();
        List<Item> loadedList = dao.getAllItems();
        String itemString = "INVENTORY LOADED:";
        
        for (Item item : loadedList) {
            itemString = itemString.concat(" "
                    + item.getQuantity() + "x" + item.getName());
        }
        
        auditDao.writeAuditEntry(itemString);
    }
    
    @Override
    public void saveMachineState() throws VendingPersistenceException {
        dao.saveAllChanges();
        auditDao.writeAuditEntry("ALL CHANGES SAVED");
    }
    
    public Item addAnItem(Item item) {
        return dao.addItem(item);
    }

    @Override
    public List<Item> getAllItemsInMachine() {
        return dao.getAllItems();
    }

    @Override
    public Item getOneItem(String vendingSlot) {
        return dao.getAnItem(vendingSlot);
    }

    @Override
    public ChangePurse purchaseItem(String vendingSlot, BigDecimal money)
            throws VendingInsufficientFundsException,
                   VendingNoItemInventoryException,
                   VendingPersistenceException {
        
        ChangePurse coinReturn = new ChangePurse(0, 0, 0, 0);
        
        Item item = dao.getAnItem(vendingSlot);
        
        if (item.getQuantity() < 1) {
            auditDao.writeAuditEntry("ERROR: Attempted to buy" + item.getName()
                    + " while it was out of stock.");
            throw new VendingNoItemInventoryException(
                    "This item is out of stock");
        }
        if (item.getCost().compareTo(money) == 1) {
            auditDao.writeAuditEntry("ERROR: Attempted to buy" + item.getName()
                    + " with insufficient funds.");
            throw new VendingInsufficientFundsException(
                    "You did not deposit enough money to purchase this item");
        }
        
        item.setQuantity(item.getQuantity() - 1);
        dao.updateAnItem(vendingSlot, item);
        auditDao.writeAuditEntry("ITEM PURCHASED: " + item.getName());
        
        BigDecimal changeLeftOver = money.subtract(item.getCost());
        if (changeLeftOver.compareTo(BigDecimal.ZERO) == 1) {
            BigDecimal oneHundred = new BigDecimal("100");
            int changeInPennies = changeLeftOver.multiply(oneHundred).intValue();
            Change changeMachine = new Change();
            coinReturn = changeMachine.calculateChange(changeInPennies);

            int numP = coinReturn.getNumPennies();
            int numN = coinReturn.getNumNickels();
            int numD = coinReturn.getNumDimes();
            int numQ = coinReturn.getNumQuarters();
            
            String coinReturnString = "CHANGE RETURNED: $" + changeLeftOver;
            if (numP > 0) {
                coinReturnString = coinReturnString.concat(" " + numP + "xPENNIES");
            }
            if (numN > 0) {
                coinReturnString = coinReturnString.concat(" " + numN + "xNICKELS");
            }
            if (numD > 0) {
                coinReturnString = coinReturnString.concat(" " + numD + "xDIMES");
            }
            if (numQ > 0) {
                coinReturnString = coinReturnString.concat(" " + numQ + "xQUARTERS");
            }

            auditDao.writeAuditEntry(coinReturnString);
        }
        
        return coinReturn;
    }

    @Override
    public BigDecimal addMoney(Change.Coin coin) throws VendingPersistenceException {
        BigDecimal value = Change.getValue(coin);
        auditDao.writeAuditEntry("MONEY DEPOSITED: $" + value.toString());
        return value;
    }

    @Override
    public void writeExceptionToFile() throws VendingPersistenceException {
        auditDao.writeAuditEntry("ERROR: Something went wrong"
                            + " (exception caught in the controller)");
    }
    
}
