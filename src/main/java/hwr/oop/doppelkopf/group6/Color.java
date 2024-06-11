package hwr.oop.doppelkopf.group6;

public enum Color {
  KARO("Ka", 1),
  HERZ("H", 2),
  PIK("P", 3),
  KREUZ("Kr", 4);

  private final String shortcut;
  private final int strenght;

  Color(String shortcut, int strenght) {
    this.shortcut = shortcut;
    this.strenght = strenght;
  }

  public String getShortcut() {
    return shortcut;
  }

  public int getStrenght() {
    return strenght;
  }
}
