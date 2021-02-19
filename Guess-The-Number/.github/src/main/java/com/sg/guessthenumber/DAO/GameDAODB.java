package com.sg.guessthenumber.DAO;

import com.sg.guessthenumber.entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// This DAO class handles creating, reading, and updating
// values associated with the game table stored in the SQL
// database.
@Repository
public class GameDAODB implements GameDAO{

    // Create an autowired jdbc variable for querying and returning
    // values from the database.
    @Autowired
    JdbcTemplate jdbc;

    public static final class GameMapper implements RowMapper<Game> {

        // This method is uses an interface to map rows of a result set from the game table.
        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            // Create a new Game
            Game gm = new Game();
            // Retrieve all the attributes used to define the game table.
            gm.setGameID(rs.getInt("Game_ID"));
            gm.setAnswer(rs.getString("Answer"));
            gm.setStatus(rs.getBoolean("Is_Finished"));
            // Return the newly created game.
            return gm;
        }
    }

    // Method for querying the database and retrieving all
    // values stored within the game table.
    @Override
    public List<Game> getAllGames() {
        // Use the same command normally used for retrieving these
        // values in SQL, and assign it to a String.
        final String SELECT_ALL_GAMES = "SELECT * FROM game";
        // Return the list of games retrieved.
        return jdbc.query(SELECT_ALL_GAMES, new GameMapper());
    }

    // Method for querying the database and retrieving all
    // values from a specific game in the database.
    @Override
    public Game getGameById(int gameId) {
        // Surround in try-catch block to catch any errors.
        try {
            // Use the same command normally used for retrieving these
            // values in SQL, and assign it to a String.
            final String SELECT_GAME_BY_ID = "SELECT * FROM game WHERE Game_ID = ?";
            // Return the game retrieved.
            return jdbc.queryForObject(SELECT_GAME_BY_ID, new GameMapper(), gameId);
        } catch(DataAccessException ex) {
            // Return null, if something went wrong.
            return null;
        }
    }

    // Method for adding a newly created game to the database.
    @Override
    @Transactional
    public Game addGame(Game game) {
        // Use the same command used for inserting values in a table in SQL.
        // (Note: We are only adding one value, as the other two values have default values
        // (made alongside a newly created Game).
        final String INSERT_GAME = "INSERT INTO game(answer) VALUES(?)";
        // Update the database to reflect these changes.
        jdbc.update(INSERT_GAME,
                game.getAnswer()
                );
        // Make sure that this id has the proper value to match the game_id's sequential order,
        // by retrieving the ID of the last value inserted into the table, before this one.
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        // Set the game's id to the value above.
        game.setGameID(newId);
        // Return the newly created game.
        return game;
    }

    // Method for updating an existing game in the database.
    @Override
    public void updateGame(Game game) {
        // Update only the status (the game's current progress) of a specific game,
        // as that will be the only value that needs to be changed.
        final String UPDATE_GAME = "UPDATE game SET Is_Finished = ? WHERE Game_ID = ?";
        // Update the database.
        jdbc.update(UPDATE_GAME,
                game.getStatus(),
                game.getGameID());
    }

}
