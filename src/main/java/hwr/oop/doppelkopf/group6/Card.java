package hwr.oop.doppelkopf.group6;

public class Card {
    private final Color color;
    private final Type number;

    public Card(Color color, Type number) {
        this.color = color;
        this.number = number;
    }

    public Color getColor() {
        return this.color;
    }

    public Type getNumber() {
        return this.number;
    }
}
