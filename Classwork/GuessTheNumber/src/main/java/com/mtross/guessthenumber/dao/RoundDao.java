/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.guessthenumber.dao;

import com.mtross.guessthenumber.model.Round;
import java.util.List;

/**
 *
 * @author mike
 */
public interface RoundDao {

    Round addRound(Round round);

    List<Round> getAllRounds();

    List<Round> getRoundsForGame(int gameId);

    Round getRoundById(int roundId);

    void updateRound(Round round);

    void deleteRoundById(int roundId);
}
