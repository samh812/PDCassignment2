/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package BlackJackGame;

import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.SQLException;

public class BettingTest {

    @Test
    public void testBetPlacement() throws SQLException {
        BlackJackDBManager dbManager = new BlackJackDBManager();
        String username = "testUser";
        String password = "testPass";
        double initialBalance = 1000.00; //setting the predefined initial balance
        double betAmount = 10;

        //ensure the user exists and set the balance to a known value
        if (!dbManager.checkCredentials(username, password)) {
            dbManager.addUser(username, password);
        }
        dbManager.updateUserBalance(username, -dbManager.getUserBalance(username) + initialBalance);

        //place the bet
        Betting bet = new Betting(username, betAmount);
        dbManager.placeBet(bet);

        //check the balance after placing the bet
        double newBalance = dbManager.getUserBalance(username);
        assertEquals("Balance should be reduced by bet amount", initialBalance - betAmount, newBalance, 0.001);

        //close the database connection
        dbManager.closeConnections();
    }
}