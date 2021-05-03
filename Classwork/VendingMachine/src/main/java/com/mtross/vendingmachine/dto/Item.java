/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author mike
 */
public class Item {
    private String slotId;
    private String name;
    private BigDecimal cost;
    private int quantity;

    public Item() {
    }

    public Item(String slotId, String name, BigDecimal cost, int quantity) {
        this.slotId = slotId;
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.slotId);
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.cost);
        hash = 47 * hash + this.quantity;
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
        final Item other = (Item) obj;
        if (this.quantity != other.quantity) {
            return false;
        }
        if (!Objects.equals(this.slotId, other.slotId)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.cost, other.cost)) {
            return false;
        }
        return true;
    }

    public int checkPurchase(BigDecimal money) {
        if (this != null) {
            if (this.getCost().compareTo(money) < 1) {
                return 1;
            } else {
                return 2;
            }
        } else {
            return 3;
        }
    }
    
}
