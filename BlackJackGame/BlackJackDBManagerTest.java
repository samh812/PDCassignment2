package BlackJackGame;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BlackJackDBManagerTest {

    private BlackJackDBManager dbManager;

    @Before
    public void setUp() {
        dbManager = new BlackJackDBManager();

    }

    @After
    public void tearDown() {
        //delete test user from the database after each test
        try {
            dbManager.deleteUser("test_user");
            dbManager.closeConnections();
        } catch (SQLException ex) {
            fail("SQLException occurred: " + ex.getMessage());
        }
    }

    @Test
    public void testEstablishConnection() {
        Connection conn = dbManager.getConnection();
        assertNotNull("Connection should not be null", conn);
        try {
            assertTrue("Connection should be open", !conn.isClosed());
        } catch (SQLException ex) {
            fail("SQLException occurred: " + ex.getMessage());
        }
    }

    @Test
    public void testCheckCredentials() {
        //adding a new user for the test
        assertTrue("Adding a new user should return true", dbManager.addUser("test_user", "test_password"));

        //test with the new user
        assertTrue("Valid credentials should return true", dbManager.checkCredentials("test_user", "test_password"));
        assertFalse("Invalid credentials should return false", dbManager.checkCredentials("test_user", "wrong_password"));
    }

    @Test
    public void testGetUserBalance() {
        //adding a new user for the test
        assertTrue("Adding a new user should return true", dbManager.addUser("test_user", "test_password"));

        //test with the new user
        assertEquals("User balance should be 1000.00", 1000.00, dbManager.getUserBalance("test_user"), 0.001);
    }

    @Test
    public void testPlaceBet() {
        //adding a new user for the test
        assertTrue("Adding a new user should return true", dbManager.addUser("test_user", "test_password"));

        //place bet for the new user
        Betting bet = new Betting("test_user", 100.00);
        try {
            dbManager.placeBet(bet);
            assertEquals("User balance should be 900.00 after placing a bet of 100.00", 900.00, dbManager.getUserBalance("test_user"), 0.001);
        } catch (SQLException ex) {
            fail("SQLException occurred: " + ex.getMessage());
        }
    }

    @Test
    public void testUpdateUserBalance() {
        //adding a new user for the test
        assertTrue("Adding a new user should return true", dbManager.addUser("test_user", "test_password"));

        //update balance for the new user
        try {
            dbManager.updateUserBalance("test_user", 500.00);
            assertEquals("User balance should be 1500.00 after updating with 500.00", 1500.00, dbManager.getUserBalance("test_user"), 0.001);
        } catch (SQLException ex) {
            fail("SQLException occurred: " + ex.getMessage());
        }
    }
}
