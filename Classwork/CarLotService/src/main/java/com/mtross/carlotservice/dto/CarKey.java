/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.carlotservice.dto;

import java.util.Objects;

/**
 *
 * @author mike
 */
public class CarKey {
    private String VIN;
    private boolean allowsRemote;

    public CarKey() {
    }

    public CarKey(String VIN, boolean allowsRemote) {
        this.VIN = VIN;
        this.allowsRemote = allowsRemote;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public boolean isAllowsRemote() {
        return allowsRemote;
    }

    public void setAllowsRemote(boolean allowsRemote) {
        this.allowsRemote = allowsRemote;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.VIN);
        hash = 53 * hash + (this.allowsRemote ? 1 : 0);
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
        final CarKey other = (CarKey) obj;
        if (this.allowsRemote != other.allowsRemote) {
            return false;
        }
        if (!Objects.equals(this.VIN, other.VIN)) {
            return false;
        }
        return true;
    }
    
}
