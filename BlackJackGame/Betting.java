package BlackJackGame;

public class Betting {
    private String username;
    private double betAmount;

    public Betting(String username, double betAmount) {
        this.username = username;
        this.betAmount = betAmount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(double betAmount) {
        this.betAmount = betAmount;
    }
}
