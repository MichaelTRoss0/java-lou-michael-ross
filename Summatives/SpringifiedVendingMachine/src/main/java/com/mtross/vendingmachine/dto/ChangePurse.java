/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.vendingmachine.dto;

/**
 *
 * @author mike
 */
public class ChangePurse {
    private int numPennies;
    private int numNickels;
    private int numDimes;
    private int numQuarters;

    public ChangePurse() {
        this.numPennies = 0;
        this.numNickels = 0;
        this.numDimes = 0;
        this.numQuarters = 0;
    }

    public ChangePurse(int numPennies, int numNickels, int numDimes, int numQuarters) {
        this.numPennies = numPennies;
        this.numNickels = numNickels;
        this.numDimes = numDimes;
        this.numQuarters = numQuarters;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.numPennies;
        hash = 29 * hash + this.numNickels;
        hash = 29 * hash + this.numDimes;
        hash = 29 * hash + this.numQuarters;
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
        final ChangePurse other = (ChangePurse) obj;
        if (this.numPennies != other.numPennies) {
            return false;
        }
        if (this.numNickels != other.numNickels) {
            return false;
        }
        if (this.numDimes != other.numDimes) {
            return false;
        }
        if (this.numQuarters != other.numQuarters) {
            return false;
        }
        return true;
    }
    
}
