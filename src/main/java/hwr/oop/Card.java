package hwr.oop;

public class Card {
    public String getSymbol() {
        return symbol;
    }

    public CardColours getColour() {
        return colour;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    final String symbol;
    final CardColours colour;
    final int value; //Strength of the card
    final int worth; //Worth of the Card
    final String name;

    public Card(String symbol, CardColours colour, int value, String name, int worth) {
        this.symbol = symbol;
        this.colour = colour;
        this.value = value;
        this.name = name;
        this.worth = worth;
    }
}
