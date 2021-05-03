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
public class Round {

    private int roundId;
    private int gameId;
    private int userPlay;
    private int compPlay;
    private Outcome result;

    // Constructors
    public Round() {
    }

    public Round(int roundId, int gameId, int userPlay, int compPlay, Outcome result) {
        this.roundId = roundId;
        this.gameId = gameId;
        this.userPlay = userPlay;
        this.compPlay = compPlay;
        this.result = result;
    }

    // toString
    @Override
    public String toString() {
        return "Round{" + "roundId=" + roundId + ", gameId=" + gameId + ", userPlay=" + userPlay + ", compPlay=" + compPlay + ", result=" + result + '}';
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

    public int getUserPlay() {
        return userPlay;
    }

    public void setUserPlay(int userPlay) {
        this.userPlay = userPlay;
    }

    public int getCompPlay() {
        return compPlay;
    }

    public void setCompPlay(int compPlay) {
        this.compPlay = compPlay;
    }

    public Outcome getResult() {
        return result;
    }

    public void setResult(Outcome result) {
        this.result = result;
    }

    // Equals & hashCode
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + this.roundId;
        hash = 13 * hash + this.gameId;
        hash = 13 * hash + this.userPlay;
        hash = 13 * hash + this.compPlay;
        hash = 13 * hash + Objects.hashCode(this.result);
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
        if (this.userPlay != other.userPlay) {
            return false;
        }
        if (this.compPlay != other.compPlay) {
            return false;
        }
        if (this.result != other.result) {
            return false;
        }
        return true;
    }

}
