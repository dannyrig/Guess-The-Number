package com.sg.guessthenumber.service;


import com.sg.guessthenumber.DAO.GameDAO;
import com.sg.guessthenumber.DAO.RoundDAO;
import com.sg.guessthenumber.entity.Game;
import com.sg.guessthenumber.entity.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceLayer {

    // Create a reference variable to the GameDAO and RoundDAO.
    @Autowired
    GameDAO gameDao;

    @Autowired
    RoundDAO roundDao;

    // Method to retrieve all games.
    public List<Game> getAllGames() {
        // Create a list of type games and use the GameDAO method
        // to get all the current games that exist.
        List<Game> games = gameDao.getAllGames();
        // Use a for-each loop to go through the games list.
        for (Game g : games) {
            // Check and see if the game is still in progress,
            // if so then hide the answer.
            if (!g.getStatus()) {
                g.setAnswer("****");
            }
        }
        return games;
    }

    // Method to retrieve a game by a specific id.
    public Game getGameById(int gameId) {
        // Create a new game and have it equal the
        // GameDAO method used for retrieving specific games.
        Game game = gameDao.getGameById(gameId);
        // Like the previous method, check to see if the game
        // is still in progress, and hide the answer if it is.
        if (!game.getStatus()) {
            game.setAnswer("****");
        }
        return game;
    }

    // Simple method that returns all the rounds associated with a specific game.
    public List<Round> getAllRoundsByGameId(int gameID) {
        return roundDao.getAllRoundsByGameId(gameID);
    }

    // Method used for making a guess.
    public Round makeGuess(Round round) {
        // Get the answer for the game where a guess is being made and store it
        // in a string.
        String answer = gameDao.getGameById(round.getGame()).getAnswer();
        // Get the guess that was made.
        String guess = round.getGuess();
        // Check and see if the input matches the required length and that only numbers were entered
        // If so, set the guess variable to null.
        if (guess.length() < 4 || guess.matches("^[a-zA-Z]*$")) {
            guess = null;
        }
        // Otherwise, generate the results of the guess
        // using the generateResults method.
        String results = generateResults(guess, answer);
        // Set the results for the round.
        round.setResult(results);

        // If the guess is 100% accurate, change the game's status
        // so that it is no longer in progress, and update the game.
        if(guess.equals(answer)) {
            Game game = gameDao.getGameById(round.getGame());
            game.setStatus(true);
            gameDao.updateGame(game);
        }
            // Return the round.
            return roundDao.addRound(round);
    }

    // Method for starting a new game.
    public int startGame() {
        // Create a new game.
        Game game = new Game();
        // Set the answer by generating a random value.
        game.setAnswer(generateAnswer());
        // Add the game to the database.
        game = gameDao.addGame(game);
        // Return the newly created game's id.
        return game.getGameID();
    }

    // Method for generating and setting a random answer for each game.
    public String generateAnswer() {
        // Create four random integer variables that generate values from 1 to 10 (exclusive).
        int randNum1 = (int) (Math.random() * 10);
        int randNum2 = (int) (Math.random() * 10);
        int randNum3 = (int) (Math.random() * 10);
        int randNum4 = (int) (Math.random() * 10);

        // Check to make sure that each number is not equal to any of the other
        // numbers, we don't want repeating values.
        while (randNum2 == randNum1) {
            randNum2 = (int) (Math.random() * 10);
        }
        while (randNum3 == randNum2 || randNum3 == randNum1) {
            randNum3 = (int) (Math.random() * 10);
        }
        while (randNum4 == randNum3 || randNum4 == randNum2 || randNum4 == randNum1) {
            randNum4 = (int) (Math.random() * 10);
        }
        // Convert all the random numbers to string, concat them together, and return the result.
        return Integer.toString(randNum1) + Integer.toString(randNum2) + Integer.toString(randNum3) + Integer.toString(randNum4);
    }

    // Method for checking whether or not the numbers entered match
    // the answer of a game, or if they partially match.
    public String generateResults(String guess, String answer) {
        // Store both the guess and the actual answer into a char array
        // to loop through the values.
        char[] guessNumbers = guess.toCharArray();
        char[] answerNumbers = answer.toCharArray();

        // Create two counters for checking the number of partial and exact results.
        int partial = 0;
        int exact = 0;
        // Create a boolean value with a default value of false,
        // this will be used to see if a number with repeating values was entered.
        boolean repeating = false;
        // Create an empty string value, that will be updated and returned later on.
        String result = "";
        // Check and see if any repeating numbers exist, and if they do set the
        // boolean variable created above to true.
        if (guessNumbers[0] == guessNumbers[1] || guessNumbers[0] == guessNumbers[2] || guessNumbers[0] == guessNumbers[3]) {
            repeating = true;
        } else if (guessNumbers[1] == guessNumbers[2] || guessNumbers[1] == guessNumbers[3]) {
            repeating = true;
        } else if (guessNumbers[2] == guessNumbers[3]) {
            repeating = true;
        }

        // If no repeating numbers exist then proceed with the rest of the method.
        if (!repeating) {
                // Loop through each character in the guess array.
                for (int i = 0; i < guessNumbers.length; i++) {
                    // If one of the numbers guessed matches one of the numbers in the answer,
                    // and it is in the same position, increment the exact counter.
                    if (guessNumbers[i] == answerNumbers[i]) {
                        exact++;
                    // Otherwise, if the one of the numbers guessed matches one of the numbers
                    // in the answer, but the positions are different, increment the partial counter.
                    } else if (answer.contains(String.valueOf(guessNumbers[i]))) {
                        partial++;
                    }
                }
                // Display the results based on the exact and partial counters.
                result = "e:" + exact + ":p:" + partial;
            } else {
            // If a repeating number value was found, return null.
            result = null;
        }
        // Return the final result.
        return result;
    }

}