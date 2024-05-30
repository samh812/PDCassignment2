
package BlackJackGame;


import java.util.ArrayList;


public class Dealer extends Person
{
    public int dealerTotalCardValue = 0;
    public int newTotal = 0;

    ArrayList<String> dealerCardNum = new ArrayList<>(); //store randomly generated num to arraylist to be accessed for creating cards
    ArrayList<String> dealerSuit = new ArrayList<>(); //store randomly generated suit to arraylist to be accessed for creating cards 

    public Dealer() //adding cards to array
    {
        initializePlayerCards();
    }
    
    private void initializePlayerCards() //function to add 15 random cards to their respective arrays so they can be accessed during card creation
    {
        for(int i = 0; i < 15; i++) {
            dealerCardNum.add(generateCardNum());
            dealerSuit.add(generateSuit());

        }
    }
    

//function prints the first card in the array and then a hard coded blank card next to it to simulate a card that is face down, takes values from index 0 of array to create the first card
//if card number is equal to 10 cards need to print slightly differently to have cards inline with eachother 
    public void printBlank() 
    {

        
        int cardAmount;
        if(dR == 0){
            cardAmount = dR + 1;
            
        }
        else{
            cardAmount = dR;
        }

        for(int x = 0; x < cardAmount+1;x++){
            System.out.print(" ------------");
            System.out.print("      ");
        }
        System.out.print("\n");
        

        if(dealerCardNum.get(0).equals("10"))
        {
            System.out.print("|" + dealerSuit.get(0) + "         " + dealerCardNum.get(0) + "|" + "     " + "|            |");
        }
        else{
            System.out.print("|" + dealerSuit.get(0) + "          " + dealerCardNum.get(0) + "|"+ "     " + "|            |");
        }
        System.out.print("\n");

        for(int x = 0; x < cardHeight;x++){
            for (int j = 0; j < cardAmount+1; j++) {
                System.out.print("|            |");
                System.out.print("     ");

            }
            System.out.print("\n");

        }



            if(dealerCardNum.get(0).equals("10"))
            {
                System.out.print("|" + dealerCardNum.get(0) + "         " + dealerSuit.get(0) + "|"+ "     " + "|            |");
          
            }
            else
            {
                System.out.print("|" + dealerCardNum.get(0) + "          " + dealerSuit.get(0) + "|"+ "     " + "|            |");
 
            }

        
        System.out.print("\n");

        for(int x = 0; x < cardAmount+1;x++){
            System.out.print(" ------------");
            System.out.print("      ");
        }
        System.out.print("\n");

    }
    
    
    //function is overriding abstract function in person class, card amount printed is determined by the "round" the dealer is on depending on if they need to keep hitting and drawing cards
    //x is used to get values from the arrays as a for loop iterates through the arrays choosing the next number and suit in the list to be used to build the cards
    //if card number is equal to 10 cards need to print slightly differently to have cards inline with eachother 
    @Override
    public void printCard()
    {

        
        int cardAmount;
        if(dR == 0){
            cardAmount = dR;
            
        }
        else
        {
            cardAmount = dR;
        }

        for(int x = 0; x < cardAmount+1;x++){
            System.out.print(" ------------");
            System.out.print("      ");
        }
        System.out.print("\n");
        

        for(int x = 0; x < cardAmount+1;x++){
            if(dealerCardNum.get(x).equals("10")){
                System.out.print("|" + dealerSuit.get(x) + "         " + dealerCardNum.get(x) + "|");
                System.out.print("     ");
            }
            else{
                System.out.print("|" + dealerSuit.get(x) + "          " + dealerCardNum.get(x) + "|");
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
            if(dealerCardNum.get(x).equals("10")){
                System.out.print("|" + dealerCardNum.get(x) + "         " + dealerSuit.get(x) + "|");
                System.out.print("     ");
            }
            else{
                System.out.print("|" + dealerCardNum.get(x) + "          " + dealerSuit.get(x) + "|");
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
    
    //function to get a new card printed to the screen
    @Override
    public void newCardToScreen()
    {

        int dealerAceCount = 0;

            int card = dR;
            System.out.println("Dealer's cards: ");
            printCard();
            int cardString = getCardValue(dealerCardNum.get(card));
            for (int i = 0; i < dR+2; i++){
                if(dealerCardNum.get(i).equals("A")){
                    dealerAceCount++;
                }
            }
            newTotal = dealerTotalCardValue += cardString;
            if (newTotal > 21 && dealerAceCount > 0){
                while(dealerAceCount > 0 && newTotal > 21){
                    dealerAceCount--;
                    newTotal -= 10;
                }
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

