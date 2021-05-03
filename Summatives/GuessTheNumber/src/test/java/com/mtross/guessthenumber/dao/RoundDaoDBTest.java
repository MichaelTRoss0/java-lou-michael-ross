/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.guessthenumber.dao;

import com.mtross.guessthenumber.TestApplicationConfiguration;
import com.mtross.guessthenumber.model.Game;
import com.mtross.guessthenumber.model.Round;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author mike
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class RoundDaoDBTest {

    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;

    public RoundDaoDBTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<Round> rounds = roundDao.getAllRounds();
        for (Round round : rounds) {
            roundDao.deleteRoundById(round.getRoundId());
        }

        List<Game> games = gameDao.getAllGames();
        for (Game game : games) {
            gameDao.deleteGameById(game.getGameId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addRound method, of class RoundDaoDB.
     */
    @Test
    public void testAddGetRound() {
        Game game = new Game();
        game.setAnswer("1234");
        game = gameDao.addGame(game);

        Round round = new Round();
        round.setGameId(game.getGameId());
        round.setGuess("2468");
        round.setTimeOfGuess(LocalDateTime.now());
        round.setExactMatches(0);
        round.setPartialMatches(2);
        round = roundDao.addRound(round);

        Round fromDao = roundDao.getRoundById(round.getRoundId());

        Assertions.assertEquals(round, fromDao,
                "The round object taken out of the database should be the same as the one put in.");
        Assertions.assertNotNull(round.getTimeOfGuess(),
                "The timestamp should not be null.");
    }

    /**
     * Test of getAllRounds method, of class RoundDaoDB.
     */
    @Test
    public void testGetAllRounds() {
        Game game = new Game();
        game.setAnswer("1234");
        game = gameDao.addGame(game);

        Round round1 = new Round();
        round1.setGameId(game.getGameId());
        round1.setGuess("2468");
        round1.setTimeOfGuess(LocalDateTime.now());
        round1.setExactMatches(0);
        round1.setPartialMatches(2);
        roundDao.addRound(round1);

        Round round2 = new Round();
        round2.setGameId(game.getGameId());
        round2.setGuess("3579");
        round2.setTimeOfGuess(LocalDateTime.now());
        round2.setExactMatches(0);
        round2.setPartialMatches(1);
        roundDao.addRound(round2);

        List<Round> rounds = roundDao.getAllRounds();

        Assertions.assertEquals(2, rounds.size(),
                "The list of rounds should have exactly two rounds in it.");
        Assertions.assertTrue(rounds.contains(round1),
                "The first round should be in the list.");
        Assertions.assertTrue(rounds.contains(round2),
                "The second round should be in the list.");
    }

    /**
     * Test of getRoundsForGame method, of class RoundDaoDB.
     */
    @Test
    public void testGetRoundsForGame() {
        Game game1 = new Game();
        game1.setAnswer("1234");
        game1 = gameDao.addGame(game1);

        Round round1 = new Round();
        round1.setGameId(game1.getGameId());
        round1.setGuess("2468");
        round1.setTimeOfGuess(LocalDateTime.now());
        round1.setExactMatches(0);
        round1.setPartialMatches(2);
        roundDao.addRound(round1);

        Round round2 = new Round();
        round2.setGameId(game1.getGameId());
        round2.setGuess("3579");
        round2.setTimeOfGuess(LocalDateTime.now());
        round2.setExactMatches(0);
        round2.setPartialMatches(1);
        roundDao.addRound(round2);

        Game game2 = new Game();
        game2.setAnswer("4321");
        game2 = gameDao.addGame(game2);

        Round round3 = new Round();
        round3.setGameId(game2.getGameId());
        round3.setGuess("8642");
        round3.setTimeOfGuess(LocalDateTime.now());
        round3.setExactMatches(0);
        round3.setPartialMatches(2);
        roundDao.addRound(round3);

        Round round4 = new Round();
        round4.setGameId(game2.getGameId());
        round4.setGuess("9753");
        round4.setTimeOfGuess(LocalDateTime.now());
        round4.setExactMatches(0);
        round4.setPartialMatches(1);
        roundDao.addRound(round4);

        List rounds1 = roundDao.getRoundsForGame(game1.getGameId());

        Assertions.assertEquals(2, rounds1.size(),
                "The list of rounds from the first game should have exactly two rounds in it.");
        Assertions.assertTrue(rounds1.contains(round1),
                "The first round from the first game should be in the list.");
        Assertions.assertTrue(rounds1.contains(round2),
                "The second round from the first game should be in the list.");

        List rounds2 = roundDao.getRoundsForGame(game2.getGameId());

        Assertions.assertEquals(2, rounds2.size(),
                "The list of rounds from the second game should have exactly two rounds in it.");
        Assertions.assertTrue(rounds2.contains(round3),
                "The first round from the second game should be in the list.");
        Assertions.assertTrue(rounds2.contains(round4),
                "The second round from the second game should be in the list.");
    }

    /**
     * Test of updateRound method, of class RoundDaoDB.
     */
    @Test
    public void testUpdateRound() {
        Game game = new Game();
        game.setAnswer("1234");
        game = gameDao.addGame(game);

        Round round = new Round();
        round.setGameId(game.getGameId());
        round.setGuess("2468");
        round.setTimeOfGuess(LocalDateTime.now());
        round.setExactMatches(0);
        round.setPartialMatches(2);
        round = roundDao.addRound(round);

        Round fromDao = roundDao.getRoundById(round.getRoundId());

        Assertions.assertEquals(round, fromDao,
                "The round object put in the database should "
                + "be the same as the one taken out.");

        round.setGuess("2413");
        round.setPartialMatches(4);

        roundDao.updateRound(round);

        Assertions.assertNotEquals(round, fromDao,
                "The updated round object should not be the same "
                + "as the one taken out before updating.");

        fromDao = roundDao.getRoundById(round.getRoundId());

        Assertions.assertNotNull(fromDao.getTimeOfGuess(),
                "The timestamp after updating should not be null.");
//        Assertions.assertEquals(-1, round.getTimeOfGuess().compareTo(fromDao.getTimeOfGuess()),
//                "The original timestamp should be at an earlier time "
//                + "than the one taken out after updating.");

        round.setTimeOfGuess(fromDao.getTimeOfGuess());

        Assertions.assertEquals(round, fromDao,
                "The updated round object should be the same "
                + "as the one put in after being updated.");
    }

    /**
     * Test of deleteRoundById method, of class RoundDaoDB.
     */
    @Test
    public void testDeleteRoundById() {
        Game game = new Game();
        game.setAnswer("1234");
        game = gameDao.addGame(game);

        Round round = new Round();
        round.setGameId(game.getGameId());
        round.setGuess("2468");
        round.setTimeOfGuess(LocalDateTime.now());
        round.setExactMatches(0);
        round.setPartialMatches(2);
        round = roundDao.addRound(round);

        roundDao.deleteRoundById(round.getRoundId());

        Game gameFromDao = gameDao.getGameById(game.getGameId());
        Round roundFromDao = roundDao.getRoundById(round.getRoundId());

        Assertions.assertNull(roundFromDao,
                "The deleted round should not be in the database.");
        Assertions.assertNotNull(gameFromDao,
                "The game should still be in the database.");
        Assertions.assertEquals(gameFromDao, game,
                "The game itself should not have changed from deleting one of its rounds.");
    }

}
