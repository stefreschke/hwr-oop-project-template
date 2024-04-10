package hwr.oop;

import java.util.ArrayList;
import java.util.List;

public class KartenStarpel {
    KartenStarpel(List<Spieler> spielers) {
        //TODO warum Array List ?
        List<Karte> karten = new ArrayList<>();
        List<Wert> Werte = Wert.getZahlen();
        List<Farbe> Farben = Farbe.getFarben();
        for(Wert wert : Werte) {
            for(Farbe farbe : Farben) {
                karten.add(new Karte(farbe,wert));

            }
        }
    }
}
