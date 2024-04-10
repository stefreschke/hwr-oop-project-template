package hwr.oop;

import java.util.Arrays;
import java.util.List;

public enum Farbe {
  Kreuz(1),
  Herz(3),
  Pik(2),
  Karo(4);

  private int Wert;

  private Farbe(int neuerWert) {
    this.Wert = neuerWert;
  }

  public int getWert() {
    return Wert;
  }

  public static List<Farbe> getFarben() {
    return Arrays.asList(Farbe.values());
  }
}
