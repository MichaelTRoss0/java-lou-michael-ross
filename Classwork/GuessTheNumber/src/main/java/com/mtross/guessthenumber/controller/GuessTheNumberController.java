/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.guessthenumber.controller;

import com.mtross.guessthenumber.model.Game;
import com.mtross.guessthenumber.model.Round;
import com.mtross.guessthenumber.service.GuessTheNumberService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mike
 */
@Component
@RestController
@RequestMapping("/api")
public class GuessTheNumberController {

    @Autowired
    GuessTheNumberService service;

    @PostMapping("/begin")
    Integer startNewGame() {
        Game newGame = service.makeNewGame();
        newGame = service.insertGame(newGame);
        return newGame.getGameId();
    }

    @PostMapping("/guess")
    Round makeAGuess(@RequestBody Round round) {
        Round newRound = service.findMatches(round.getGameId(), round.getGuess());
        newRound = service.insertRound(newRound);
        return newRound;
    }

    @GetMapping("/game")
    List<Game> getListOfGames() {
        List<Game> listOfGames = service.getEveryGame();
        listOfGames = service.hideAllGameAnswers(listOfGames);
        return listOfGames;
    }

    @GetMapping("/game/{gameId}")
    Game getOneGame(@PathVariable("gameId") int gameId) {
        Game aGame = service.getGame(gameId);
        aGame = service.hideGameAnswer(aGame);
        return aGame;
    }

    @GetMapping("/rounds/{gameId}")
    List<Round> getRoundsByGame(@PathVariable("gameId") int gameId) {
        return service.getRoundsOfGame(gameId);
    }

}
