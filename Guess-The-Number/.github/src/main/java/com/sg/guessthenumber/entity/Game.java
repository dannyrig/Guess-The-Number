package com.sg.guessthenumber.entity;

import java.util.Objects;

public class Game {
    // Create three global variables for the game's id, answer, and status.
    private int game_id;
    private String answer;
    private boolean gameFinished;

    // Create a default constructor for generating games without any arguments.
    public Game(){
    }

    // Create a getter to retrieve the game's id.
    public int getGameID() {
        return game_id;
    }

    // Create a setter to set an integer value for a game's id.
    public void setGameID(int id) {
        game_id = id;
    }

    // Create a getter to retrieve the randomly generated answer used for a game.
    public String getAnswer() {
        return answer;
    }

    // Create a setter to set the answer value.
    public void setAnswer(String ans) {
        answer = ans;
    }

    // Create a getter to retrieve the game's current status (in-progress or finished)
    public Boolean getStatus() {
        return gameFinished;
    }

    // Create a setter to alter the status of a game.
    public void setStatus(Boolean is_Finished) {
        gameFinished = is_Finished;
    }


    // These two methods below are used for running unit test cases, against all the DAO interface methods.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(game_id, game.game_id) && Objects.equals(answer, game.answer) && Objects.equals(gameFinished, game.gameFinished);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game_id, answer, gameFinished);
    }
}
