package hwr.oop;

import java.util.Arrays;
import java.util.List;

public enum Wert {
  Sieben(0,0),
  Acht(0,1),
  Neun(0,2),
  Zehn(10,5),
  Bube(2,7),
  Dame(3,3),
  Könisch(4,4),
  Ass(11,6);

  private int Punkte;
  private int staerke;

  private Wert(int punkte, int staerke) {

  }

  public int getWert() {
    return Punkte;
  }

  public int getStaerke() {return staerke;}

  public static List<hwr.oop.Wert> getZahlen() {
    return Arrays.asList(hwr.oop.Wert.values());
  }
}
