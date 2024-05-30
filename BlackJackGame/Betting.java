package BlackJackGame;

import java.util.Scanner;

public class Betting extends userLogin {

    private userLogin userLogin;
    private int currentBet = 0;

    public Betting(userLogin login) {
        this.userLogin = login;
    }
    
    public void placeBet(String userName) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Your current balance is: $" + userLogin.getBalance(userName));
    System.out.print("Enter your bet amount: ");
    
    //Loop until a valid int is entered
    while (!scanner.hasNextInt()) 
    {
        String input = scanner.next(); 
        System.out.println("Invalid input: " + input + ". Please enter an integer.");
        System.out.print("Enter your bet amount: ");
    }
    int betAmount = scanner.nextInt();
    
    //Check if the bet is within the user's balance
    while (betAmount > userLogin.getBalance(userName) || betAmount <= 0)
    {
        if (betAmount <= 0) {
            System.out.println("Bet amount must be greater than 0.");
        } 
        else 
        {
            System.out.println("Bet amount cannot exceed your current balance.");
        }
        System.out.print("Enter your bet amount: ");
        
        while (!scanner.hasNextInt()) 
        {
            String input = scanner.next();
            System.out.println("Invalid input: " + input + ". Please enter an integer.");
            System.out.print("Enter your bet amount: ");
        }
        betAmount = scanner.nextInt();
    }
    
    currentBet = betAmount;
    //Correctly deduct the bet amount from the user's current balance
    int newBalance = userLogin.getBalance(userName) - betAmount;
    userLogin.updateBalance(userName, newBalance); //Update the balance
    System.out.println("Bet placed. Your new balance is: $" + newBalance);
}

    public void payout(String userName, boolean won) {
        if (won) {
            int winnings = currentBet * 2;
            int adjustment = winnings - currentBet; //Since the bet was already deducted, we add only the winnings minus the deducted bet.
            userLogin.updateBalance(userName, userLogin.getBalance(userName) + adjustment);
            System.out.println("You won!");
        } else {

            System.out.println("You lost your bet.");
        }

        //display the updated balance
        System.out.println("Your new balance is: $" + userLogin.getBalance(userName));
    }

    public void refund(String userName) 
    {
        //Refund the bet amount by adding it back to the user's balance
        int newBalance = userLogin.getBalance(userName) + currentBet;
        userLogin.updateBalance(userName, newBalance); // Update the balance with the refunded amount

        currentBet = 0;

        System.out.println("Your bet of $" + currentBet + " has been refunded.");
        System.out.println("Your new balance is: $" + userLogin.getBalance(userName));
    }

    public int getCurrentBet() 
    {
        return currentBet;
    }
}
