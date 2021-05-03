/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.flooringproject.controller;

import com.mtross.flooringproject.daoexceptions.FlooringPersistenceException;
import com.mtross.flooringproject.dto.Order;
import com.mtross.flooringproject.dto.Product;
import com.mtross.flooringproject.servicelayer.FlooringService;
import com.mtross.flooringproject.servicelayerexceptions.FlooringInvalidInputException;
import com.mtross.flooringproject.ui.FlooringView;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author mike
 */
public class FlooringController {

    FlooringService service;
    FlooringView view;

    public FlooringController(FlooringService service, FlooringView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {

        boolean keepGoing = true;
        int userInput;

        try {

            openAllFiles();

            while (keepGoing) {

                userInput = getMenuSelection();
                switch (userInput) {
                    case 1:
                        displayOrders();
                        break;
                    case 2:
                        addAnOrder();
                        break;
                    case 3:
                        editAnOrder();
                        break;
                    case 4:
                        removeAnOrder();
                        break;
                    case 5:
                        saveCurrentWork();
                        break;
                    case 6:
                        quit();
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }

            exitMessage();

        } catch (FlooringPersistenceException e) {
            errorMessage(e.getMessage());
        }

    }   // End of run()

    private void openAllFiles() throws FlooringPersistenceException {
        view.displayOpeningFilesBanner();
        service.loadAllFiles();
        view.displayOpenedFilesBanner();
    }

    private void displayOrders() {
        List<Order> orders;
        LocalDate date;

        try {
            date = view.getOrderDate();
            service.verifyDate();
            view.displayDisplayOrdersBanner(date);
            orders = service.getOrdersByDate(date);
            view.printOrders(orders);
        } catch (FlooringInvalidInputException e) {
            String errorMsg = "No order exists for that date.";
            view.displayErrorMessage(errorMsg);
        }
    }

    private void addAnOrder() {
        view.displayAddAnOrderBanner();
        LocalDate date = LocalDate.MIN;
        String name = "";
        String state = "";
        String productType = "";
        BigDecimal area = BigDecimal.ZERO;
        Order newOrder;

        boolean tryAgain = false;
        do {
            try {
                date = view.getNewOrderDate();
                service.verifyNewOrderDate(date);
            } catch (FlooringInvalidInputException e) {
                String errorMsg = "The date for a new order must be in the future.";
                view.displayErrorMessage(errorMsg);
                tryAgain = true;
            }
        } while (tryAgain);

        tryAgain = false;
        do {
            try {
                name = view.getNewName();
                service.verifyName(name);
            } catch (FlooringInvalidInputException e) {
                String errorMsg = "The name cannot be blank and can only contain"
                        + "[a-z][1-9] as well as period and comma characters";
                view.displayErrorMessage(errorMsg);
                tryAgain = true;
            }
        } while (tryAgain);

        tryAgain = false;
        do {
            try {
                state = view.getNewState();
                state = service.verifyState(state);
            } catch (FlooringInvalidInputException e) {
                String errorMsg = "We do not sell in that state, or you have "
                        + "not entered a valid state name or abbreviation.";
                view.displayErrorMessage(errorMsg);
                tryAgain = true;
            }
        } while (tryAgain);

        tryAgain = false;
        List<Product> products = service.getEveryProduct();
        do {
            try {
                productType = view.getNewProductType(products);
                service.verifyProduct(productType);
            } catch (FlooringInvalidInputException e) {
                String errorMsg = "That is not on the list of products we sell.";
                view.displayErrorMessage(errorMsg);
                tryAgain = true;
            }
        } while (tryAgain);

        tryAgain = false;
        do {
            try {
                area = view.getNewArea();
                service.verifyArea(area);
            } catch (FlooringInvalidInputException e) {
                String errorMsg = "The area must be a positive decimal, and "
                        + "must be 100 square feet at minimum.";
                view.displayErrorMessage(errorMsg);
                tryAgain = true;
            }
        } while (tryAgain);

        newOrder = service.makeNewOrder(name, state, productType, area);
        view.displayNewOrderSummaryBannerAndNewOrderSummary(newOrder);
        String prompt = "Would you like to place this order?";
        if (userConfirm(prompt)) {
            service.addOrder(newOrder, date);
            view.displayOrderSavedToMemBanner();
        }
    }

    private void editAnOrder() {
        view.displayEditAnOrderBanner();
        LocalDate date;
        int orderNum = 0;
        Order oldOrder = new Order();
        String oldName;
        String oldState;
        String oldProductType;
        BigDecimal oldArea;
        Order newOrder;
        String newName = "";
        String newState = "";
        String newProductType = "";
        BigDecimal newArea = BigDecimal.ZERO;

        boolean tryAgain = false;
        do {
            try {
                date = view.getOrderEditDate();
                orderNum = view.getOrderNumber();
                service.verifyDateAndOrderNum(date, orderNum);
                oldOrder = service.getOneOrder(orderNum);
                view.displayOrderAndDate(oldOrder, date);
            } catch (FlooringInvalidInputException e) {
                String errorMsg = "The order you requested does not exist, "
                        + "or it does not exist on the date you entered.";
                view.displayErrorMessage(errorMsg);
                tryAgain = true;
            }
        } while (tryAgain);

        oldName = oldOrder.getCustomerName();
        oldState = oldOrder.getState();
        oldProductType = oldOrder.getProductType();
        oldArea = oldOrder.getArea();

        tryAgain = false;
        do {
            try {
                newName = view.editName(oldName);
                service.verifyName(newName);
            } catch (FlooringInvalidInputException e) {
                String errorMsg = "The name cannot be blank and can only contain"
                        + "[a-z][1-9] as well as period and comma characters.";
                view.displayErrorMessage(errorMsg);
                tryAgain = true;
            }
        } while (tryAgain);

        tryAgain = false;
        do {
            try {
                newState = view.editState(oldState);
                newState = service.verifyState(newState);
            } catch (FlooringInvalidInputException e) {
                String errorMsg = "We do not sell in that state, or you have "
                        + "not entered a valid state name or abbreviation";
                view.displayErrorMessage(errorMsg);
                tryAgain = true;
            }
        } while (tryAgain);

        tryAgain = false;
        do {
            try {
                newProductType = view.editProductType(oldProductType);
                service.verifyProduct(newProductType);
            } catch (FlooringInvalidInputException e) {
                String errorMsg = "That is not on the list of products we sell";
                view.displayErrorMessage(errorMsg);
                tryAgain = true;
            }
        } while (tryAgain);

        tryAgain = false;
        do {
            try {
                newArea = view.editArea(oldArea);
                service.verifyArea(newArea);
            } catch (FlooringInvalidInputException e) {
                String errorMsg = "The area must be a positive decimal, and "
                        + "must be at least 100 sq ft at minimum";
                view.displayErrorMessage(errorMsg);
                tryAgain = true;
            }
        } while (tryAgain);

        newOrder = service.makeNewOrder(newName, newState, newProductType, newArea);
        view.displayEditedOrderSummaryBannerAndEditedOrderSummary(oldOrder, newOrder);
        if (!oldOrder.equals(newOrder)) {
            String prompt = "Would you like to replace the old order with the new order?";
            if (userConfirm(prompt)) {
                service.editOrder(orderNum, newOrder);
                view.displayEditSavedToMemBanner();
            }
        }
    }

    private void removeAnOrder() {
        view.displayRemoveAnOrderBanner();
        LocalDate date;
        int orderNum = 0;
        Order order;

        boolean tryAgain = false;
        do {
            try {
                date = view.getOrderDate();
                orderNum = view.getOrderNumber();
                service.verifyDateAndOrderNum(date, orderNum);
                order = service.getOneOrder(orderNum);
                view.displayOrderAndDate(order, date);
            } catch (FlooringInvalidInputException e) {
                String errorMsg = "The order you requested does not exist, "
                        + "or it does not exist on the date you entered.";
                view.displayErrorMessage(errorMsg);
                tryAgain = true;
            }
        } while (tryAgain);

        String prompt = "This order cannot be recovered if you save afterwards. "
                + "Do you really want to delete it?";
        if (userConfirm(prompt)) {
            service.removeOrder(orderNum);
            view.displayOrderRemovedFromMemBanner();
        }
    }

    private void saveCurrentWork() throws FlooringPersistenceException {
        view.displaySaveCurrentWorkBanner();
        String prompt = "Would you like to save all changes?";
        if (userConfirm(prompt)) {
            save();
        }
    }

    private void quit() throws FlooringPersistenceException {
        view.displayQuitProgramBanner();
        String prompt = "Would you like to save changes before exiting?";
        if (userConfirm(prompt)) {
            save();
        }
        exitMessage();
    }

    private void save() throws FlooringPersistenceException {
        view.displaySavingChangesBanner();
        service.saveAllChanges();
        view.displaySavedChangesBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private int getMenuSelection() {
        return view.printMainMenuAndGetSelection();
    }

    private void exitMessage() {
        view.displayExitMessage();
    }

    private void errorMessage(String message) {
        view.displayErrorMessage(message);
    }

    private boolean userConfirm(String prompt) {
        return view.getConfirmation(prompt);
    }
}
