package BlackJackGame;

import java.util.ArrayList;

public class Player extends Person 
{
    public int totalCardValue = 0;
    public int newTotal = 0;
    ArrayList<String> playerCardNum = new ArrayList<>();
    ArrayList<String> playerSuit = new ArrayList<>();

    public Player() { //Player constructor
        initializePlayerCards();
    }
    
    private void initializePlayerCards() { //generating player cards and storing them in ArrayLists
        for(int i = 0; i < 10; i++) {
            playerCardNum.add(generateCardNum());
            playerSuit.add(generateSuit());

        }
    }
    @Override
    public void printCard() { //method to print out cards side by side
                                //tracks pR to see what round the player is on and how many cards to print
        int cardAmount = pR + 1;



        for(int x = 0; x < cardAmount+1;x++){
            System.out.print(" ------------");
            System.out.print("      ");
        }
        System.out.print("\n");
        

        for(int x = 0; x < cardAmount+1;x++){
            if(playerCardNum.get(x).equals("10")){
                System.out.print("|" + playerSuit.get(x) + "         " + playerCardNum.get(x) + "|");
                System.out.print("     ");
            }
            else{
                System.out.print("|" + playerSuit.get(x) + "          " + playerCardNum.get(x) + "|");
                System.out.print("     ");
            }

        }
        System.out.print("\n");

        for(int x = 0; x < cardHeight;x++){
            for (int j = 0; j < cardAmount+1; j++) {
                System.out.print("|            |");
                System.out.print("     ");

            }
            System.out.print("\n");

        }


        for(int x = 0; x < cardAmount+1;x++){
            if(playerCardNum.get(x).equals("10")){
                System.out.print("|" + playerCardNum.get(x) + "         " + playerSuit.get(x) + "|");
                System.out.print("     ");
            }
            else{
                System.out.print("|" + playerCardNum.get(x) + "          " + playerSuit.get(x) + "|");
                System.out.print("     ");
            }

        }
        System.out.print("\n");

        for(int x = 0; x < cardAmount+1;x++){
            System.out.print(" ------------");
            System.out.print("      ");
        }
        System.out.print("\n");
    }
    
    @Override
    public void newCardToScreen()//Calculating the value of the player's hand and printing cards
    {
        int playerAceCount = 0;
        if(pR == 0){//For the first round, where two cards have to be printed and valued
            int card = pR;
            System.out.println("Player cards: ");
            printCard();
            int cardString = getCardValue(playerCardNum.get(card));
            newTotal = totalCardValue += cardString;

            int cardString2 = getCardValue(playerCardNum.get(card+1));
            newTotal = totalCardValue += cardString2;


            for (int i = 0; i < pR+2; i++) {

              if (playerCardNum.get(i).equals("A")) { //counting how many aces are in the hand
                  playerAceCount++;

              }
            }

            if (newTotal > 21 && playerAceCount > 0){
                while(playerAceCount > 0 && newTotal > 21){//Subtracting a value of 10 for every ace that make the total value more than 21
                playerAceCount --;
                newTotal -= 10;
                }
            }
            System.out.println("Total Player Card Value: " + newTotal); //print total card value
            

            
        }
        else{//For every other round
            int card = pR;
            printCard();
            int cardString = getCardValue(playerCardNum.get(card+1));
            for (int i = 0; i < pR+2; i++) {
              if (playerCardNum.get(i).equals("A")) {//Ace counting
                  playerAceCount++;

              }
            }

            newTotal = totalCardValue += cardString;
            if (newTotal > 21 && playerAceCount > 0){//Subtracting for aces
                while(playerAceCount > 0 && newTotal > 21){
                playerAceCount --;
                newTotal -= 10;
                }
            }

            System.out.println("Your new Card");


            System.out.println("Total Player Card Value: " + newTotal); //print total card value
        }
        

    }
    
    

    public int getNewTotal()
    {
        return this.newTotal;
    }
    
    private int getCardValue(String playerRound) 
    {
        switch (playerRound) {
            case "A":
                return CardNumber.A.getValue();
            case "J":
                return CardNumber.J.getValue();
            case "Q":
                return CardNumber.Q.getValue();
            case "K":
                return CardNumber.K.getValue();
            default:
                return Integer.parseInt(playerRound);
        }
    }
}
