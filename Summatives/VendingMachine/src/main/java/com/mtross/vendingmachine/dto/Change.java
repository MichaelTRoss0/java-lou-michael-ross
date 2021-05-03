/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author mike
 */
public class Change {
    
    public static enum Coin{
        PENNY, NICKEL, DIME, QUARTER
    }
    
    private int numPennies;
    private int numNickels;
    private int numDimes;
    private int numQuarters;

    public Change() {
        this.numPennies = 0;
        this.numNickels = 0;
        this.numDimes = 0;
        this.numQuarters = 0;
    }

    public int getNumPennies() {
        return numPennies;
    }

    public void setNumPennies(int numPennies) {
        this.numPennies = numPennies;
    }

    public int getNumNickels() {
        return numNickels;
    }

    public void setNumNickels(int numNickels) {
        this.numNickels = numNickels;
    }

    public int getNumDimes() {
        return numDimes;
    }

    public void setNumDimes(int numDimes) {
        this.numDimes = numDimes;
    }

    public int getNumQuarters() {
        return numQuarters;
    }

    public void setNumQuarters(int numQuarters) {
        this.numQuarters = numQuarters;
    }
    
    public ChangePurse calculateChange(int changeInPennies) {
        ChangePurse result = new ChangePurse();
        
        this.setNumPennies(0);
        this.setNumNickels(0);
        this.setNumDimes(0);
        this.setNumQuarters(0);
        
        while (changeInPennies >= 25) {
            addQuarter();
            changeInPennies -= 25;
        }
        while (changeInPennies >= 10) {
            addDime();
            changeInPennies -= 10;
        }
        while (changeInPennies >= 5) {
            addNickel();
            changeInPennies -= 5;
        }
        while (changeInPennies >= 1) {
            addPenny();
            changeInPennies -= 1;
        }
        
        result.setNumPennies(getNumPennies());
        result.setNumNickels(getNumNickels());
        result.setNumDimes(getNumDimes());
        result.setNumQuarters(getNumQuarters());
        
        return result;
    }

    public static BigDecimal getValue(Coin coin) {
        BigDecimal value;
        
        switch (coin) {
            case PENNY:
                value = new BigDecimal("0.01");
                break;
            case NICKEL:
                value = new BigDecimal("0.05");
                break;
            case DIME:
                value = new BigDecimal("0.10");
                break;
            case QUARTER:
                value = new BigDecimal("0.25");
                break;
            default:
                value = new BigDecimal("0.00");
                break;
        }
        
        return value;
    }
    
    private void addPenny() {
        this.numPennies++;
    }
    
    private void addNickel() {
        this.numNickels++;
    }
    
    private void addDime() {
        this.numDimes++;
    }
    
    private void addQuarter() {
        this.numQuarters++;
    }
    
}
