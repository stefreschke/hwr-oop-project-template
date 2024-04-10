package hwr.oop;

import java.util.List;

public class KartenStarpel {
    KartenStarpel(List<Spieler> spielers) {
        List<Karte> karten;
        List<Zahl> Zahlen = Zahl.getZahlen();
        List<Farbe> Farben = Farbe.getFarben();
        for(Zahl zahl : Zahlen) {
            for(Farbe farbe : Farben) {
                //Karte adden
                //karten.add();
            }
        }
    }
}
