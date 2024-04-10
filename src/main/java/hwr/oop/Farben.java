package hwr.oop;

import java.util.Arrays;
import java.util.List;

public enum Farben {
  Kreuz(1),
  Herz(2),
  Pik(2),
  Karo(3);

  private int Wert;

  private Farben(int neuerWert) {
    this.Wert = neuerWert;
  }

  public int getWert() {
    return Wert;
  }

  public List<Zahlen> getZahlen() {
    return Arrays.asList(Zahlen.values());
  }
}
