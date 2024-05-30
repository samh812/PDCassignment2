/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BlackJackGame;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    private Stack<Card> cards;

    public Deck() {
        cards = new Stack<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cards.push(new Card(suit, rank));
            }
        }
        Collections.shuffle(cards);
    }

    public Card draw() {
        return cards.pop();
    }
}
