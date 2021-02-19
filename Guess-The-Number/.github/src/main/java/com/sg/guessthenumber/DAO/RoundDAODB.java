package com.sg.guessthenumber.DAO;


import com.sg.guessthenumber.entity.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

// This DAO class handles creating and reading
// values associated with the round table stored in the SQL
// database.
@Repository
public class RoundDAODB implements RoundDAO{

    // Create an autowired jdbc variable for querying and returning
    // values from the database.
    @Autowired
    JdbcTemplate jdbc;

    public static final class RoundMapper implements RowMapper<Round> {

        // This method is uses an interface to map rows of a result set from the round table.
        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            // Create a new round
            Round rd = new Round();
            // Retrieve all the attributes used to define the round table.
            rd.setRound(rs.getInt("Round_ID"));
            rd.setGame(rs.getInt("Game_ID"));
            rd.setGuess(rs.getString("Guess"));

            // Set the timestamp of the round equal to the current date and time
            Timestamp stamp = rs.getTimestamp("Round_Time");
            rd.setTime(stamp.toLocalDateTime());

            // Return the newly created game.
            rd.setResult(rs.getString("Result"));
            return rd;
        }
    }

    // Method for querying the database and retrieving all
    // the values for a round of a specific game.
    @Override
    public List<Round> getAllRoundsByGameId(int gameID) {
        // Surround in a try-catch block to handle exception errors.
        try {
            // Use a SQL query statement to search for the round using the game's id
            // and order the results by the time.
            final String SELECT_ROUNDS_BY_GAME_ID = "SELECT * FROM round "
                    + "WHERE Game_ID = ? ORDER BY Round_Time";
            // Return a list of all the values searched for.
            List<Round> rounds = jdbc.query(SELECT_ROUNDS_BY_GAME_ID, new RoundMapper(), gameID);
            return rounds;
        } catch(DataAccessException ex) {
            // If nothing can be found or an error occurs, return null.
            return null;
        }
    }

    // Method to retrieve a round by its id.
    @Override
    public Round getRoundById(int roundId) {
        // Surround in a try-catch block to handle exception errors.
        try {
            // Use a SQL query statement to search for the id from the round table,
            // and return the results.
            final String SELECT_ROUND_BY_ID = "SELECT * FROM round WHERE Round_ID = ?";
            return jdbc.queryForObject(SELECT_ROUND_BY_ID, new RoundMapper(), roundId);
        } catch(DataAccessException ex) {
            // If nothing can be found or an error occurs, return null.
            return null;
        }
    }


    // Method for adding a new round in the database.
    @Override
    @Transactional
    public Round addRound(Round round) {
        // Use a SQL insertion statement to add the proper values for the round to be inserted.
        final String INSERT_ROUND = "INSERT INTO round(Game_ID, Guess, Result) VALUES(?, ?, ?)";
        // Update the database to reflect the changes made.
        jdbc.update(INSERT_ROUND,
                round.getGame(),
                round.getGuess(),
                round.getResult());
        // Make sure that this id has the proper value to match the round_id's sequential order,
        // by retrieving the ID of the last value inserted into the table, before this one.
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        // Set the round_id to the value above;
        round.setRound(newId);
        // Return the newly created round.
        return round;
    }




}
