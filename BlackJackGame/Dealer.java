package BlackJackGame;

public class Dealer extends Player {
    private static final int DEALER_STAND_THRESHOLD = 17;

    public void playTurn(Deck deck) {
        while (getScore() < DEALER_STAND_THRESHOLD) {
            drawCard(deck);
        }
    }

    @Override
    public String getHand() {
        // Show only the first card of the dealer's hand and hide the rest until the game is over
        if (hand.size() > 1) {
            StringBuilder handString = new StringBuilder();
            handString.append(hand.get(0).toString()).append(", [hidden]");
            return handString.toString();
        }
        return super.getHand();
    }
    
    public String getFullHand() {
        return super.getHand();
    }
}

