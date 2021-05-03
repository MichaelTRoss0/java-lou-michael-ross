/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.flooringproject.dao;

import com.mtross.flooringproject.daoexceptions.FlooringPersistenceException;
import com.mtross.flooringproject.dto.Taxes;
import java.util.List;

/**
 *
 * @author mike
 */
public interface TaxesDao {
    
    public void loadFromFile()
            throws FlooringPersistenceException;
    
    public Taxes getATax(String stateAbbr);
    public List<Taxes> getAllTaxes();
        
}
