package hwr.oop.doppelkopf.group6;

public enum Type {
  NEUN(0, 0),
  ZEHN(10, 1),
  BUBE(2, 4),
  DAME(3, 5),
  KOENIG(4, 2),
  ASS(11, 3);

  private final int value;
  private final int strength;

  Type(int value, int strength) {
    this.value = value;
    this.strength = strength;
  }
}
