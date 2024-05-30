//package BlackJackGame;
//
//import java.util.Scanner;
//
//public class Game //the game class handles most of the logic behind player turns and dealer turns as well as deciding who wins each round and if money should be paid out
//{
//    //references to other classes
//
//    private final Player player;
//    private final Dealer dealer;
//    private final userLogin userLogin;
//    private final Betting betting;
//
//    //game constructor 
//    public Game() 
//    {
//        player = new Player();
//        dealer = new Dealer();
//        userLogin = new userLogin();
//        betting = new Betting(userLogin);
//
//
//
//    }
//
//    //the run function pools all the main functions together to run the game of blackjack using mostly functions from inside the Game class 
//    public void run() 
//    {
//
//
//        Start();
//        String userName = userLogin.login(); //this method returns username
//        betting.placeBet(userName);
//        System.out.println("\n\nDealer's cards: ");
//        dealer.printBlank();
//        playerTurn();
//        dealerTurn();
//        makeSpace();
//        int gameOutcome = winOrLose();
//
//        //payout based on the game outcome
//        switch (gameOutcome) 
//        {
//            case 1: //player wins
//                betting.payout(userName, true);
//                break;
//            case -1: //dealer wins
//                betting.payout(userName, false);
//                break;
//            case 0: //tie
//                betting.refund(userName);
//                break;
//            default: 
//                System.out.println("Unexpected game outcome.");
//                break;
//        }
//
//
//    }
//
//    //start function is an introduction to the program and asks the user if the want to start the program or not
//    private void Start() 
//    {
//    //BlackJack Sign Created by ChatGPT
//    System.out.println("  ____  _            _        _            _    \n" +
//" |  _ \\| |          | |      | |          | |   \n" +
//" | |_) | | __ _  ___| | __   | | __ _  ___| | __\n" +
//" |  _ <| |/ _` |/ __| |/ /   | |/ _` |/ __| |/ /\n" +
//" | |_) | | (_| | (__|   < |__| | (_| | (__|   < \n" +
//" |____/|_|\\__,_|\\___|_|\\_\\____/ \\__,_|\\___|_|\\_\\\n" +
//"                                                \n" +
//"                                                ");
//
//    System.out.println("Welcome to BlackJack!\n");
//    System.out.println("Press Y to start or N to quit");
//    System.out.println("WARNING: pressing N at any input will close BlackJack");
//    Scanner scan = new Scanner(System.in);
//    String answer = scan.nextLine().toUpperCase(); //convert to upper case
//
//    while (!answer.equals("Y") && !answer.equals("N")) //if the answer is not y or n keep prompting the user to input a valid response to move on
//    {
//        System.out.println("Incorrect Input, please put Y or N");
//        answer = scan.nextLine().toUpperCase(); //update answer with new input
//    }
//
//    if (answer.equals("Y")) //y will start the game of blackjack and move onto the next function that is in the run function
//    {
//        System.out.println("\nStarting BlackJack!");
//        // Proceed with starting the game...
//    } else if (answer.equals("N")) //n will stop the game and compiler 
//    {
//        System.out.println("Stopping BlackJack");
//        System.exit(0);
//    }
//}
//
//        private void playerTurn() {
//        player.newCardToScreen();
//        Scanner scan = new Scanner(System.in);
//
//        while (player.getNewTotal() < 21) {
//            System.out.println("Hit(H) or Stand(S)");
//            String hitOrStand = scan.nextLine();
//
//            switch (hitOrStand.toUpperCase()) {
//                case "H":
//                    hit();
//                    break;
//                case "S":
//                    stand();
//                    return;
//                case "N":
//                    System.out.println("Closing BlackJack");
//                    System.exit(0);
//                    break;
//                default:
//                    makeSpace();
//                    System.out.println("Invalid Input. Please enter H or S.");
//                    break;
//            }
//        }
//
//        if (player.getNewTotal() == 21) {
//            System.out.println("You Have BlackJack!");
//        }
//    }
//
//    public void hit() {
//        System.out.println("You chose to Hit");
//        player.pR++;
//        player.newCardToScreen();
//    }
//
//    public void stand() {
//        System.out.println("You chose to Stand");
//        System.out.println("Total Player Value: " + player.getNewTotal());
//    }
//
//    //the player turn controls if a player will hit or stand by taking in input of h or s 
////    @SuppressWarnings("ConvertToStringSwitch")
////    private void playerTurn() 
////    {
////        player.newCardToScreen();
////
////        Scanner scan = new Scanner(System.in);
////
////        while (player.getNewTotal() < 21) //if the total value of your cards together is less than 21, the user will be asked if they want to hit or stand again until they stand or go over 21
////        {
////            System.out.println("Hit(H) or Stand(S)");
////            String hitOrStand = scan.nextLine();
////
////            if (hitOrStand.toUpperCase().equals("H")) //if player hits increase the "round" number by 1 so the player class knows to get the next card in the array and then print to screen
////            {
////                System.out.println("You chose to Hit");
////                player.pR++;
////                player.newCardToScreen();
////
////            } 
////            else if (hitOrStand.toUpperCase().equals("S")) //if player stands loop is broken 
////            {
////                System.out.println("You chose to Stand");
////
////                System.out.println("Total Player Value:" + player.getNewTotal());
////
////                break; //exit the loop if the player chooses to stand
////
////            } 
////            else if(hitOrStand.toUpperCase().equals("N"))
////            {
////                System.out.println("Closing BlackJack");
////                System.exit(0);
////            }
////            else
////            {
////
////                makeSpace(); //clearing the CUI of clutter when there is an invalid input
////                System.out.println("Invalid Input. Please enter H or S.");
////            }
////        }
////        if (player.getNewTotal() == 21) {
////            System.out.println("You Have BlackJack!");
////        }
////
////    }
//
//    public void dealerTurn() {
//        dealer.dR--;
//
//        try {
//
//            while (dealer.getNewTotal() < 17) {
//                //Draw a new card for the dealer and display the updated total card value
//                dealer.dR++;
//                dealer.newCardToScreen();
//
//                //Display the current total
//                //Check for bust
//                if (dealer.getNewTotal() > 21) {
//                    System.out.println("Dealer busts!");
//                    return;
//                }
//                Thread.sleep(1250); //Wait 1.25s between drawing dealer cards 
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        //If dealer's total card value is 21 right after drawing the first card
//        if (dealer.getNewTotal() == 21) {
//            System.out.println("Dealer has Blackjack!");
//            return;
//        }
//
//        //Dealer stands
//        System.out.println("Dealer stops at: " + dealer.getNewTotal());
//
//        //Loop until the dealer's total card value is 17 or higher
//    }
//
//    public int winOrLose() {
//        int playerTotal = player.getNewTotal(); // Assuming getNewTotal() gives the accurate total
//        int dealerTotal = dealer.getNewTotal();
//
//        //Player busts
//        if (playerTotal > 21) {
//            System.out.println("You bust at " + playerTotal + ". Dealer wins.");
//            return -1; //Dealer wins
//        }
//
//        //Dealer busts
//        if (dealerTotal > 21) {
//            System.out.println("Dealer busts at " + dealerTotal + ". You win!");
//            return 1; //Player wins
//        }
//
//        //Tie
//        if (playerTotal == dealerTotal) {
//            System.out.println("It's a tie at " + playerTotal + ".");
//            return 0; //Tie
//        }
//
//        //Player has higher score
//        if (playerTotal > dealerTotal) {
//            System.out.println("You win with a total of " + playerTotal + " against dealer's " + dealerTotal);
//            return 1; //Player wins
//        }
//
//        //Remaining case: Dealer has a higher score
//        System.out.println("Dealer wins with a total of " + dealerTotal + " against your " + playerTotal);
//        return -1; //Dealer wins
//    }
//
//    public void makeSpace() { //Function that clears and reprints the current decks of cards to reduce CUI clutter, increasing usability
//        for (int y = 0; y < 20; y++) {
//            System.out.println("");
//
//        }
//        if (dealer.dR == 0) {
//            System.out.println("Dealer's Cards");
//            dealer.printBlank();
//            System.out.println("Player's Cards");
//            player.printCard();
//            System.out.println("Total Player Card Value: " + player.getNewTotal());
//
//        } else {
//            System.out.println("Dealer's Cards");
//            dealer.printCard();
//            System.out.println("Player's Cards");
//            player.printCard();
//            System.out.println("Total Player Card Value: " + player.getNewTotal());
//
//
//
//        }
//
//    }
//
//}
