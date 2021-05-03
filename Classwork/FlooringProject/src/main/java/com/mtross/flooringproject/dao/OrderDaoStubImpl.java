/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.flooringproject.dao;

import com.mtross.flooringproject.daoexceptions.FlooringPersistenceException;
import com.mtross.flooringproject.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mike
 */
public class OrderDaoStubImpl implements OrderDao {

    private Order onlyOrder;
    private List<Order> orderList = new ArrayList<>();

    public OrderDaoStubImpl() {
        onlyOrder = new Order(1);
        onlyOrder.setCustomerName("Michael Ross");
        onlyOrder.setState("KY");
        onlyOrder.setTaxRate(new BigDecimal("6.00"));
        onlyOrder.setProductType("Wood");
        onlyOrder.setArea(new BigDecimal("1000.00"));
        onlyOrder.setCostPerSquareFoot(new BigDecimal("5.15"));
        onlyOrder.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        onlyOrder.setMaterialCost(new BigDecimal("5150.00"));
        onlyOrder.setLaborCost(new BigDecimal("4750.00"));
        onlyOrder.setTax(new BigDecimal("594.00"));
        onlyOrder.setTotal(new BigDecimal("10494.00"));
        
        orderList.add(onlyOrder);
    }
    
    @Override
    public void loadFromFiles() throws FlooringPersistenceException {
        // Does nothing by design.
    }

    @Override
    public void saveToAllFiles() throws FlooringPersistenceException {
        // Does nothing by design.
    }

    @Override
    public Order addAnOrder(Order newOrder, LocalDate date) {
        if (newOrder.equals(onlyOrder) && date.equals(LocalDate.now())) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public Order getAnOrder(int orderNum) {
        if (orderNum == onlyOrder.getOrderNumber()) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return orderList;
    }

    @Override
    public List<Order> getDatedOrders(LocalDate date) {
        if (date.equals(LocalDate.now())) {
            return orderList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Order updateAnOrder(int orderNum, Order newOrder) {
        if (orderNum == onlyOrder.getOrderNumber()) {
            return orderList.set(0, newOrder);
        } else {
            return null;
        }
    }

    @Override
    public Order removeAnOrder(int orderNum) {
        if (orderNum == onlyOrder.getOrderNumber()) {
            return onlyOrder;
        } else {
            return null;
        }
    }
    
}
