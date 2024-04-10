package hwr.oop;

public class Card {
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    String symbol;
    String colour;
    int value; //Strenght of the card
    int worth; //Worth of the Card
    String name;

    enum colour {
        Clubs,
        Ceart,
        Spades,
        Trump
    }

    public Card(String symbol, String colour, int value, String name, int worth) {
        this.symbol = symbol;
        this.colour = colour;
        this.value = value;
        this.name = name;
        this.worth = worth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
