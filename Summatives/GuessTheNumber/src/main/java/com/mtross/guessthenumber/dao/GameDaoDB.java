/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.guessthenumber.dao;

import com.mtross.guessthenumber.model.Game;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mike
 */
@Repository
public class GameDaoDB implements GameDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    @Transactional
    public Game addGame(Game game) {
        final String INSERT_GAME = "INSERT INTO Game(answer, gameStatus) "
                + "VALUES(?,DEFAULT)";
        jdbc.update(INSERT_GAME,
                game.getAnswer());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setGameId(newId);
        game.setGameStatus("Ongoing");
        return game;
    }
    
    @Override
    public List<Game> getAllGames() {
        final String SELECT_ALL_GAMES = "SELECT * FROM Game";
        return jdbc.query(SELECT_ALL_GAMES, new GameMapper());
    }
    
    @Override
    public Game getGameById(int gameId) {
        try {
            final String SELECT_GAME_BY_ID = "SELECT * FROM Game WHERE gameId = ?";
            return jdbc.queryForObject(SELECT_GAME_BY_ID, new GameMapper(), gameId);
        } catch (DataAccessException ex) {
            return null;
        }
    }
    
    @Override
    public void updateGame(Game game) {
        final String UPDATE_GAME = "UPDATE Game SET answer = ?, gameStatus = ? "
                + "WHERE gameId = ?";
        jdbc.update(UPDATE_GAME,
                game.getAnswer(),
                game.getGameStatus(),
                game.getGameId());
    }
    
    @Override
    @Transactional
    public void deleteGameById(int gameId) {
        final String DELETE_ROUND = "DELETE FROM Round WHERE gameId = ?";
        jdbc.update(DELETE_ROUND, gameId);
        
        final String DELETE_GAME = "DELETE FROM Game WHERE gameId = ?";
        jdbc.update(DELETE_GAME, gameId);
    }
    
    private static final class GameMapper implements RowMapper<Game> {
        
        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameId(rs.getInt("gameId"));
            game.setAnswer(rs.getString("answer"));
            game.setGameStatus(rs.getString("gameStatus"));
            return game;
        }
    }
    
}
