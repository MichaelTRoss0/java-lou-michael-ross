/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.flooringproject.dao;

import com.mtross.flooringproject.daoexceptions.FlooringPersistenceException;
import com.mtross.flooringproject.dto.Taxes;
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
public class TaxesDaoFileImpl implements TaxesDao {

    public static final String TAXES_FILE = "Data/Taxes.txt";
    public static final String DELIMITER = ",";
    
    private Map<String, Taxes> taxes = new HashMap<>();

    @Override
    public void loadFromFile() throws FlooringPersistenceException {
        readTaxes();
    }

    @Override
    public Taxes getATax(String stateAbbr) {
        return taxes.get(stateAbbr);
    }

    @Override
    public List<Taxes> getAllTaxes() {
        return new ArrayList<>(taxes.values());
    }
    
    private void readTaxes() throws FlooringPersistenceException {
        Scanner scanner;
        
        try {
            scanner = new Scanner(
                      new BufferedReader(
                      new FileReader(TAXES_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException(
                    "\"-_- Could not load order data into memory.", e);
        }
        
        // Advance past the legend first
        scanner.nextLine();
        
        String currentLine;
        Taxes currentTaxes;
        
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTaxes = unmarshallTaxes(currentLine);
            String stateAbbr = currentTaxes.getStateAbbreviation();
            taxes.put(stateAbbr, currentTaxes);
        }
        
        scanner.close();
    }
    
    private Taxes unmarshallTaxes(String taxesAsText) {
        String[] taxesTokens = taxesAsText.split(DELIMITER);
        
        Taxes currentTaxes = new Taxes(taxesTokens[0]);
        currentTaxes.setStateName(taxesTokens[1]);
        currentTaxes.setTaxRate(new BigDecimal(taxesTokens[2]));
        
        return currentTaxes;
    }
    
}
