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
public class Location {

    private int locationId;

    @NotBlank(message = "Name must not be empty.")
    @Size(max = 50, message = "Name must be less than 50 characters.")
    private String name;

    @NotBlank(message = "Description must not be empty.")
    @Size(max = 255, message = "Description must be less than 255 characters.")
    private String description;

    @NotBlank(message = "Address must not be empty.")
    @Size(max = 50, message = "Address must be less than 50 characters.")
    private String address;

    @NotBlank(message = "Latitude must not be empty.")
    @Size(min = 9, max = 9, message = "Latitude must be exactly 9 characters.")
    private String latitude;

    @NotBlank(message = "Longitude must not be empty.")
    @Size(min = 9, max = 9, message = "Longitude must be exactly 9 characters.")
    private String longitude;

    // Constructors
    public Location() {
    }

    public Location(int locationId, String name, String description, String address, String latitude, String longitude) {
        this.locationId = locationId;
        this.name = name;
        this.description = description;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters & Setters
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    // toString
    @Override
    public String toString() {
        return "Location{" + "locationId=" + locationId
                + ", name=" + name
                + ", description=" + description
                + ", address=" + address
                + ", latitude=" + latitude
                + ", longitude=" + longitude + '}';
    }

    // equals & hashCode
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.locationId;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.description);
        hash = 79 * hash + Objects.hashCode(this.address);
        hash = 79 * hash + Objects.hashCode(this.latitude);
        hash = 79 * hash + Objects.hashCode(this.longitude);
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
        final Location other = (Location) obj;
        if (this.locationId != other.locationId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }
        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }
        return true;
    }

}
