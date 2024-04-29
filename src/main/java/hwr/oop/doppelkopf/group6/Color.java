package hwr.oop.doppelkopf.group6;

public enum Color {
  KREUZ("Kr"),
  PIK("P"),
  HERZ("H"),
  KARO("Ka");

  private final String shortcut;

  Color(String shortcut) {
    this.shortcut = shortcut;
  }

  public String getShortcut() {
    return shortcut;
  }
}
