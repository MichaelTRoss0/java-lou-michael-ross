/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.flooringproject.dao;

import com.mtross.flooringproject.daoexceptions.FlooringPersistenceException;
import com.mtross.flooringproject.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author mike
 */
public interface OrderDao {
    
    public void loadFromFiles()
            throws FlooringPersistenceException;
    
    public void saveToAllFiles()
            throws FlooringPersistenceException;
    
    public Order addAnOrder(Order newOrder, LocalDate date);
    
    public Order getAnOrder(int orderNum);
    
    public List<Order> getAllOrders();

    public List<Order> getDatedOrders(LocalDate date);
    
    public Order updateAnOrder(int orderNum, Order newOrder);
    
    public Order removeAnOrder(int orderNum);
    
}
