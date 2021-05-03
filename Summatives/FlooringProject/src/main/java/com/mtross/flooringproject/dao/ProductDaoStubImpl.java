/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.flooringproject.dao;

import com.mtross.flooringproject.daoexceptions.FlooringPersistenceException;
import com.mtross.flooringproject.dto.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mike
 */
public class ProductDaoStubImpl implements ProductDao {

    private Product onlyProduct;
    private List<Product> productList = new ArrayList<>();

    public ProductDaoStubImpl() {
        onlyProduct = new Product("Wood");
        onlyProduct.setCostPerSquareFoot(new BigDecimal("5.15"));
        onlyProduct.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        
        this.productList.add(onlyProduct);
    }
    
    @Override
    public void loadFromFile() throws FlooringPersistenceException {
        // Does nothing by design.
    }

    @Override
    public Product getAProduct(String productType) {
        if (productType.equals(onlyProduct.getProductType())) {
            return onlyProduct;
        } else {
            return null;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productList;
    }
    
}
