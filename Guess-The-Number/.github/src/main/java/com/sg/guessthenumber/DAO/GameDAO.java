package com.sg.guessthenumber.DAO;

import com.sg.guessthenumber.entity.Game;
import java.util.List;

public interface GameDAO {
    List<Game> getAllGames();
    Game getGameById(int gameId);
    Game addGame(Game game);
    void updateGame(Game game);
}
