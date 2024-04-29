package hwr.oop.doppelkopf.group6;

public enum Type {
  NEUN(0),
  ZEHN(10),
  BUBE(2),
  DAME(3),
  KOENIG(4),
  ASS(11);

  private final int value;

  Type(int value) {
    this.value = value;
  }
}
