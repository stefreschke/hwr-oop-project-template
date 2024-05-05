package hwr.oop.doppelkopf.group6;

public enum Type {
  NEUN(0, 0),
  ZEHN(10, 2),
  BUBE(2, 4),
  DAME(3, 5),
  KOENIG(4, 1),
  ASS(11, 3);

  private final int value;
  private final int strength;

  Type(int value, int strength) {
    this.value = value;
    this.strength = strength;
  }
  public int getStrenght() {
    return strength;
  }
  
  public int getValue(){
    return value;
  }
}
