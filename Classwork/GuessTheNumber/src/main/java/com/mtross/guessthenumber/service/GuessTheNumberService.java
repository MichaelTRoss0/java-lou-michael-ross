/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.guessthenumber.service;

import com.mtross.guessthenumber.dao.GameDao;
import com.mtross.guessthenumber.dao.RoundDao;
import com.mtross.guessthenumber.model.Game;
import com.mtross.guessthenumber.model.Round;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mike
 */
@Service
public class GuessTheNumberService {

    @Autowired
    GameDao games;

    @Autowired
    RoundDao rounds;

    public Game insertGame(Game game) {
        return games.addGame(game);
    }

    public Round insertRound(Round round) {
        return rounds.addRound(round);
    }

    public List<Game> getEveryGame() {
        return games.getAllGames();
    }

    public List<Round> getEveryRound() {
        return rounds.getAllRounds();
    }

    public List<Round> getRoundsOfGame(int gameId) {
        return rounds.getRoundsForGame(gameId);
    }

    public Game getGame(int gameId) {
        return games.getGameById(gameId);
    }

    public Round getRound(int roundId) {
        return rounds.getRoundById(roundId);
    }

    public void reviseGame(Game game) {
        games.updateGame(game);
    }

    public void reviseRound(Round round) {
        rounds.updateRound(round);
    }

    public void removeGame(int gameId) {
        games.deleteGameById(gameId);
    }

    public void removeRound(int roundId) {
        rounds.deleteRoundById(roundId);
    }

    public Game makeNewGame() {
        Game newGame = new Game(0, "0000", "Ongoing");
        newGame.setAnswer(makeRandomNumber());
        return newGame;
    }

    private String makeRandomNumber() {
        int digit1;
        int digit2;
        int digit3;
        int digit4;
        Random rng = new Random();
        String numberString = "";

        digit1 = rng.nextInt(10);
        numberString += digit1;

        do {
            digit2 = rng.nextInt(10);
        } while (digit2 == digit1);
        numberString += digit2;

        do {
            digit3 = rng.nextInt(10);
        } while (digit3 == digit1 || digit3 == digit2);
        numberString += digit3;

        do {
            digit4 = rng.nextInt(10);
        } while (digit4 == digit1 || digit4 == digit2 || digit4 == digit3);
        numberString += digit4;

        return numberString;
    }

    public Round findMatches(int gameId, String guess) {
        Round newRound = new Round();
        newRound.setGameId(gameId);
        newRound.setGuess(guess);

        int exactMatches = 0;
        int partialMatches = 0;
        Game game = getGame(gameId);
        String[] guessDigits = guess.split("");
        String answer = game.getAnswer();
        String[] answerDigits = answer.split("");

        if (guess.equals(answer)) {
            exactMatches = 4;
        } else {
            for (int i = 0; i <= 3; i++) {
                if (guessDigits[i].equals(answerDigits[i])) {
                    exactMatches++;
                } else if (answer.contains(guessDigits[i])) {
                    partialMatches++;
                }
            }
        }

        newRound.setExactMatches(exactMatches);
        newRound.setPartialMatches(partialMatches);

        if (exactMatches == 4) {
            Game finishedGame = getGame(newRound.getGameId());
            finishedGame.setGameStatus("Finished");
            reviseGame(finishedGame);
        }

        return newRound;
    }

    public Game hideGameAnswer(Game aGame) {
        String gameStatus = aGame.getGameStatus();
        if (gameStatus.equals("Ongoing")) {
            aGame.setAnswer("????");
            return aGame;
        } else {
            return aGame;
        }
    }

    public List<Game> hideAllGameAnswers(List<Game> listOfGames) {
        for (Game currentGame : listOfGames) {
            currentGame = hideGameAnswer(currentGame);
        }
        return listOfGames;
    }

}
