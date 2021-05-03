/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.guessthenumber.dao;

import com.mtross.guessthenumber.model.Game;
import java.util.List;

/**
 *
 * @author mike
 */
public interface GameDao {

    Game addGame(Game game);

    List<Game> getAllGames();

    Game getGameById(int gameId);

    void updateGame(Game game);

    void deleteGameById(int gameId);
}
