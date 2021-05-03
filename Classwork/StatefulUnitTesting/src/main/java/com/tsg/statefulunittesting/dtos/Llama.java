/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.statefulunittesting.dtos;

import java.util.Objects;

/**
 *
 * @author austynhill
 */
public class Llama {
    
    private String id;
    private String color;
    private String name;
    private int yearsOld;
    private String favFood;

    public Llama() {}

    public Llama(String id, String color, String name, int yearsOld, String favFood) {
        this.id = id;
        this.color = color;
        this.name = name;
        this.yearsOld = yearsOld;
        this.favFood = favFood;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.color);
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + this.yearsOld;
        hash = 13 * hash + Objects.hashCode(this.favFood);
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
        final Llama other = (Llama) obj;
        if (this.yearsOld != other.yearsOld) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.favFood, other.favFood)) {
            return false;
        }
        return true;
    }
    
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearsOld() {
        return yearsOld;
    }

    public void setYearsOld(int yearsOld) {
        this.yearsOld = yearsOld;
    }

    public String getFavFood() {
        return favFood;
    }

    public void setFavFood(String favFood) {
        this.favFood = favFood;
    }
    
    
    
}
