package com.sg.guessthenumber.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Round {
    // Create global variables for a round's id, the game id associated with a round,
    // the user's guess of what the game's answer is, the time that the guess was made for the round
    // and the results of the guess made.
    private int round_id;
    private int game_id;
    private String guess;
    private LocalDateTime round_time = LocalDateTime.now();
    private String result;

    // Create a default constructor for the Round class.
    public Round() {
    }

    // Create a getter to retrieve the round's id.
    public int getRound() {
        return round_id;
    }

    // Create a setter to set the round's id.
    public void setRound(int round_id) {
        this.round_id = round_id;
    }

    // Create a getter to retrieve the game id associated with a round.
    public int getGame() {
        return game_id;
    }

    // Create a setter to set the game id associated with a round.
    public void setGame(int game_id) {
        this.game_id = game_id;
    }

    // Create a getter to retrieve the guess made.
    public String getGuess() {
        return guess;
    }

    // Create a setter to set the guess made.
    public void setGuess(String guess) {
        this.guess = guess;
    }

    // Create a getter to retrieve the current time and date.
    public LocalDateTime getTime() {
        return round_time;
    }

    // Create a setter to set the current time and date.
    public void setTime(LocalDateTime round_time) {
        this.round_time = round_time;
    }

    // Create a getter to retrieve the results of your guess.
    public String getResult() {
        return result;
    }

    // Create a setter to set the results of your guess.
    public void setResult(String result) {
        this.result = result;
    }

    // These two methods below are used for running unit test cases, against all the DAO interface methods.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return round_id == round.round_id && game_id == round.game_id && Objects.equals(guess, round.guess) && Objects.equals(round_time, round.round_time) && Objects.equals(result, round.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(round_id, game_id, guess, round_time, result);
    }
}
