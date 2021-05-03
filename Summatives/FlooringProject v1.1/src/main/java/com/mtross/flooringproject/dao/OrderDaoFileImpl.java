/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.flooringproject.dao;

import com.mtross.flooringproject.daoexceptions.FlooringPersistenceException;
import com.mtross.flooringproject.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mike
 */
public class OrderDaoFileImpl implements OrderDao {

    public static String ORDER_FILE;
    public static final String FORMAT = "MMddyyyy";
    public static final String DELIMITER = ",";

    public OrderDaoFileImpl() {
        ORDER_FILE = "Orders/Orders_MMddyyyy.txt";
    }

    public OrderDaoFileImpl(String filename) {
        ORDER_FILE = filename;
    }

    private Map<LocalDate, File> orderFiles = new HashMap<>();
    private Map<Integer, LocalDate> orderDates = new HashMap<>();
    private TreeMap<Integer, Order> orders = new TreeMap<>();

    @Override
    public void loadFromFiles() throws FlooringPersistenceException {
        mapOrderFiles();
        readOrders();
    }

    @Override
    public void saveToAllFiles() throws FlooringPersistenceException {
        writeOrders();
    }

    @Override
    public Order addAnOrder(Order newOrder, LocalDate date) {
        int orderNum;

        if (orders.isEmpty()) {
            orderNum = 1;
        } else {
            orderNum = orders.lastKey() + 1;
        }

        newOrder.setOrderNumber(orderNum);
        orderDates.put(orderNum, date);
        return orders.put(orderNum, newOrder);
    }

    @Override
    public Order getAnOrder(int orderNum) {
        return orders.get(orderNum);
    }

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public List<Order> getDatedOrders(LocalDate date) {
        ArrayList<Order> allOrders = new ArrayList<>(orders.values());
        ArrayList<Order> datedOrders = new ArrayList<>();

        for (Order currentOrder : allOrders) {
            int orderNum = currentOrder.getOrderNumber();
            if (orderDates.get(orderNum).equals(date)) {
                datedOrders.add(currentOrder);
            }
        }

        return datedOrders;
    }

    @Override
    public Order updateAnOrder(int orderNum, Order newOrder) {
        newOrder.setOrderNumber(orderNum);
        return orders.replace(orderNum, newOrder);
    }

    @Override
    public Order removeAnOrder(int orderNum) {
        // TODO: improve this method, and/or improve the writeOrders method
        orderDates.remove(orderNum);
        return orders.remove(orderNum);
    }

    private void mapOrderFiles() throws FlooringPersistenceException {
        String[] splitOrderFile = ORDER_FILE.split("/");

        File[] finder;
        File dir = new File(splitOrderFile[0]);

        finder = dir.listFiles((File dir0, String filename)
                -> filename.endsWith(".txt"));

        for (File currentFile : finder) {
            String filename = currentFile.getName();
            LocalDate date = this.getDateFromFilename(filename);
            orderFiles.put(date, currentFile);
        }
    }

    private void readOrders() throws FlooringPersistenceException {
        Scanner scanner = null;

        for (LocalDate currentDate : orderFiles.keySet()) {
            String filename = this.getFilenameFromDate(currentDate);

            try {
                scanner = new Scanner(
                        new BufferedReader(
                                new FileReader(filename)));
            } catch (FileNotFoundException e) {
                throw new FlooringPersistenceException(
                        "\"-_- Could not load order data into memory.", e);
            }

            // Each order file has a legend at the top; advance past it first
            scanner.nextLine();

            String currentLine;
            Order currentOrder;

            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                currentOrder = unmarshallOrder(currentLine);
                int orderNum = currentOrder.getOrderNumber();
                orderDates.put(orderNum, currentDate);
                orders.put(orderNum, currentOrder);
            }
        }

