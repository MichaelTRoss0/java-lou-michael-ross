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
public class Organization {

    private int organizationId;

    @NotBlank(message = "Name must not be empty.")
    @Size(max = 50, message = "Name must be less than 50 characters.")
    private String name;

    @NotBlank(message = "Description must not be empty.")
    @Size(max = 255, message = "Description must be less than 255 characters.")
    private String description;

    @Size(max = 50, message = "Address must be less than 50 characters.")
    private String address;

    @Size(max = 14, message = "Phone number must be less than 14 characters.")
    private String phone;

    @Size(max = 32, message = "Email must be less than 32 characters.")
    private String email;

    @NotNull(message = "Either is heroic, or is not.")
    private boolean isHeroic;

    @NotNull(message = "Either is villainous, or is not.")
    private boolean isVillainous;

    private List<Super> supers;

    // Constructors
    public Organization() {
    }

    public Organization(int organizationId, String name, String description, String address, String phone, String email, boolean isHeroic, boolean isVillainous, List<Super> supers) {
        this.organizationId = organizationId;
        this.name = name;
        this.description = description;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.isHeroic = isHeroic;
        this.isVillainous = isVillainous;
        this.supers = supers;
    }

    // Getters & Setters
    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isHeroic() {
        return isHeroic;
    }

    public void setIsHeroic(boolean isHeroic) {
        this.isHeroic = isHeroic;
    }

    public boolean isVillainous() {
        return isVillainous;
    }

    public void setIsVillainous(boolean isVillainous) {
        this.isVillainous = isVillainous;
    }

    public List<Super> getSupers() {
        return supers;
    }

    public void setSupers(List<Super> supers) {
        this.supers = supers;
    }

    // toString
    @Override
    public String toString() {
        return "Organization{" + "organizationId=" + organizationId
                + ", name=" + name
                + ", description=" + description
                + ", address=" + address
                + ", phone=" + phone
                + ", email=" + email
                + ", isHeroic=" + isHeroic
                + ", isVillainous=" + isVillainous
                + ", supers=" + supers + '}';
    }

    // equals & hashCode
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.organizationId;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.description);
        hash = 37 * hash + Objects.hashCode(this.address);
        hash = 37 * hash + Objects.hashCode(this.phone);
        hash = 37 * hash + Objects.hashCode(this.email);
        hash = 37 * hash + (this.isHeroic ? 1 : 0);
        hash = 37 * hash + (this.isVillainous ? 1 : 0);
        hash = 37 * hash + Objects.hashCode(this.supers);
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
        final Organization other = (Organization) obj;
        if (this.organizationId != other.organizationId) {
            return false;
        }
        if (this.isHeroic != other.isHeroic) {
            return false;
        }
        if (this.isVillainous != other.isVillainous) {
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
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.supers, other.supers)) {
            return false;
        }
        return true;
    }

}
