package hwr.oop;

public class Cards {


    public static String[] generateCards() {
        String[] symbols = {"7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] colours = {"H", "D", "C", "S"};

        String[] skatCards = new String[symbols.length * colours.length];
        int index = 0;
        for (String symbol : symbols) {
            for (String colour : colours) {
                String cardName = colour + symbol;
                skatCards[index++] = cardName;
            }
        }
        return skatCards;
    }
}



