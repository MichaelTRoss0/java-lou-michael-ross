/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.flooringproject.servicelayer;

import com.mtross.flooringproject.daoexceptions.FlooringPersistenceException;
import com.mtross.flooringproject.dto.Order;
import com.mtross.flooringproject.dto.Product;
import com.mtross.flooringproject.servicelayerexceptions.FlooringInvalidInputException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author mike
 */
public interface FlooringService {

    public void loadAllFiles()
            throws FlooringPersistenceException;

    public List<Order> getEveryOrder();

    public List<Order> getOrdersByDate(LocalDate date)
            throws FlooringInvalidInputException;

    public Order getOneOrder(int orderNum);
    
    public List<Product> getEveryProduct();

    public Order addOrder(Order newOrder, LocalDate date);

    public Order editOrder(int orderNum, Order newOrder);

    public Order removeOrder(int orderNum);

    public void saveAllChanges()
            throws FlooringPersistenceException;

    public void verifyDate(LocalDate date)
            throws FlooringInvalidInputException;

    public void verifyName(String name)
            throws FlooringInvalidInputException;

    public String verifyState(String state)
            throws FlooringInvalidInputException;

    public void verifyProduct(String productType, List<Product> products)
            throws FlooringInvalidInputException;

    public void verifyArea(BigDecimal area)
            throws FlooringInvalidInputException;

    public void verifyDateAndOrderNum(LocalDate date, int orderNum)
            throws FlooringInvalidInputException;

    public Order makeNewOrder(String name, String state,
            String productType, BigDecimal area);

}
