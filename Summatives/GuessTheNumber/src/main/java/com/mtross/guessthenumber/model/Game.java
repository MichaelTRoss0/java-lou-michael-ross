/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.guessthenumber.model;

import java.util.Objects;

/**
 *
 * @author mike
 */
public class Game {

    private int gameId;
    private String answer;
    private String gameStatus;

    // Constructors
    public Game() {
    }

    public Game(int gameId, String answer, String gameStatus) {
        this.gameId = gameId;
        this.answer = answer;
        this.gameStatus = gameStatus;
    }

    // toString
    @Override
    public String toString() {
        return "Game{" + "gameId=" + gameId + ", answer=" + answer
                + ", gameStatus=" + gameStatus + '}';
    }

    // Getters & Setters
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    // Equals & hashCode
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.gameId;
        hash = 53 * hash + Objects.hashCode(this.answer);
        hash = 53 * hash + Objects.hashCode(this.gameStatus);
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
        if (!Objects.equals(this.answer, other.answer)) {
            return false;
        }
        if (!Objects.equals(this.gameStatus, other.gameStatus)) {
            return false;
        }
        return true;
    }

}
