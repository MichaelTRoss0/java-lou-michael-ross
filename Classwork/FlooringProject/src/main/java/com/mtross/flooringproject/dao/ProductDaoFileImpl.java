/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.flooringproject.dao;

import com.mtross.flooringproject.daoexceptions.FlooringPersistenceException;
import com.mtross.flooringproject.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author mike
 */
public class ProductDaoFileImpl implements ProductDao {

    public static final String PRODUCTS_FILE = "Data/Products.txt";
    public static final String DELIMITER = ",";
    
    private Map<String, Product> products = new HashMap<>();
    
    @Override
    public void loadFromFile() throws FlooringPersistenceException {
        readProduct();
    }

    @Override
    public Product getAProduct(String productType) {
        return products.get(productType);
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
    
    private void readProduct() throws FlooringPersistenceException {
        Scanner scanner;
        
        try {
            scanner = new Scanner(
                      new BufferedReader(
                      new FileReader(PRODUCTS_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException(
                    "\"-_- Could not load order data into memory.", e);
        }
        
        // Advance past the legend first
        scanner.nextLine();
        
        String currentLine;
        Product currentProduct;
        
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentProduct = unmarshallProduct(currentLine);
            String productType = currentProduct.getProductType();
            products.put(productType, currentProduct);
        }
        
        scanner.close();
    }
    
    private Product unmarshallProduct(String productAsText) {
        String[] productTokens = productAsText.split(DELIMITER);
        
        Product currentProduct = new Product(productTokens[0]);
        currentProduct.setCostPerSquareFoot(new BigDecimal(productTokens[1]));
        currentProduct.setLaborCostPerSquareFoot(new BigDecimal(productTokens[2]));
        
        return currentProduct;
    }
    
}
