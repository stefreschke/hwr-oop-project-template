package hwr.oop.doppelkopf.group6;

public enum Type {
  NEUN(0, 0, "9"),
  ZEHN(10, 1, "10"),
  BUBE(2, 4, "B"),
  DAME(3, 5, "D"),
  KOENIG(4, 2, "K"),
  ASS(11, 3, "A");

  private final int points;
  private final int strength;
  private final String shortcut;

  Type(int points, int strength, String shortcut) {
    this.points = points;
    this.strength = strength;
    this.shortcut = shortcut;
  }

  public String getShortcut() {
    return shortcut;
  }

  public int getPoints() {
    return points;
  }

  public int getStrength() {
    return strength;
  }
}
