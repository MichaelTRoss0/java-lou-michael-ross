/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.supersightings.entity;

import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author mike
 */
public class Power {

    private int powerId;

    @NotBlank(message = "Name must not be empty.")
    @Size(max = 50, message = "Name must be less than 50 characters.")
    private String name;

    @NotBlank(message = "Description must not be empty.")
    @Size(max = 255, message = "Description must be less than 255 characters.")
    private String description;

    // Constructors
    public Power() {
    }

    public Power(int powerId, String name, String description) {
        this.powerId = powerId;
        this.name = name;
        this.description = description;
    }

    // Getters & Setters
    public int getPowerId() {
        return powerId;
    }

    public void setPowerId(int powerId) {
        this.powerId = powerId;
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

    // toString
    @Override
    public String toString() {
        return "Power{" + "powerId=" + powerId
                + ", name=" + name
                + ", description=" + description + '}';
    }

    // equals & hashCode
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.powerId;
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.description);
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
        final Power other = (Power) obj;
        if (this.powerId != other.powerId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

}
