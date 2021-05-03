/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.contactjdbcexercise.dto;

import java.util.Objects;

/**
 *
 * @author mike
 */
public class Agent {
    
    private int agentId;
    private String name;
    private String password;

    // Constructors
    public Agent() {
    }

    public Agent(int agentId, String name, String password) {
        this.agentId = agentId;
        this.name = name;
        this.password = password;
    }

    // toString
    @Override
    public String toString() {
        return "Agent{" + "agentId=" + agentId
             + ", name=" + name + ", password=" + password + '}';
    }

    // Getters & Setters
    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Equals & hashCode
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.agentId;
        hash = 43 * hash + Objects.hashCode(this.name);
        hash = 43 * hash + Objects.hashCode(this.password);
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
        final Agent other = (Agent) obj;
        if (this.agentId != other.agentId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }
    
}
