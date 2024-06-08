package BlackJackGame;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {
    private Deck deck;
    private List<Card> playerHand;
    private List<Card> dealerHand;
    private boolean dealerCardRevealed;

    public BlackJackGame() {
        newGame();
    }

    public void newGame() {
        deck = new Deck();
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        dealerCardRevealed = false;
        // Deal two cards to the player and the dealer at the start of the game
        playerHand.add(deck.draw());
        playerHand.add(deck.draw());
       // dealerHand.add(deck.draw());
        dealerHand.add(deck.draw());
    }

    public Card hit() {
        if (!deck.isEmpty()) {
            Card card = deck.draw();
            playerHand.add(card);
            return card;
        } else {
            return null; // Indicate no more cards
        }
    }

    public Card dealerHit() {
        if (!deck.isEmpty()) {
            Card card = deck.draw();
            dealerHand.add(card);
            return card;
        } else {
            return null; // Indicate no more cards
        }
    }

    public void revealDealerCard() {
        dealerCardRevealed = true;
    }

    public boolean isDealerCardRevealed() {
        return dealerCardRevealed;
    }

    public boolean isDeckEmpty() {
        return deck.isEmpty();
    }

    public int getPlayerScore() {
        return calculateScore(playerHand);
    }

    public int getDealerScore() {
        return calculateScore(dealerHand);
    }

    public boolean hasPlayerBusted() {
        return getPlayerScore() > 21;
    }

    public boolean hasDealerBusted() {
        return getDealerScore() > 21;
    }

    public List<Card> getPlayerHand() {
        return playerHand;
    }

    public List<Card> getDealerHand() {
        return dealerHand;
    }

    private int calculateScore(List<Card> hand) {
        int score = 0;
        int aces = 0;
        for (Card card : hand) {
            score += card.getValue();
            if (card.getValue() == 11) {
                aces++;
            }
        }
        // Adjust for aces
        while (score > 21 && aces > 0) {
            score -= 10;
            aces--;
        }
        return score;
    }
}