        scanner.close();
    }

    private void writeOrders() throws FlooringPersistenceException {
        PrintWriter out = null;

        // Creates a dummy file so that the PrintWriter can be closed without issue
        String dummy = "dummy.txt";
        File dummyFile = new File(dummy);
        try {
            out = new PrintWriter(
                    new FileWriter(dummy));
        } catch (IOException ex) {
            Logger.getLogger(OrderDaoFileImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

//        for (LocalDate currentDate : orderDates.values()) {
//            String filename = this.getFilenameFromDate(currentDate);
//            
//            try {
//                out = new PrintWriter(
//                      new FileWriter(filename));
//            } catch (IOException e) {
//                throw new FlooringPersistenceException(
//                        "Could not save order data", e);
//            }
//            
//            // Print the legend at the top of each file
//            out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,"
//                      + "Area,CostPerSquareFoot,LaborCostPerSquareFoot,"
//                      + "MaterialCost,LaborCost,Tax,Total");
//            out.flush();
//            
//            String orderAsText;
//            List<Order> orderList = this.getAllOrders();
//            
//            for (Order currentOrder : orderList) {
//                int orderNum = currentOrder.getOrderNumber();
//                LocalDate date = orderDates.get(orderNum);
//                if (date.equals(currentDate)) {
//                    orderAsText = marshallOrder(currentOrder);
//                    out.println(orderAsText);
//                    out.flush();
//                }
//            }
//        }
        for (File currentFile : orderFiles.values()) {
            String currentFilename = currentFile.getName();
            LocalDate currentDate = getDateFromFilename(currentFilename);
            List<Order> datedOrders = getDatedOrders(currentDate);
            String orderAsText;

            if (datedOrders.isEmpty()) {
                currentFile.delete();
            } else {

                try {
                    out = new PrintWriter(new FileWriter(currentFilename));
                } catch (IOException e) {
                    throw new FlooringPersistenceException(
                            "Could not save order data to file.", e);
                }

                // Print the legend at the top of each file
                out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,"
                          + "Area,CostPerSquareFoot,LaborCostPerSquareFoot,"
                          + "MaterialCost,LaborCost,Tax,Total");
                out.flush();
                
                for (Order currentOrder : datedOrders) {
                    orderAsText = marshallOrder(currentOrder);
                    out.println(orderAsText);
                    out.flush();
                }

            }

        }

        dummyFile.delete();
        out.close();
    }

    private Order unmarshallOrder(String orderAsText) {
        String[] orderTokens = orderAsText.split(DELIMITER);

        Order currentOrder = new Order(Integer.parseInt(orderTokens[0]));
        currentOrder.setCustomerName(orderTokens[1]);
        currentOrder.setState(orderTokens[2]);
        currentOrder.setTaxRate(new BigDecimal(orderTokens[3]));
        currentOrder.setProductType(orderTokens[4]);
        currentOrder.setArea(new BigDecimal(orderTokens[5]));
        currentOrder.setCostPerSquareFoot(new BigDecimal(orderTokens[6]));
        currentOrder.setLaborCostPerSquareFoot(new BigDecimal(orderTokens[7]));
        currentOrder.setMaterialCost(new BigDecimal(orderTokens[8]));
        currentOrder.setLaborCost(new BigDecimal(orderTokens[9]));
        currentOrder.setTax(new BigDecimal(orderTokens[10]));
        currentOrder.setTotal(new BigDecimal(orderTokens[11]));

        return currentOrder;
    }

    private String marshallOrder(Order anOrder) {
        String orderAsText = anOrder.getOrderNumber() + DELIMITER;
        orderAsText += anOrder.getCustomerName() + DELIMITER;
        orderAsText += anOrder.getState() + DELIMITER;
        orderAsText += anOrder.getTaxRate() + DELIMITER;
        orderAsText += anOrder.getProductType() + DELIMITER;
        orderAsText += anOrder.getArea() + DELIMITER;
        orderAsText += anOrder.getCostPerSquareFoot() + DELIMITER;
        orderAsText += anOrder.getLaborCostPerSquareFoot() + DELIMITER;
        orderAsText += anOrder.getMaterialCost() + DELIMITER;
        orderAsText += anOrder.getLaborCost() + DELIMITER;
        orderAsText += anOrder.getTax() + DELIMITER;
        orderAsText += anOrder.getTotal();

        return orderAsText;
    }

    private LocalDate getDateFromFilename(String filename) {
        String[] splitFile = filename.split("_");
        String dateAsText = splitFile[1].replace(".txt", "");
        LocalDate date = LocalDate.parse(dateAsText,
                DateTimeFormatter.ofPattern(FORMAT));

        return date;
    }

    private String getFilenameFromDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);
        String dateString = date.format(formatter);
        String filename = ORDER_FILE.replace(FORMAT, dateString);

        return filename;
    }

}
