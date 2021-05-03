/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.flooringproject.dao;

import com.mtross.flooringproject.daoexceptions.FlooringPersistenceException;
import com.mtross.flooringproject.dto.Product;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author mike
 */
public class ProductDaoFileImplTest {
    
    ProductDao testDao;
    
    public ProductDaoFileImplTest() {
        testDao = new ProductDaoFileImpl();
    }

    @Test
    public void testEmptyDao() {
        // ARRANGE
        
        // ACT
        List<Product> emptyList = testDao.getAllProducts();
        
        // ASSERT
        Assertions.assertNotNull(emptyList,
                "Should be an empty list, not null");
        Assertions.assertEquals(0, emptyList.size(),
                "Should be an empty list");
    }

    @Test
    public void testLoadAndGet() throws FlooringPersistenceException {
        // ARRANGE
        String productType1 = "Carpet";
        Product productIn = new Product(productType1);
        productIn.setCostPerSquareFoot(new BigDecimal("2.25"));
        productIn.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        
        // ACT
        testDao.loadFromFile();
        
        Product productOut = testDao.getAProduct(productType1);
        
        // ASSERT
        Assertions.assertNotNull(productOut,
                "The product from the dao should not be null");
        Assertions.assertEquals(productIn, productOut,
                "The product type for carpeting should have been in the file");
    }
    
    @Test
    public void LoadAndGetAll() throws FlooringPersistenceException {
        // ARRANGE
        String productType1 = "Carpet";
        Product product1 = new Product(productType1);
        product1.setCostPerSquareFoot(new BigDecimal("2.25"));
        product1.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        
        String productType2 = "Laminate";
        Product product2 = new Product(productType2);
        product2.setCostPerSquareFoot(new BigDecimal("1.75"));
        product2.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        
        String productType3 = "Tile";
        Product product3 = new Product(productType3);
        product3.setCostPerSquareFoot(new BigDecimal("3.50"));
        product3.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
        
        String productType4 = "Wood";
        Product product4 = new Product(productType4);
        product4.setCostPerSquareFoot(new BigDecimal("5.15"));
        product4.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        
        // ACT
        testDao.loadFromFile();
        
        List<Product> loadedList = testDao.getAllProducts();
        
        // ASSERT
        Assertions.assertEquals(4, loadedList.size(),
                "Loaded list should have 4 taxes");
        Assertions.assertTrue(loadedList.contains(product1),
                "Carpet info should be in the list");
        Assertions.assertTrue(loadedList.contains(product2),
                "Laminate info should be in the list");
        Assertions.assertTrue(loadedList.contains(product3),
                "Tile info should be in the list");
        Assertions.assertTrue(loadedList.contains(product4),
                "Wood info should be in the list");
    }
    
}
