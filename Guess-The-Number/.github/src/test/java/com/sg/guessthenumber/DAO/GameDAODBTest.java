package com.sg.guessthenumber.DAO;

import com.sg.guessthenumber.TestApplicationConfiguration;
import com.sg.guessthenumber.entity.Game;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

// In this class, tests will be ran to check the validity of the methods
// created in gameDAO class, and see if they function like their suppose to.
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GameDAODBTest extends TestCase {

    @Autowired
    GameDAO gameDao;

    // Unit test to test the GetAllGames function.
    @Test
    public void testGetAllGames() {
        // Create a new game.
        Game game = new Game();
        // Set the answer to a random value.
        game.setAnswer("2354");
        // Set the game's status.
        game.setStatus(false);
        // Add the game to the database.
        gameDao.addGame(game);

        // Create another new game, similar to the one above.
        Game game2 = new Game();
        game2.setAnswer("4687");
        game2.setStatus(false);
        gameDao.addGame(game2);

        // Use the gameDAO method to get all the games that were just created.
        // Store it in a list.
        List<Game> games = gameDao.getAllGames();

        // See if the size of the database is equal to two (the number of games created).
        assertEquals(2, games.size());
        // See if the database contains both game objects created.
        assertTrue(games.contains(game));
        assertTrue(games.contains(game2));


    }

    // Unit test to test the AddGame function.
    @Test
    public void testAddGame() {
        // Create a new game.
        Game game = new Game();
        // Set the answer to a random value.
        game.setAnswer("9503");
        // Set the game's status.
        game.setStatus(false);
        // Add the game to the database.
        game = gameDao.addGame(game);

        // Retrieve the newly created game from the database.
        Game fromDAO = gameDao.getGameById(game.getGameID());
        // Check and see if the game just retrieved equals the game object created.
        assertEquals(game, fromDAO);
    }

    // Unit test to test the UpdateGame function.
    @Test
    public void testUpdateGame() {
        // Create a new game.
        Game game = new Game();
        // Set the answer to a random value.
        game.setAnswer("1264");
        // Set the game's status.
        game.setStatus(false);
        // Add the game to the database.
        game = gameDao.addGame(game);

        // Retrieve the newly created game from the database.
        Game fromDAO = gameDao.getGameById(game.getGameID());
        // Check and see if the game just retrieved equals the game object created.
        assertEquals(game, fromDAO);
        // Change the game's status from false to true.
        game.setStatus(true);
        // Update the changes made to the database.
        gameDao.updateGame(game);
        // Assume now that the fromDAO no long equals the game variable.
        assertNotEquals(game, fromDAO);
        // Retrieve the same game object from the database again,
        // if everything went well, it should now be different.
        fromDAO = gameDao.getGameById(game.getGameID());
        // Now assume that the fromDAO and game object are equal.
        assertEquals(game, fromDAO);
    }
}