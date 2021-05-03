/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.flooringproject.ui;

import com.mtross.flooringproject.dto.Order;
import com.mtross.flooringproject.dto.Product;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author mike
 */
public class FlooringView {

    UserIO io;

    public FlooringView(UserIO io) {
        this.io = io;
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
        io.readString("Press enter to continue.");
    }

    public void displayOpeningFilesBanner() {
        io.print("Loading data from files...");
    }

    public void displayOpenedFilesBanner() {
        io.print("All data successfully loaded into memory!");
    }

    public int printMainMenuAndGetSelection() {
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
        io.print("*  <<Flooring Program>>");
        io.print("* 1. Display Orders");
        io.print("* 2. Add an Order");
        io.print("* 3. Edit an Order");
        io.print("* 4. Remove an Order");
        io.print("* 5. Save Current Work");
        io.print("* 6. Quit");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");

        return io.readInt("Please select an option from the above choices, [1-6].", 1, 6);
    }

    public LocalDate getOrderDate() {
        return io.readLocalDate("Please enter the date in this format: YYYY-MM-DD.");
    }

    public void displayDisplayOrdersBanner(LocalDate date) {
        io.print("=== Display all Orders on " + date.toString() + " ===");
    }

    public void printOrders(List<Order> orders) {
        for (Order currentOrder : orders) {
            io.print(currentOrder.toString());
        }

        io.readString("Press enter to continue.");
    }

    public void displayAddAnOrderBanner() {
        io.print("=== Add an Order ===");
    }

    public LocalDate getNewOrderDate() {
        return io.readLocalDate("Please enter the date for the new order in this format: YYYY-MM-DD.");
    }

    public String getNewName() {
        return io.readString("Please enter the customer name for the new order.");
    }

    public String getNewState() {
        return io.readString("Please enter the State name or abbreviation for the new order.");
    }

    public String getNewProductType(List<Product> products) {
        io.print("Products Available:");
        for (Product currentProduct : products) {
            String productType = currentProduct.getProductType();
            BigDecimal costPerSquareFoot = currentProduct.getCostPerSquareFoot();
            BigDecimal laborCostPerSquareFoot = currentProduct.getLaborCostPerSquareFoot();
            BigDecimal totalCostPerSquareFoot = costPerSquareFoot.add(laborCostPerSquareFoot);
            String totalCost = totalCostPerSquareFoot.toString();

            io.print(productType + ": $" + totalCost + " per square foot for material and labor");
        }
        return io.readString("Please enter the product type for the new order.");
    }

    public BigDecimal getNewArea() {
        return io.readBigDecimal("Please enter the area for the new order.");
    }

    public void displayNewOrderSummaryBannerAndNewOrderSummary(Order order) {
        io.print("=== New Order Summary ===");
        io.print("Customer Name:                " + order.getCustomerName());
        io.print("State:                        " + order.getState());
        io.print("Tax Rate:                     " + order.getTaxRate() + "%");
        io.print("Product Type:                 " + order.getProductType());
        io.print("Area:                         " + order.getArea() + " square feet");
        io.print("Cost Per Square Foot:         $" + order.getCostPerSquareFoot());
        io.print("Labor Cost Per Square Foot:   $" + order.getLaborCostPerSquareFoot());
        io.print("Material Cost:                $" + order.getMaterialCost());
        io.print("Labor Cost:                   $" + order.getLaborCost());
        io.print("Tax:                          $" + order.getTax());
        io.print("Total:                        $" + order.getTotal());
    }

    public boolean getConfirmation(String prompt) {
        boolean goodInput = false;
        String answer;

        do {
            answer = io.readString(prompt + " (y/n)");
            switch (answer) {
                case "y":
                case "Y":
                case "yes":
                case "Yes":
                    return true;
                case "n":
                case "N":
                case "no":
                case "No":
                    return false;
                default:
                    io.print("Please answer with either 'y' or 'n'.");
            }
        } while (!goodInput);

        return false;
    }

    public void displayOrderSavedToMemBanner() {
        io.print("The new order has been placed into temporary memory! Remember to save before exiting.");
        io.readString("Press enter to continue.");
    }

    public void displayEditAnOrderBanner() {
        io.print("=== Edit an Order ===");
    }

    public LocalDate getOrderEditDate() {
        return io.readLocalDate("Please enter the date for the order to edit in this format: YYYY-MM-DD.");
    }

    public int getOrderNumber() {
        return io.readInt("Please enter the order number of the order.");
    }

    public void displayOrderAndDate(Order order, LocalDate date) {
        io.print("This order was made on " + date.toString() + ":");
        io.print(order.toString());
    }

    public String editName(String oldName) {
        String newName = io.readString("Please enter the customer name to change to, "
                + "or press enter to keep the old name (" + oldName + ").");
        if (newName.equals("")) {
            return oldName;
        } else {
            return newName;
        }
    }

    public String editState(String oldState) {
        String newState = io.readString("Please enter the State name or abbreviation to change to, "
                + "or press enter to keep the old State (" + oldState + ").");
        if (newState.equals("")) {
            return oldState;
        } else {
            return newState;
        }
    }

