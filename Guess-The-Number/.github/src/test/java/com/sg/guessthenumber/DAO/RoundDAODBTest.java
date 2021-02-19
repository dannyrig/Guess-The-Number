package com.sg.guessthenumber.DAO;

import com.sg.guessthenumber.TestApplicationConfiguration;
import com.sg.guessthenumber.entity.Game;
import com.sg.guessthenumber.entity.Round;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotEquals;

// In this class, tests will be ran to check the validity of the methods
// created in gameDAO class, and see if they function like their suppose to.
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class RoundDAODBTest extends TestCase {

    @Autowired
    GameDAO gameDao;

    @Autowired
    RoundDAO roundDao;

    // Unit test to test the GetAllRoundsByGameId function.
    @Test
    public void testGetAllRoundsByGameId() {
        // Create a new game.
        Game game = new Game();
        // Set the answer to a random value.
        game.setAnswer("1045");
        // Set the game's status.
        game.setStatus(false);
        // Add the game to the database.
        game = gameDao.addGame(game);

        // Create a new round for the game object just created.
        Round round = new Round();
        // Set the game_id for the round object equal to the newly created game id.
        round.setGame(game.getGameID());
        // Set the guess to a value that doesn't match at all.
        round.setGuess("9876");
        // Set the proper result for the round.
        round.setResult("e:0:p:0");
        // Add the round to the database.
        roundDao.addRound(round);

        // Create another new round object, similar to the one above and repeat the steps.
        Round round2 = new Round();
        round2.setGame(game.getGameID());
        round2.setGuess("1045");
        round2.setResult("e:4:p:0");
        roundDao.addRound(round2);

        // Retrieve all rounds from the game object made and store everything in a list.
        List<Round> rounds = roundDao.getAllRoundsByGameId(game.getGameID());

        // Assume the list size is two (the number of rounds created).
        assertEquals(2, rounds.size());

    }

    // Unit test for the AddRound function.
    @Test
    public void testAddRound() {
        // Create a new game.
        Game game = new Game();
        // Set the answer to a random value.
        game.setAnswer("8103");
        // Set the game's status.
        game.setStatus(false);
        // Add the game to the database.
        game = gameDao.addGame(game);

        // Create a new round for the game object just created.
        Round round = new Round();
        // Set the game_id for the round object equal to the newly created game id.
        round.setGame(game.getGameID());
        // Set the guess to a value that doesn't match at all.
        round.setGuess("4592");
        // Set the proper result for the round.
        round.setResult("e:0:p:0");
        // Add the round to the database.
        roundDao.addRound(round);

        // Assume that the round object and the object being retrieved from the database
        // is not null, meaning that a round object did get created and does exist.
        assertNotNull(round = roundDao.getRoundById(round.getRound()));
    }
}