package hwr.oop.doppelkopf.group6;

public class Card {
  private final Color color;
  private final Type number;
  private final String shortcut;

  public Card(Color color, Type number, String shortcut) {
    this.color = color;
    this.number = number;
    this.shortcut = shortcut;
  }

  public String getShortcut() {
    return shortcut;
  }

  public Color getColor() {
    return this.color;
  }

  public Type getNumber() {
    return this.number;
  }
}
