/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.flooringproject.dao;

import com.mtross.flooringproject.daoexceptions.FlooringPersistenceException;
import com.mtross.flooringproject.dto.Taxes;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mike
 */
public class TaxesDaoStubImpl implements TaxesDao {

    private Taxes onlyTaxes;
    private List<Taxes> taxesList = new ArrayList<>();

    public TaxesDaoStubImpl() {
        onlyTaxes = new Taxes("KY");
        onlyTaxes.setStateName("Kentucky");
        onlyTaxes.setTaxRate(new BigDecimal("6.00"));
        
        taxesList.add(onlyTaxes);
    }
    
    @Override
    public void loadFromFile() throws FlooringPersistenceException {
        // Does nothing by design.
    }

    @Override
    public Taxes getATax(String stateAbbr) {
        if (stateAbbr.equals(onlyTaxes.getStateAbbreviation())) {
            return onlyTaxes;
        } else {
            return null;
        }
    }

    @Override
    public List<Taxes> getAllTaxes() {
        return taxesList;
    }
    
}
