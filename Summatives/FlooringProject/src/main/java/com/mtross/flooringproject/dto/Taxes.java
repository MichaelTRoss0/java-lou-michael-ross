/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.flooringproject.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import static java.math.RoundingMode.HALF_UP;
import java.util.Objects;

/**
 *
 * @author mike
 */
public class Taxes {
    
    private final RoundingMode ROUNDING_MODE = HALF_UP;
    private final int SCALE = 2;
    
    private String stateAbbreviation;
    private String stateName;
    private BigDecimal taxRate;

    // Constructors
    public Taxes() {
    }

    public Taxes(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    public Taxes(String stateAbbreviation, String stateName, BigDecimal taxRate) {
        this.stateAbbreviation = stateAbbreviation;
        this.stateName = stateName;
        this.taxRate = taxRate;
    }

    // toString
    @Override
    public String toString() {
        return "Taxes{" + "stateAbbreviation=" + stateAbbreviation +
                ", stateName=" + stateName + ", taxRate=" + taxRate + '}';
    }

    // Getters & Setters
    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate.setScale(SCALE, ROUNDING_MODE);
    }

    // Equals and HashCode
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.stateAbbreviation);
        hash = 97 * hash + Objects.hashCode(this.stateName);
        hash = 97 * hash + Objects.hashCode(this.taxRate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Taxes other = (Taxes) obj;
        if (!Objects.equals(this.stateAbbreviation, other.stateAbbreviation)) {
            return false;
        }
        if (!Objects.equals(this.stateName, other.stateName)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        return true;
    }
    
}
