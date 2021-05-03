/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.guessthenumber.dao;

import com.mtross.guessthenumber.model.Round;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
public class RoundDaoDB implements RoundDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public Round addRound(Round round) {
        final String INSERT_ROUND = "INSERT INTO Round(gameId, guess, timeOfGuess, "
                + "exactMatches, partialMatches) "
                + "VALUES (?,?,DEFAULT,?,?)";
        jdbc.update(INSERT_ROUND,
                round.getGameId(),
                round.getGuess(),
                round.getExactMatches(),
                round.getPartialMatches());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setRoundId(newId);
        LocalDateTime timestamp = getRoundById(newId).getTimeOfGuess();
        round.setTimeOfGuess(timestamp);
        return round;
    }

    @Override
    public List<Round> getAllRounds() {
        final String SELECT_ALL_ROUNDS = "SELECT * FROM Round";
        return jdbc.query(SELECT_ALL_ROUNDS, new RoundMapper());
    }

    @Override
    public List<Round> getRoundsForGame(int gameId) {
        final String SELECT_ROUNDS_BY_GAMEID
                = "SELECT * "
                + "FROM Round "
                + "WHERE gameId = ? "
                + "GROUP BY roundId, timeOfGuess "
                + "ORDER BY timeOfGuess DESC";
        return jdbc.query(SELECT_ROUNDS_BY_GAMEID,
                new RoundMapper(), gameId);
    }

    @Override
    public Round getRoundById(int roundId) {
        try {
            final String SELECT_ROUND_BY_ID = "SELECT * FROM Round WHERE roundId = ?";
            return jdbc.queryForObject(SELECT_ROUND_BY_ID, new RoundMapper(), roundId);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public void updateRound(Round round) {
        final String UPDATE_GAME = "UPDATE Round SET gameId = ?, guess = ?, "
                + "timeOfGuess = NOW(), exactMatches = ?, partialMatches = ? "
                + "WHERE roundId = ?";
        jdbc.update(UPDATE_GAME,
                round.getGameId(),
                round.getGuess(),
                round.getExactMatches(),
                round.getPartialMatches(),
                round.getRoundId());
    }

    @Override
    @Transactional
    public void deleteRoundById(int roundId) {
        final String DELETE_ROUND = "DELETE FROM Round WHERE roundId = ?";
        jdbc.update(DELETE_ROUND, roundId);
    }

    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundId(rs.getInt("roundId"));
            round.setGameId(rs.getInt("gameId"));
            round.setGuess(rs.getString("guess"));
            round.setTimeOfGuess(rs.getTimestamp("timeOfGuess").toLocalDateTime());
            round.setExactMatches(rs.getInt("exactMatches"));
            round.setPartialMatches(rs.getInt("partialMatches"));
            return round;
        }
    }

}
