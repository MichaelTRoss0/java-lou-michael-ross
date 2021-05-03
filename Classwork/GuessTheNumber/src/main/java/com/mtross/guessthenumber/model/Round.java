/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.guessthenumber.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author mike
 */
public class Round {

    private int roundId;
    private int gameId;
    private String guess;
    private LocalDateTime timeOfGuess;
    private int exactMatches;
    private int partialMatches;

    // Constructors
    public Round() {
    }

    public Round(int roundId, int gameId, String guess, LocalDateTime timeOfGuess,
            int exactMatches, int partialMatches) {
        this.roundId = roundId;
        this.gameId = gameId;
        this.guess = guess;
        this.timeOfGuess = timeOfGuess;
        this.exactMatches = exactMatches;
        this.partialMatches = partialMatches;
    }

    // toString
    @Override
    public String toString() {
        return "Round{" + "roundId=" + roundId + ", gameId=" + gameId
                + ", guess=" + guess + ", timeOfGuess=" + timeOfGuess
                + ", exactMatches=" + exactMatches
                + ", partialMatches=" + partialMatches + '}';
    }

    // Getters & Setters
    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public LocalDateTime getTimeOfGuess() {
        return timeOfGuess;
    }

    public void setTimeOfGuess(LocalDateTime timeOfGuess) {
        this.timeOfGuess = timeOfGuess;
    }

    public int getExactMatches() {
        return exactMatches;
    }

    public void setExactMatches(int exactMatches) {
        this.exactMatches = exactMatches;
    }

    public int getPartialMatches() {
        return partialMatches;
    }

    public void setPartialMatches(int partialMatches) {
        this.partialMatches = partialMatches;
    }

    // Equals & hashCode
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + this.roundId;
        hash = 73 * hash + this.gameId;
        hash = 73 * hash + Objects.hashCode(this.guess);
        hash = 73 * hash + Objects.hashCode(this.timeOfGuess);
        hash = 73 * hash + this.exactMatches;
        hash = 73 * hash + this.partialMatches;
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
        final Round other = (Round) obj;
        if (this.roundId != other.roundId) {
            return false;
        }
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.exactMatches != other.exactMatches) {
            return false;
        }
        if (this.partialMatches != other.partialMatches) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        if (!Objects.equals(this.timeOfGuess, other.timeOfGuess)) {
            return false;
        }
        return true;
    }

}
