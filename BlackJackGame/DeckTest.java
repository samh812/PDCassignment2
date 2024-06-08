/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package BlackJackGame;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

public class DeckTest {

    @Test
    public void testDraw() {
        Deck deck = new Deck();
        assertFalse(deck.isEmpty());

        Set<Integer> drawnIndexes = new HashSet<>();
        for (int i = 0; i < 52; i++) {
            Card card = deck.draw();
            assertNotNull(card);
            assertFalse(drawnIndexes.contains(card.getIndex())); //each card should be unique
            drawnIndexes.add(card.getIndex());
        }

        assertTrue(deck.isEmpty());
    }

    @Test
    public void testShuffle() {
        //since testing randomness is non-deterministic, we'll use a statistical approach
        final int NUM_TESTS = 1000; //number of times to run the test
        final double ACCEPTABLE_THRESHOLD = 0.05; //acceptable deviation from expected proportion

        int numDifferentDraws = 0;
        for (int i = 0; i < NUM_TESTS; i++) {
            Deck deck1 = new Deck();
            Deck deck2 = new Deck();

            //draw cards from both decks to see if they're different
            boolean different = false;
            for (int j = 0; j < 52; j++) {
                if (deck1.draw().getIndex() != deck2.draw().getIndex()) {
                    different = true;
                    break;
                }
            }

            if (different) {
                numDifferentDraws++;
            }
        }

        double proportionDifferent = (double) numDifferentDraws / NUM_TESTS;
        assertTrue("Proportion of different draws outside of acceptable threshold",
                Math.abs(proportionDifferent - 1.0) < ACCEPTABLE_THRESHOLD);
    }
}