/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.vendingmachine.controller;

import com.mtross.vendingmachine.daoexception.VendingPersistenceException;
import com.mtross.vendingmachine.dto.Change;
import com.mtross.vendingmachine.dto.ChangePurse;
import com.mtross.vendingmachine.dto.Item;
import com.mtross.vendingmachine.serviceexception.VendingInsufficientFundsException;
import com.mtross.vendingmachine.serviceexception.VendingNoItemInventoryException;
import com.mtross.vendingmachine.servicelayer.VendingService;
import com.mtross.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author mike
 */
public class VendingMachineController {
    
    VendingService service;
    VendingMachineView view;

    public VendingMachineController(VendingService service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }
    
    public void run() {
        
        boolean keepGoing = true;
        String userInput;
        BigDecimal money = BigDecimal.ZERO;
        String itemId;
        int vendingFlow;
        try {
            openInventory();
            displayInventoryMenu();
            displayInstructions();
            while (keepGoing) {

                userInput = getUserInput();

                switch (userInput) {
                    case "h":
                    case "H":
                    case "i":
                    case "I":
                    case "help":
                    case "instructions":
                        displayInstructions();
                        break;
                    case "m":
                    case "M":
                        displayInventoryMenu();
                        break;
                    case "b":
                    case "B":
                    case "balance":
                        displayMoneyDeposited(money);
                        break;

                    case "q":
                    case "Q":
                    case "quarter":
                        money = money.add(insertCoin(Change.Coin.QUARTER));
                        displayMoneyDeposited(money);
                        break;
                    case "d":
                    case "D":
                    case "dime":
                        money = money.add(insertCoin(Change.Coin.DIME));
                        displayMoneyDeposited(money);
                        break;
                    case "n":
                    case "N":
                    case "nickel":
                        money = money.add(insertCoin(Change.Coin.NICKEL));
                        displayMoneyDeposited(money);
                        break;
                    case "p":
                    case "P":
                    case "penny":
                        money = money.add(insertCoin(Change.Coin.PENNY));
                        displayMoneyDeposited(money);
                        break;

                    case "s":
                    case "S":
                    case "select":
                        if (money.compareTo(BigDecimal.ZERO) == 1) {
                            itemId = makeSelection();
                            vendingFlow = canPurchase(itemId, money);
                            switch (vendingFlow) {
                                case 1:
                                    try {
                                        makePurchase(itemId, money);
                                        money = BigDecimal.ZERO;
                                    } catch (VendingNoItemInventoryException e) {
                                        outOfStock();
                                    }
                                    break;
                                case 2:
                                    try {
                                        makePurchase(itemId, money);
                                    } catch (VendingInsufficientFundsException e) {
                                        purchaseDenied();
                                        displayMoneyDeposited(money);
                                    }
                                    break;
                                case 3:
                                    invalidId();
                                    break;
                                default:
                                    unknownCommand();
                            }
                        } else {
                            requestMoney();
                        }
                        break;

                    case "exit":
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();

                }

            }
            closeInventory();
            exitMessage();
        } catch ( VendingPersistenceException
                | VendingInsufficientFundsException
                | VendingNoItemInventoryException e ) {
            
            try {
                requestExceptionAudit();
            } catch (VendingPersistenceException ex) {
                view.displayErrorMessage(e.getMessage());
            }
            
            view.displayErrorMessage(e.getMessage());
        }
        
    }   // End of run()
    
    
    
    private void openInventory() throws VendingPersistenceException {
        view.displayOpeningInventoryBanner();
        service.loadMachine();
        view.displayInventoryOpenedBanner();
    }
    
    private void closeInventory() throws VendingPersistenceException {
        view.displayClosingInventoryBanner();
        service.saveMachineState();
        view.displayInventoryClosedBanner();
    }

    private void displayInventoryMenu() {
        List<Item> inventory = service.getAllItemsInMachine();
        view.printInventoryAvailable(inventory);
    }
    
    private void displayInstructions() {
        view.printInstructions();
    }

    private String getUserInput() {
        return view.displayGetUserInputBanner();
    }

    private BigDecimal insertCoin(Change.Coin coin) throws VendingPersistenceException {
        return service.addMoney(coin);
    }

    private void displayMoneyDeposited(BigDecimal money) {
        view.displayMoneydepositedBanner(money);
    }

    private void requestMoney() {
        view.displayMoneyRequiredBanner();
    }

    private String makeSelection() {
        return view.getSlotSelection();
    }

    private int canPurchase(String itemId, BigDecimal money) {
        Item item = service.getOneItem(itemId);
        return item.checkPurchase(money);
    }

    private void makePurchase(String itemId, BigDecimal money)
            throws VendingInsufficientFundsException,
                   VendingNoItemInventoryException,
                   VendingPersistenceException {
        
        ChangePurse changePurse = service.purchaseItem(itemId, money);
        Item item = service.getOneItem(itemId);
        view.displayItemPurchasedBanner(item.getName(), item.getCost());
        view.displayChangeDispensed(changePurse);
    }

    private void outOfStock() {
        view.displayOutOfStockBanner();
    }

    private void purchaseDenied() {
        view.displayInsufficientFundsBanner();
    }

    private void invalidId() {
        view.displayInvalidIdBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void requestExceptionAudit() throws VendingPersistenceException {
        service.writeExceptionToFile();
    }

}
