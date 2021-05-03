/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.zooservice.dtos;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author mike
 */
public class Critter {
    // id, name, species, diet, size
    private int id;
    private String name;
    private String species;
    private Diet diet;
    private BigDecimal size;

    // Constructors
    public Critter() {
    }

    public Critter(int id, String name, String species, Diet diet, BigDecimal size) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.diet = diet;
        this.size = size;
    }

    // ANY OTHER METHOD GOES HERE
    
    // toString
    @Override    
    public String toString() {
        return "Critter{" + "id=" + id + ", name=" + name + ", species=" + species + ", diet=" + diet + ", size=" + size + '}';
    }

    // equals and hashCode
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.species);
        hash = 29 * hash + Objects.hashCode(this.diet);
        hash = 29 * hash + Objects.hashCode(this.size);
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
        final Critter other = (Critter) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.species, other.species)) {
            return false;
        }
        if (this.diet != other.diet) {
            return false;
        }
        if (!Objects.equals(this.size, other.size)) {
            return false;
        }
        return true;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Diet getDiet() {
        return diet;
    }

    public void setDiet(Diet diet) {
        this.diet = diet;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }
    
}
