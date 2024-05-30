
package BlackJackGame;

public class BlackJackGame {
    private Dealer dealer;
    private Player player;
    private Deck deck;

    public BlackJackGame() {
        newGame();
    }

    public void newGame() {
        deck = new Deck();
        dealer = new Dealer();
        player = new Player();
        
        dealer.drawCard(deck);
        dealer.drawCard(deck);
        player.drawCard(deck);
        player.drawCard(deck);
    }

    public void hit() {
        player.drawCard(deck);
        if (player.getScore() > 21) {
            // Handle bust
        }
    }

    public void stand() {
        // Dealer's turn logic
        while (dealer.getScore() < 17) {
            dealer.drawCard(deck);
        }
        // Determine winner
    }

    public String getGameState() {
        return "Player's hand: " + player.getHand() + "\n" +
               "Dealer's hand: " + dealer.getHand();
    }

    public int getPlayerScore() {
        return player.getScore();
    }

    public int getDealerScore() {
        return dealer.getScore();
    }
}
