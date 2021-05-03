/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.vendingmachine.ui;

import com.mtross.vendingmachine.dto.ChangePurse;
import com.mtross.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author mike
 */
public class VendingMachineView {
    
    UserIO io;
    
    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displayOpeningInventoryBanner() {
        io.print("Loading inventory from file...");
    }

    public void displayInventoryOpenedBanner() {
        io.print("Inventory successfully loaded into memory!");
    }

    public void displayClosingInventoryBanner() {
        io.print("Saving inventory to file...");
    }

    public void displayInventoryClosedBanner() {
        io.print("Inventory successfully saved to file!");
    }

    public void printInventoryAvailable(List<Item> inventory) {
        for (Item item : inventory) {
            if (item.getQuantity() > 0) {
                String id = item.getSlotId();
                String name = item.getName();
                BigDecimal cost = item.getCost();
                io.print(id + " - " + name + ":    $" + cost);
            }
        }
    }

    public void printInstructions() {
        io.print("Enter 'h' or 'i' to display these (i)nstructions again for (h)elp");
        io.print("Enter 'm' to print the current available (m)enu");
        io.print("Enter 'b' to print out the total amount of money you have deposited");
        io.print("Enter 'q', 'd', 'n', or 'p' to insert a (q)uarter, (n)ickel, (d)ime, or (p)enny in the vending machine respectively");
        io.print("Enter 's' to start making a (s)election after depositing money");
        io.print("Enter 'exit' to leave the program and save all changes");
    }

    public String displayGetUserInputBanner() {
        return io.readString("Enter your input now:");
    }

    public void displayMoneydepositedBanner(BigDecimal money) {
        io.print("You have deposited $" + money + " in change");
    }

    public void displayMoneyRequiredBanner() {
        io.print("You must deposit money into the vending machine before you can make a selection");
    }

    public String getSlotSelection() {
        return io.readString("Please enter the Slot ID of the item you wish to purchase:");
    }

    public void displayItemPurchasedBanner(String name, BigDecimal cost) {
        io.print("You have purchased: " + name + " for $" + cost);
    }

    public void displayChangeDispensed(ChangePurse changePurse) {
        io.print("Your change is:");
        io.print(changePurse.getNumPennies() + " pennies");
        io.print(changePurse.getNumNickels() + " nickels");
        io.print(changePurse.getNumDimes() + " dimes");
        io.print(changePurse.getNumQuarters() + " Quarters");
    }

    public void displayInsufficientFundsBanner() {
        io.print("You have not yet deposited enough money to purchase this item");
    }

    public void displayOutOfStockBanner() {
        io.print("This item is no longer in stock and cannot be purchased");
    }

    public void displayInvalidIdBanner() {
        io.print("The ID you entered does not match to any item in the current inventory");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
}
