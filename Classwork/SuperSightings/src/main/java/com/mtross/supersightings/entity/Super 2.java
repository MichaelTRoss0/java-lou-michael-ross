/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.supersightings.entity;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author mike
 */
public class Super {

    private int superId;

    @NotBlank(message = "Name must not be empty.")
    @Size(max = 50, message = "Name must be less than 50 characters.")
    private String name;

    @NotBlank(message = "Description must not be empty.")
    @Size(max = 255, message = "Description must be less than 255 characters.")
    private String description;

    @NotNull(message = "Either is a hero, or is not.")
    private boolean isHero;

    @NotNull(message = "Either is a villain, or is not.")
    private boolean isVillain;

    private List<Power> powers;
    private List<Sighting> sightings;

    // Constructors
    public Super() {
    }

    public Super(int superId, String name, String description, boolean isHero, boolean isVillain, List<Power> powers, List<Sighting> sightings) {
        this.superId = superId;
        this.name = name;
        this.description = description;
        this.isHero = isHero;
        this.isVillain = isVillain;
        this.powers = powers;
        this.sightings = sightings;
    }

    // Getters & Setters
    public int getSuperId() {
        return superId;
    }

    public void setSuperId(int superId) {
        this.superId = superId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHero() {
        return isHero;
    }

    public void setIsHero(boolean isHero) {
        this.isHero = isHero;
    }

    public boolean isVillain() {
        return isVillain;
    }

    public void setIsVillain(boolean isVillain) {
        this.isVillain = isVillain;
    }

    public List<Power> getPowers() {
        return powers;
    }

    public void setPowers(List<Power> powers) {
        this.powers = powers;
    }

    public List<Sighting> getSightings() {
        return sightings;
    }

    public void setSightings(List<Sighting> sightings) {
        this.sightings = sightings;
    }

    // toString
    @Override
    public String toString() {
        return "Super{" + "superId=" + superId
                + ", name=" + name
                + ", description=" + description
                + ", isHero=" + isHero
                + ", isVillain=" + isVillain
                + ", powers=" + powers
                + ", sightings=" + sightings + '}';
    }

    // equals & hashCode
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.superId;
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.description);
        hash = 41 * hash + (this.isHero ? 1 : 0);
        hash = 41 * hash + (this.isVillain ? 1 : 0);
        hash = 41 * hash + Objects.hashCode(this.powers);
        hash = 41 * hash + Objects.hashCode(this.sightings);
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
        final Super other = (Super) obj;
        if (this.superId != other.superId) {
            return false;
        }
        if (this.isHero != other.isHero) {
            return false;
        }
        if (this.isVillain != other.isVillain) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.powers, other.powers)) {
            return false;
        }
        if (!Objects.equals(this.sightings, other.sightings)) {
            return false;
        }
        return true;
    }

}
