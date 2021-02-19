package com.sg.guessthenumber.controller;


import com.sg.guessthenumber.entity.Game;
import com.sg.guessthenumber.entity.Round;
import com.sg.guessthenumber.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api")

// Controller for handling the REST endpoint methods
// used to send and retrieve data.
public class GuessTheNumberController {

    // Create an Autowired annotation and have it wired to a
    // ServiceLayer variable (the class used for executing the main functions of this game)
    @Autowired
    ServiceLayer gameplay;

    // Create a POST method for beginning a new game
    // Return the game_id attached to the newly created game
    // and a HttpStatus message to show that everything
    // has executed successfully.
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int begin() {
        return gameplay.startGame();
    }

    // Create a POST method allowed for guessing a game's answer.
    @PostMapping("/guess")
    public Round guess(@RequestBody Round round) {
        return gameplay.makeGuess(round);
    }

    // Create a GET method to retrieve all the games
    // stored in the database.
    @GetMapping("/game")
    public List<Game> getGames() {
        return gameplay.getAllGames();
    }

    // Create a GET method to retrieve a game by a specific id
    // and return all the values associated with that game.
    @GetMapping("/game/{game_id}")
    public Game gameByID(@PathVariable("game_id") int gameID) {
            return gameplay.getGameById(gameID);
    }

    // Create a GET method to retrieve all rounds associated with
    // a specific game.
    @GetMapping("/rounds/{game_id}")
    public List<Round> getAllRounds(@PathVariable("game_id") int gameID) {
        return gameplay.getAllRoundsByGameId(gameID);
    }
}
