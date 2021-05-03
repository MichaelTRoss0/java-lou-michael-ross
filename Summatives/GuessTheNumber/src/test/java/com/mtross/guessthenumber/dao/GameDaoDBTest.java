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
public class GameDaoDBTest {

    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;

    public GameDaoDBTest() {
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
     * Test of addGame method, of class GameDaoDB.
     */
    @Test
    public void testAddGetGame() {
        Game game = new Game();
        game.setAnswer("1234");
        game = gameDao.addGame(game);

        Game fromDao = gameDao.getGameById(game.getGameId());

        Assertions.assertEquals(game, fromDao,
                "The game object taken out of the database should be the same as the one put in.");
        Assertions.assertEquals("Ongoing", game.getGameStatus(),
                "The default value of the gameStatus parameter should be 'Ongoing'.");
    }

    /**
     * Test of getAllGames method, of class GameDaoDB.
     */
    @Test
    public void testGetAllGames() {
        Game game1 = new Game();
        game1.setAnswer("1234");
        game1 = gameDao.addGame(game1);

        Game game2 = new Game();
        game2.setAnswer("5678");
        game2 = gameDao.addGame(game2);

        List<Game> games = gameDao.getAllGames();

        Assertions.assertEquals(2, games.size(),
                "The list of games should have exactly two games in it.");
        Assertions.assertTrue(games.contains(game1),
                "The first game should be in the list.");
        Assertions.assertTrue(games.contains(game2),
                "The second game should be in the list.");
    }

    /**
     * Test of updateGame method, of class GameDaoDB.
     */
    @Test
    public void testUpdateGame() {
        Game game = new Game();
        game.setAnswer("1234");
        game = gameDao.addGame(game);

        Game fromDao = gameDao.getGameById(game.getGameId());

        Assertions.assertEquals(game, fromDao,
                "The game object put in the database should "
                + "be the same as the one taken out.");

        game.setGameStatus("Finished");

        gameDao.updateGame(game);

        Assertions.assertNotEquals(game, fromDao,
                "The updated game object should not be the same "
                + "as the one taken out before updating.");

        fromDao = gameDao.getGameById(game.getGameId());

        Assertions.assertEquals(game, fromDao,
                "The updated game object should be the same "
                + "as the one put in after being updated.");
    }

    /**
     * Test of deleteGameById method, of class GameDaoDB.
     */
    @Test
    public void testDeleteGameById() {
        Game game = new Game();
        game.setAnswer("1234");
        game = gameDao.addGame(game);

        Round round = new Round();
        round.setGameId(game.getGameId());
        round.setGuess("2468");
        round.setTimeOfGuess(LocalDateTime.now());
        round.setExactMatches(0);
        round.setPartialMatches(2);
        roundDao.addRound(round);

        gameDao.deleteGameById(game.getGameId());

        Game gameFromDao = gameDao.getGameById(game.getGameId());
        Round roundFromDao = roundDao.getRoundById(round.getRoundId());

        Assertions.assertNull(gameFromDao,
                "The deleted game should not be in the database.");
        Assertions.assertNull(roundFromDao,
                "The round of the deleted game should not be in the database.");
    }

}
