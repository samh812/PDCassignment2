package BlackJackGame;

/**
 *
 * @author sebas
 */
public abstract class Person extends Cards 
{

    //paramenters for building the visual representation of the playing card
    int cardNum = 1;
    int cardHeight = 5;
    int pR = 0; //counter for the round the player is on and how many cards should be printed to the screen from the array
    int dR = 0; //counter for round dealer is on and how many cards shoud  be printed to screen from array

    //createCard method is an abstract method to be overriden by the player & dealer class as they print/draw cards differently
    public abstract void printCard();
    
    //abstract method to put the card on the screen
    public abstract void newCardToScreen();
}

