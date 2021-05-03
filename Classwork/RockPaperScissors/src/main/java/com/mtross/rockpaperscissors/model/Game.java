/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.rockpaperscissors.model;

import java.util.Objects;

/**
 *
 * @author mike
 */
public class Game {

    private int gameId;
    private Outcome outcome;

    // Constructors
    public Game() {
    }

    public Game(int gameId, Outcome outcome) {
        this.gameId = gameId;
        this.outcome = outcome;
    }

    // toString
    @Override
    public String toString() {
        return "Game{" + "gameId=" + gameId + ", outcome=" + outcome + '}';
    }

    // Getters & Setters
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    // Equals & hashCode
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.gameId;
        hash = 37 * hash + Objects.hashCode(this.outcome);
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
        final Game other = (Game) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.outcome != other.outcome) {
            return false;
        }
        return true;
    }

}
