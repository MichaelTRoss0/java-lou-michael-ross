/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.rockpaperscissors.dao;

import com.mtross.rockpaperscissors.daoexceptions.RpsPersistenceException;
import com.mtross.rockpaperscissors.model.Round;
import java.util.List;

/**
 *
 * @author mike
 */
public interface RoundDao {

    public void loadFromFile()
            throws RpsPersistenceException;

    public void saveToFile()
            throws RpsPersistenceException;

    public Round addARound(Round newRound, int gameId);

    public Round getARound(int roundId);

    public List<Round> getAllRounds();

    public List<Round> getGameRounds(int gameId);

    public Round updateARound(Round round, int roundId);

    public Round removeARound(int roundId);

}
