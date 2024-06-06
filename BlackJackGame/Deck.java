package BlackJackGame;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    private Stack<Card> cards;

    public Deck() {
        cards = new Stack<>();
        for (int i = 0; i < 52; i++) {
            cards.push(new Card(i));
        }
        Collections.shuffle(cards);
    }

    public Card draw() {
        return cards.pop();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
