/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.contactjdbcexercise.dto;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author mike
 */
public class Log {
    
    private int logId;
    private int contactId;
    private LocalDateTime timestamp;
    private String logMessage;
    private int agentId;

    // Constructors
    public Log() {
    }

    public Log(int logId, int contactId, LocalDateTime timestamp, String logMessage, int agentId) {
        this.logId = logId;
        this.contactId = contactId;
        this.timestamp = timestamp;
        this.logMessage = logMessage;
        this.agentId = agentId;
    }

    // toString
    @Override
    public String toString() {
        return "Log{" + "logId=" + logId + ", contactId=" + contactId
             + ", timestamp=" + timestamp + ", logMessage=" + logMessage
             + ", agentId=" + agentId + '}';
    }

    // Getters & Setters
    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    // Equals & hashCode
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.logId;
        hash = 31 * hash + this.contactId;
        hash = 31 * hash + Objects.hashCode(this.timestamp);
        hash = 31 * hash + Objects.hashCode(this.logMessage);
        hash = 31 * hash + this.agentId;
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
        final Log other = (Log) obj;
        if (this.logId != other.logId) {
            return false;
        }
        if (this.contactId != other.contactId) {
            return false;
        }
        if (this.agentId != other.agentId) {
            return false;
        }
        if (!Objects.equals(this.logMessage, other.logMessage)) {
            return false;
        }
        if (!Objects.equals(this.timestamp, other.timestamp)) {
            return false;
        }
        return true;
    }
}
