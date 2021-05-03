/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.zooservice.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mike
 */
public class Habitat {
    // id, maxOccupancy, currentOccupents
    private int id;
    private int maxOccupancy;
    private List<String> currentOccupants = new ArrayList<>();

    // constructors
    public Habitat() {
    }

    public Habitat(int id, int maxOccupancy, List<String> currentOccupants) {
        this.id = id;
        this.maxOccupancy = maxOccupancy;
        this.currentOccupants = currentOccupants;
    }
    
    // toString
    @Override
    public String toString() {
        return "Habitat{" + "id=" + id + ", maxOccupancy=" + maxOccupancy + ", currentOccupants=" + currentOccupants + '}';
    }
    
    // hashCode & equals
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.id;
        hash = 17 * hash + this.maxOccupancy;
        hash = 17 * hash + Objects.hashCode(this.currentOccupants);
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
        final Habitat other = (Habitat) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.maxOccupancy != other.maxOccupancy) {
            return false;
        }
        if (!Objects.equals(this.currentOccupants, other.currentOccupants)) {
            return false;
        }
        return true;
    }
    
    // getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public List<String> getCurrentOccupants() {
        return currentOccupants;
    }

    public void setCurrentOccupants(List<String> currentOccupants) {
        this.currentOccupants = currentOccupants;
    }
    
}
