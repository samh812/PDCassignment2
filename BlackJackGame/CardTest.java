/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package BlackJackGame;

import org.junit.Test;
import static org.junit.Assert.*;

public class CardTest {

    @Test
    public void testGetValue() {
        Card card1 = new Card(0); //TWO
        assertEquals(2, card1.getValue());

        Card card2 = new Card(37); //JACK
        assertEquals(10, card2.getValue());

        Card card3 = new Card(51); //ACE
        assertEquals(11, card3.getValue());
    }

    @Test
    public void testToString() {
        Card card = new Card(20); //SEVEN
        assertEquals("Card #20 with value 7", card.toString());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCardIndex() {
        Card card = new Card(52); //index out of bounds
    }
}