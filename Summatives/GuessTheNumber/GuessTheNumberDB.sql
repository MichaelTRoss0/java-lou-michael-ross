DROP DATABASE IF EXISTS GuessTheNumberDatabase;

CREATE DATABASE GuessTheNumberDatabase;

USE GuessTheNumberDatabase;

-- Tables
CREATE TABLE Game (
    gameId INT PRIMARY KEY AUTO_INCREMENT,
    
    answer CHAR(4) NOT NULL,
    gameStatus VARCHAR(8) DEFAULT "Ongoing" NOT NULL
);

CREATE TABLE Round (
    roundId INT PRIMARY KEY AUTO_INCREMENT,
    
    gameId INT NOT NULL,
    guess CHAR(4) NOT NULL,
    timeOfGuess TIMESTAMP DEFAULT NOW() NOT NULL,
    exactMatches TINYINT NOT NULL,
    partialMatches TINYINT NOT NULL,
    FOREIGN KEY fk_Round_gameId (GameId)
        REFERENCES Game(gameId)
);
