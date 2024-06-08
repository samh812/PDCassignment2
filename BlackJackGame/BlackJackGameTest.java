/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package BlackJackGame;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import BlackJackGame.BlackJackGame;
import BlackJackGame.Card;
 
public class BlackJackGameTest {
 
    private BlackJackGame game;
 
    @Before
    public void setUp() {
        game = new BlackJackGame();
    }
 
    @Test
    public void testInitialGameState() {
        assertNotNull(game.getPlayerHand());
        assertNotNull(game.getDealerHand());
        assertEquals(2, game.getPlayerHand().size());  // Player should start with 2 cards
        assertEquals(2, game.getDealerHand().size());  // Dealer should start with 1 card
    }
 
    @Test
    public void testPlayerHit() {
        int initialSize = game.getPlayerHand().size();
        game.hit();
        assertEquals(initialSize + 1, game.getPlayerHand().size());
    }
 
    @Test
    public void testDealerHit() {
        int initialSize = game.getDealerHand().size();
        game.dealerHit();
        assertEquals(initialSize + 1, game.getDealerHand().size());
    }
 
    @Test
    public void testPlayerBust() {
        game.getPlayerHand().clear();
        game.getPlayerHand().add(new Card(32));  // Ten
        game.getPlayerHand().add(new Card(36));  // Jack
        game.getPlayerHand().add(new Card(44));  // Five
        assertTrue(game.hasPlayerBusted());
    }
 
    @Test
    public void testDealerBust() {
        game.getDealerHand().clear();
        game.getDealerHand().add(new Card(32));  // Ten
        game.getDealerHand().add(new Card(36));  // Jack
        game.getDealerHand().add(new Card(44));  // Five
        assertTrue(game.hasDealerBusted());
    }
}