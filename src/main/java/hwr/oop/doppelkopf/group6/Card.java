package hwr.oop.doppelkopf.group6;

public class Card {
    private final Color color;
    private final Type number;
    private final boolean trump;

    public Card(Color color, Type number, boolean trump) {
        this.color = color;
        this.number = number;
        this.trump = trump;
    }

    public Color getColor() {
        return this.color;
    }

    public Type getNumber() {
        return this.number;
    }

    public boolean isTrump() {return this.trump;}
}
