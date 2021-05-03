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
public class Order {
    
    private final RoundingMode ROUNDING_MODE = HALF_UP;
    private final int SCALE = 2;
    
    private int orderNumber;
    private String customerName;
    private String state;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal area;
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal total;
    
    // Constructors
    public Order() {
    }

    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Order(int orderNumber, String customerName, String state,
            BigDecimal taxRate, String productType, BigDecimal area,
            BigDecimal costPerSquareFoot, BigDecimal laborCostPerSquareFoot,
            BigDecimal materialCost, BigDecimal laborCost, BigDecimal tax,
            BigDecimal total) {
        
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.state = state;
        this.taxRate = taxRate.setScale(SCALE, ROUNDING_MODE);
        this.productType = productType;
        this.area = area.setScale(SCALE, ROUNDING_MODE);
        this.costPerSquareFoot = costPerSquareFoot.setScale(SCALE, ROUNDING_MODE);
        this.laborCostPerSquareFoot = laborCostPerSquareFoot.setScale(SCALE, ROUNDING_MODE);
        this.materialCost = materialCost.setScale(SCALE, ROUNDING_MODE);
        this.laborCost = laborCost.setScale(SCALE, ROUNDING_MODE);
        this.tax = tax.setScale(SCALE, ROUNDING_MODE);
        this.total = total.setScale(SCALE, ROUNDING_MODE);
    }

    // toString
    @Override
    public String toString() {
        return "Order{" + "orderNumber=" + orderNumber + ", customerName="
                + customerName + ", state=" + state + ", taxRate=" + taxRate
                + ", productType=" + productType + ", area=" + area +
                ", costPerSquareFoot=" + costPerSquareFoot +
                ", laborCostPerSquareFoot=" + laborCostPerSquareFoot +
                ", materialCost=" + materialCost + ", laborCost=" + laborCost +
                ", tax=" + tax + ", total=" + total + '}';
    }

    // Getters & Setters
    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate.setScale(SCALE, ROUNDING_MODE);
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area.setScale(SCALE, ROUNDING_MODE);
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot.setScale(SCALE, ROUNDING_MODE);
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot.setScale(SCALE, ROUNDING_MODE);
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost.setScale(SCALE, ROUNDING_MODE);
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost.setScale(SCALE, ROUNDING_MODE);
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax.setScale(SCALE, ROUNDING_MODE);
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total.setScale(SCALE, ROUNDING_MODE);
    }

    // Equals & hashCode
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.orderNumber;
        hash = 83 * hash + Objects.hashCode(this.customerName);
        hash = 83 * hash + Objects.hashCode(this.state);
        hash = 83 * hash + Objects.hashCode(this.taxRate);
        hash = 83 * hash + Objects.hashCode(this.productType);
        hash = 83 * hash + Objects.hashCode(this.area);
        hash = 83 * hash + Objects.hashCode(this.costPerSquareFoot);
        hash = 83 * hash + Objects.hashCode(this.laborCostPerSquareFoot);
        hash = 83 * hash + Objects.hashCode(this.materialCost);
        hash = 83 * hash + Objects.hashCode(this.laborCost);
        hash = 83 * hash + Objects.hashCode(this.tax);
        hash = 83 * hash + Objects.hashCode(this.total);
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
        final Order other = (Order) obj;
        if (this.orderNumber != other.orderNumber) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.costPerSquareFoot, other.costPerSquareFoot)) {
            return false;
        }
        if (!Objects.equals(this.laborCostPerSquareFoot, other.laborCostPerSquareFoot)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.laborCost, other.laborCost)) {
            return false;
        }
        if (!Objects.equals(this.tax, other.tax)) {
            return false;
        }
        if (!Objects.equals(this.total, other.total)) {
            return false;
        }
        return true;
    }
    
}
