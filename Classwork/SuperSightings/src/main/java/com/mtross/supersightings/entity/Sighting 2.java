/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.supersightings.entity;

import java.time.LocalDateTime;
import static java.time.temporal.ChronoUnit.SECONDS;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 *
 * @author mike
 */
public class Sighting {

    private int sightingId;

    @NotNull(message = "Location must be given.")
    private int locationId;

    @NotNull(message = "Date must be given.")
    @Past(message = "Date must be from the past.")
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private LocalDateTime date;

    // Constructors
    public Sighting() {
    }

    public Sighting(int sightingId, int locationId, LocalDateTime date) {
        this.sightingId = sightingId;
        this.locationId = locationId;
        this.date = date;
    }

    // Getters & Setters
    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date.truncatedTo(SECONDS);
    }

    // toString
    @Override
    public String toString() {
        return "Sighting{" + "sightingId=" + sightingId
                + ", locationId=" + locationId
                + ", date=" + date + '}';
    }

    // equals & hashCode
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.sightingId;
        hash = 71 * hash + this.locationId;
        hash = 71 * hash + Objects.hashCode(this.date);
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
        final Sighting other = (Sighting) obj;
        if (this.sightingId != other.sightingId) {
            return false;
        }
        if (this.locationId != other.locationId) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

}
