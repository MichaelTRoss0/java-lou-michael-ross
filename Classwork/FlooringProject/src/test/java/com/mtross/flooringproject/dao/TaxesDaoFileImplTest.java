/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.flooringproject.dao;

import com.mtross.flooringproject.daoexceptions.FlooringPersistenceException;
import com.mtross.flooringproject.dto.Taxes;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author mike
 */
public class TaxesDaoFileImplTest {
    
    TaxesDao testDao;
    
    public TaxesDaoFileImplTest() {
        testDao = new TaxesDaoFileImpl();
    }

    @Test
    public void testEmptyDao() {
        // ARRANGE
        
        // ACT
        List<Taxes> emptyList = testDao.getAllTaxes();
        
        // ASSERT
        Assertions.assertNotNull(emptyList,
                "Should be an empty list, not null");
        Assertions.assertEquals(0, emptyList.size(),
                "Should be an empty list");
    }

    @Test
    public void testLoadAndGet() throws FlooringPersistenceException {
        // ARRANGE
        String abbrTX = "TX";
        Taxes taxInTX = new Taxes(abbrTX);
        taxInTX.setStateName("Texas");
        taxInTX.setTaxRate(new BigDecimal("4.45"));
        
        // ACT
        testDao.loadFromFile();
        
        Taxes taxOut = testDao.getATax(abbrTX);
        
        // ASSERT
        Assertions.assertNotNull(taxOut,
                "The taxes from the dao should not be null");
        Assertions.assertEquals(taxInTX, taxOut,
                "The taxes for Texas should have been in the file");
    }
    
    @Test
    public void LoadAndGetAll() throws FlooringPersistenceException {
        // ARRANGE
        String abbrTX = "TX";
        Taxes taxInTX = new Taxes(abbrTX);
        taxInTX.setStateName("Texas");
        taxInTX.setTaxRate(new BigDecimal("4.45"));
        
        String abbrWA = "WA";
        Taxes taxInWA = new Taxes(abbrWA);
        taxInWA.setStateName("Washington");
        taxInWA.setTaxRate(new BigDecimal("9.25"));
        
        String abbrKY = "KY";
        Taxes taxInKY = new Taxes(abbrKY);
        taxInKY.setStateName("Kentucky");
        taxInKY.setTaxRate(new BigDecimal("6.00"));
        
        String abbrCA = "CA";
        Taxes taxInCA = new Taxes(abbrCA);
        taxInCA.setStateName("California");
        taxInCA.setTaxRate(new BigDecimal("25.00"));
        
        // ACT
        testDao.loadFromFile();
        
        List<Taxes> loadedList = testDao.getAllTaxes();
        
        // ASSERT
        Assertions.assertEquals(4, loadedList.size(),
                "Loaded list should have 4 taxes");
        Assertions.assertTrue(loadedList.contains(taxInTX),
                "Texas taxes should be in the list");
        Assertions.assertTrue(loadedList.contains(taxInWA),
                "Washington taxes should be in the list");
        Assertions.assertTrue(loadedList.contains(taxInKY),
                "Kentucky taxes should be in the list");
        Assertions.assertTrue(loadedList.contains(taxInCA),
                "California taxes should be in the list");
    }
    
}