    public String editProductType(String oldProductType) {
        String newProductType = io.readString("Please enter the product type to change to, "
                + "or press enter to keep the old product type (" + oldProductType + ").");
        if (newProductType.equals("")) {
            return oldProductType;
        } else {
            return newProductType;
        }
    }

    public BigDecimal editArea(BigDecimal oldArea) {
        String newArea = io.readString("Please enter the area to change to, "
                + "or press enter to keep the old area (" + oldArea + ").");
        if (newArea.equals("")) {
            return oldArea;
        } else {
            return new BigDecimal(newArea);
        }
    }

    public void displayEditedOrderSummaryBannerAndEditedOrderSummary(Order oldOrder, Order newOrder) {
        io.print("=== Edited Order Summary ===");
        if (!oldOrder.equals(newOrder)) {
            String oldCustomerName = oldOrder.getCustomerName();
            String oldState = oldOrder.getState();
            BigDecimal oldTaxRate = oldOrder.getTaxRate();
            String oldProductType = oldOrder.getProductType();
            BigDecimal oldArea = oldOrder.getArea();
            BigDecimal oldCostPerSquareFoot = oldOrder.getCostPerSquareFoot();
            BigDecimal oldLaborCostPerSquareFoot = oldOrder.getLaborCostPerSquareFoot();
            BigDecimal oldMaterialCost = oldOrder.getMaterialCost();
            BigDecimal oldLaborCost = oldOrder.getLaborCost();
            BigDecimal oldTax = oldOrder.getTax();
            BigDecimal oldTotal = oldOrder.getTotal();

            String newCustomerName = newOrder.getCustomerName();
            String newState = newOrder.getState();
            BigDecimal newTaxRate = newOrder.getTaxRate();
            String newProductType = newOrder.getProductType();
            BigDecimal newArea = newOrder.getArea();
            BigDecimal newCostPerSquareFoot = newOrder.getCostPerSquareFoot();
            BigDecimal newLaborCostPerSquareFoot = newOrder.getLaborCostPerSquareFoot();
            BigDecimal newMaterialCost = newOrder.getMaterialCost();
            BigDecimal newLaborCost = newOrder.getLaborCost();
            BigDecimal newTax = newOrder.getTax();
            BigDecimal newTotal = newOrder.getTotal();

            io.print("The following changes will be made to the order:");
            if (!oldCustomerName.equals(newCustomerName)) {
                io.print("Customer Name:                "
                        + oldCustomerName + " -> " + newCustomerName);
            }
            if (!oldState.equals(newState)) {
                io.print("State:                        "
                        + oldState + " -> " + newState);
            }
            if (oldTaxRate.compareTo(newTaxRate) != 0) {
                io.print("Tax Rate:                     "
                        + oldTaxRate + "% -> " + newTaxRate + "%");
            }
            if (!oldProductType.equals(newProductType)) {
                io.print("Product Type:                 "
                        + oldProductType + " -> " + newProductType);
            }
            if (oldArea.compareTo(newArea) != 0) {
                io.print("Area:                         "
                        + oldArea + " square feet -> " + newArea + " square feet");
            }
            if (oldCostPerSquareFoot.compareTo(newCostPerSquareFoot) != 0) {
                io.print("Cost Per Square Foot:         $"
                        + oldCostPerSquareFoot + " -> $" + newCostPerSquareFoot);
            }
            if (oldLaborCostPerSquareFoot.compareTo(newLaborCostPerSquareFoot) != 0) {
                io.print("Labor Cost Per Square Foot:   $"
                        + oldLaborCostPerSquareFoot + " -> $" + newLaborCostPerSquareFoot);
            }
            if (oldMaterialCost.compareTo(newMaterialCost) != 0) {
                io.print("Material Cost:                $"
                        + oldMaterialCost + " -> $" + newMaterialCost);
            }
            if (oldLaborCost.compareTo(newLaborCost) != 0) {
                io.print("Labor Cost:                   $"
                        + oldLaborCost + " -> $" + newLaborCost);
            }
            if (oldTax.compareTo(newTax) != 0) {
                io.print("Tax:                          $"
                        + oldTax + " -> $" + newTax);
            }
            if (oldTotal.compareTo(newTotal) != 0) {
                io.print("Total:                        $"
                        + oldTotal + " -> $" + newTotal);
            }

        } else {
            io.print("No changes have been made.");
            io.readString("Press enter to continue.");
        }
    }

    public void displayEditSavedToMemBanner() {
        io.print("The edits to this order have been saved to temporary memory! Remember to save before exiting.");
        io.readString("Press enter to continue.");
    }

    public void displayRemoveAnOrderBanner() {
        io.print("=== Remove an Order ===");
    }

    public void displayOrderRemovedFromMemBanner() {
        io.print("The order has been removed from temporary memory. Once you save, it will be gone for good.");
        io.readString("Press enter to continue.");
    }

    public void displaySaveCurrentWorkBanner() {
        io.print("=== Save Changes ===");
    }

    public void displaySavingChangesBanner() {
        io.print("Saving changes to file...");
    }

    public void displaySavedChangesBanner() {
        io.print("All changes have been successfully saved!");
        io.readString("Press enter to continue.");
    }

    public void displayQuitProgramBanner() {
        io.print("=== Quit Program ===");
    }

    public void displayExitMessage() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
        io.readString("Press enter to continue.");
    }

}
