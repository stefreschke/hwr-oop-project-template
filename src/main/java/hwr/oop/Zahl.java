package hwr.oop;

import java.util.Arrays;
import java.util.List;

public enum Zahl {
  Sieben(0),
  Acht(0),
  Neun(0),
  Zehn(10),
  Bube(2),
  Dame(3),
  Könisch(4),
  Ass(11);

  private int Wert;

  private Zahl(int neuerWert) {
    this.Wert = neuerWert;
  }

  public int getWert() {
    return Wert;
  }

  public static List<Zahl> getZahlen() {
    return Arrays.asList(Zahl.values());
  }
}
