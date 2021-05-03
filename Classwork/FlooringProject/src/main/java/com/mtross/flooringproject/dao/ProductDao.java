/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.flooringproject.dao;

import com.mtross.flooringproject.daoexceptions.FlooringPersistenceException;
import com.mtross.flooringproject.dto.Product;
import java.util.List;

/**
 *
 * @author mike
 */
public interface ProductDao {
    
    public void loadFromFile()
            throws FlooringPersistenceException;
    
    public Product getAProduct(String productType);
    public List<Product> getAllProducts();
    
}
