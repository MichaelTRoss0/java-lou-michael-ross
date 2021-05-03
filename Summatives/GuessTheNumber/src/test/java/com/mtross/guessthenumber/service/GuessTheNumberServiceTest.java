/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.guessthenumber.service;

import com.mtross.guessthenumber.TestApplicationConfiguration;
import com.mtross.guessthenumber.dao.GameDao;
import com.mtross.guessthenumber.dao.RoundDao;
import com.mtross.guessthenumber.model.Game;
import com.mtross.guessthenumber.model.Round;
import java.util.ArrayList;
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
public class GuessTheNumberServiceTest {

    @Autowired
    GuessTheNumberService service;

    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;

    public GuessTheNumberServiceTest() {
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
     * Test of makeNewGame method, of class GuessTheNumberService.
     */
    @Test
    public void testMakeNewGame() {
        Game game;
        String[] digits;

        for (int i = 1; i < 101; i++) {
            game = service.makeNewGame();
            digits = game.getAnswer().split("");

            Assertions.assertEquals(4, digits.length,
                    "The answer should be exactly four digits long.");
            Assertions.assertFalse(digits[0].equals(digits[1]),
                    "The first and second digits should not be equal "
                    + "(failed on run #" + i + ").");
            Assertions.assertFalse(digits[0].equals(digits[2]),
                    "The first and third digits should not be equal "
                    + "(failed on run #" + i + ").");
            Assertions.assertFalse(digits[0].equals(digits[3]),
                    "The first and fourth digits should not be equal "
                    + "(failed on run #" + i + ").");
            Assertions.assertFalse(digits[1].equals(digits[2]),
                    "The second and third digits should not be equal "
                    + "(failed on run #" + i + ").");
            Assertions.assertFalse(digits[1].equals(digits[3]),
                    "The second and fourth digits should not be equal "
                    + "(failed on run #" + i + ").");
            Assertions.assertFalse(digits[2].equals(digits[3]),
                    "The third and fourth digits should not be equal "
                    + "(failed on run #" + i + ").");
        }

    }

    /**
     * Test of findMatches method, of class GuessTheNumberService.
     */
    @Test
    public void testFindMatches() {
        Game game1 = new Game();
        game1.setAnswer("1234");
        game1 = service.insertGame(game1);
        int game1Id = game1.getGameId();

        Round round11 = service.findMatches(game1Id, "0000");
        Round round12 = service.findMatches(game1Id, "1111");
        Round round13 = service.findMatches(game1Id, "1200");
        round11 = service.insertRound(round11);
        round12 = service.insertRound(round12);
        round13 = service.insertRound(round13);

        game1 = service.getGame(game1Id);

        Assertions.assertTrue(game1.getGameStatus().equals("Ongoing"),
                "The first game's status should be 'Ongoing'.");

        Assertions.assertEquals(0, round11.getExactMatches(),
                "There should be 0 exact matches in the first game's first round.");
        Assertions.assertEquals(0, round11.getPartialMatches(),
                "There should be 0 partial matches in the first game's first round.");

        Assertions.assertEquals(1, round12.getExactMatches(),
                "There should be 1 exact match in the first game's second round.");
        Assertions.assertEquals(3, round12.getPartialMatches(),
                "There should be 3 partial matches in the first game's second round.");

        Assertions.assertEquals(2, round13.getExactMatches(),
                "There should be 2 exact matches in the first game's third round.");
        Assertions.assertEquals(0, round13.getPartialMatches(),
                "There should be 0 partial matches in the first game's third round.");

        Game game2 = new Game();
        game2.setAnswer("5678");
        game2 = service.insertGame(game2);
        int game2Id = game2.getGameId();

        Round round21 = service.findMatches(game2Id, "1234");
        Round round22 = service.findMatches(game2Id, "0687");
        Round round23 = service.findMatches(game2Id, "8765");
        round21 = service.insertRound(round21);
        round22 = service.insertRound(round22);
        round23 = service.insertRound(round23);

        game2 = service.getGame(game2Id);

        Assertions.assertTrue(game2.getGameStatus().equals("Ongoing"),
                "The second game's status should be 'Ongoing'.");

        Assertions.assertEquals(0, round21.getExactMatches(),
                "There should be 0 exact matches in the second game's first round.");
        Assertions.assertEquals(0, round21.getPartialMatches(),
                "There should be 0 partial matches in the second game's first round.");

        Assertions.assertEquals(1, round22.getExactMatches(),
                "There should be 1 exact match in the second game's second round.");
        Assertions.assertEquals(2, round22.getPartialMatches(),
                "There should be 2 partial matches in the second game's second round.");

        Assertions.assertEquals(0, round23.getExactMatches(),
                "There should be 0 exact matches in the second game's third round.");
        Assertions.assertEquals(4, round23.getPartialMatches(),
                "There should be 4 partial matches in the second game's third round.");

        Game game3 = new Game();
        game3.setAnswer("9012");
        game3 = service.insertGame(game3);
        int game3Id = game3.getGameId();

        Round round31 = service.findMatches(game3Id, "0912");
        Round round32 = service.findMatches(game3Id, "2019");
        Round round33 = service.findMatches(game3Id, "9012");
        round31 = service.insertRound(round31);
        round32 = service.insertRound(round32);
        round33 = service.insertRound(round33);

        game3 = service.getGame(game3Id);

        Assertions.assertTrue(game3.getGameStatus().equals("Finished"),
                "The third game's status should be 'Finished'.");

        Assertions.assertEquals(2, round31.getExactMatches(),
                "There should be 2 exact matches in the third game's first round.");
        Assertions.assertEquals(2, round31.getPartialMatches(),
                "There should be 2 partial matches in the third game's first round.");

        Assertions.assertEquals(2, round32.getExactMatches(),
                "There should be 2 exact matches in the third game's second round.");
        Assertions.assertEquals(2, round32.getPartialMatches(),
                "There should be 2 partial matches in the third game's second round.");

        Assertions.assertEquals(4, round33.getExactMatches(),
                "There should be 2 exact matches in the third game's third round.");
        Assertions.assertEquals(0, round33.getPartialMatches(),
                "There should be 0 partial matches in the third game's third round.");
    }

    /**
     * Test of hideGameAnswer method, of class GuessTheNumberService.
     */
    @Test
    public void testHideGameAnswer() {
        Game game1 = new Game();
        game1.setAnswer("1234");
        game1.setGameStatus("Ongoing");

        game1 = service.hideGameAnswer(game1);

        Assertions.assertTrue(game1.getAnswer().equals("????"),
                "The ongoing game's answer should be hidden.");

        Game game2 = new Game();
        game2.setAnswer("1234");
        game2.setGameStatus("Finished");

        game2 = service.hideGameAnswer(game2);

        Assertions.assertTrue(game2.getAnswer().equals("1234"),
                "The finished game's answer should not be hidden.");
    }

    /**
     * Test of hideAllGameAnswers method, of class GuessTheNumberService.
     */
    @Test
    public void testHideAllGameAnswers() {
        Game game1 = new Game();
        game1.setGameId(1);
        game1.setAnswer("1234");
        game1.setGameStatus("Ongoing");

        Game game2 = new Game();
        game2.setGameId(2);
        game2.setAnswer("5678");
        game2.setGameStatus("Finished");

        List<Game> games = new ArrayList<>();
        games.add(game1);
        games.add(game2);

        games = service.hideAllGameAnswers(games);

        Assertions.assertEquals(2, games.size(),
                "The list of games should have two games in it.");
        Assertions.assertTrue(games.contains(new Game(1, "????", "Ongoing")),
                "The first game should have had its answer hidden.");
        Assertions.assertTrue(games.contains(game2),
                "The second game should not have been changed.");
    }

}
