package hwr.oop;

public class Cards {

    public static String[] generateCards() {
        String[] symbols = {"9", "10", "J", "Q", "K", "A"};
        String[] colours = {"H", "D", "C", "S"};// Heart, Diamonds, Clubs, Spades

        String[] generatedCards = new String[symbols.length * colours.length * 2];
        int index = 0;
        for (String symbol : symbols) {
            for (String colour : colours) {
                String cardName = colour + symbol;
                generatedCards[index++] = cardName;
                generatedCards[index++] = cardName;
            }
        }
        return generatedCards;
    }
}




