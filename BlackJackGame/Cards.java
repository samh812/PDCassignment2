package BlackJackGame;

import java.util.Random;

/**
 *
 * @author sebas
 */
public class Cards {

    String card;
    String suit;

    //create a suit to be used on the card at random
    public String generateSuit() {
        Random random = new Random();
        int randomSuitNum = random.nextInt(4);

        switch (randomSuitNum) {
            case 0:
                return "H";

            case 1:
                return "D";

            case 2:
                return "C";

            case 3:
                return "S";

            default:
                return "No Suit";

        }

    }


    public enum CardNumber { //enum to store the values of the cards
        A(11),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        J(10),
        Q(10),
        K(10);

        private final int value;

        CardNumber(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public String generateCardNum() { //generating the card numbers
        Random random = new Random();
        int randomCardNum = random.nextInt(13) + 1;

        switch (randomCardNum) { //returning the name of the enum constant for name cards
            case 1:
                return CardNumber.A.name();
            case 11:
                return CardNumber.J.name();
            case 12:
                return CardNumber.Q.name();
            case 13:
                return CardNumber.K.name();
            default:
                return Integer.toString(randomCardNum);
        }
    }

}