package hwr.oop;

import java.util.ArrayList;
import java.util.List;

public class KartenStarpel {

  KartenStarpel() {}

  KartenStarpel(List<Spieler> spielers) {}

  public List<Karte> neuerKartenStarpel() {
    // TODO warum Array List ?
    List<Karte> karten = new ArrayList<>();
    List<Wert> Werte = Wert.getZahlen();
    List<Farbe> Farben = Farbe.getFarben();
    for (Wert wert : Werte) {
      for (Farbe farbe : Farben) {
        karten.add(new Karte(farbe, wert));
      }
    }
    return karten;
  }

  public void kartenRandomVerteilenZweiBleibenUebrig(List<Karte> karten,List<SpielendeSpieler> SpielendeSpieler){
      while (karten.size()>2) {
          int randomZahl = (int) (Math.random()*(SpielendeSpieler.size()));
          SpielendeSpieler.get(randomZahl).handKarten.add(karten.get(randomZahl));
      }
  }
}

