package BlackJackGame;

public class Card 
{
    public enum CardValue 
    {
        TWO(2),
        THREE(3), 
        FOUR(4), 
        FIVE(5), 
        SIX(6), 
        SEVEN(7), 
        EIGHT(8), 
        NINE(9), 
        TEN(10), 
        JACK(10), 
        QUEEN(10), 
        KING(10), 
        ACE(11);

        private final int value;

        CardValue(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        // Based on the index we return the card value so that the card jpgs can be numbered and still have an associated value
        public static CardValue fromIndex(int index) 
        {
            if (index >= 0 && index <= 3) return TWO;
            if (index >= 4 && index <= 7) return THREE;
            if (index >= 8 && index <= 11) return FOUR;
            if (index >= 12 && index <= 15) return FIVE;
            if (index >= 16 && index <= 19) return SIX;
            if (index >= 20 && index <= 23) return SEVEN;
            if (index >= 24 && index <= 27) return EIGHT;
            if (index >= 28 && index <= 31) return NINE;
            if (index >= 32 && index <= 35) return TEN;
            if (index >= 36 && index <= 39) return JACK;
            if (index >= 40 && index <= 43) return QUEEN;
            if (index >= 44 && index <= 47) return KING;
            if (index >= 48 && index <= 51) return ACE;
            throw new IllegalArgumentException("Invalid card index: " + index);
        }
    }

    private final int index; // The index of the card in the deck (0-51)
    private final CardValue value; // The value of the card

    public Card(int index) {
        this.index = index;
        this.value = CardValue.fromIndex(index);
    }

    public int getIndex() {
        return index;
    }

    public int getValue() {
        return value.getValue();
    }

    @Override
    public String toString() {
        return "Card #" + index + " with value " + value.getValue();
    }
}
