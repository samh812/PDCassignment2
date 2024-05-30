package BlackJackGame;

import java.util.ArrayList;
import java.util.List;

public class Player {
    protected List<Card> hand;
    
    public Player() {
        hand = new ArrayList<>();
    }

    public void drawCard(Deck deck) {
        hand.add(deck.draw());
    }

    public int getScore() {
        int score = 0;
        int numberOfAces = 0;
        for (Card card : hand) {
            switch (card.getRank()) {
                case TWO: score += 2; break;
                case THREE: score += 3; break;
                case FOUR: score += 4; break;
                case FIVE: score += 5; break;
                case SIX: score += 6; break;
                case SEVEN: score += 7; break;
                case EIGHT: score += 8; break;
                case NINE: score += 9; break;
                case TEN:
                case JACK:
                case QUEEN:
                case KING: score += 10; break;
                case ACE: numberOfAces++; break;
            }
        }
        for (int i = 0; i < numberOfAces; i++) {
            if (score + 11 <= 21) {
                score += 11;
            } else {
                score += 1;
            }
        }
        return score;
    }

    public String getHand() {
        return hand.toString();
    }
}
