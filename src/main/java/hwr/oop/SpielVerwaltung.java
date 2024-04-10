package hwr.oop;

import java.util.List;

public class SpielVerwaltung {


    public static void main(String[] args){

    }

    public SpielVerwaltung(List<SpielendeSpieler> spielers) {
        KartenStarpel starpel = new KartenStarpel();
        starpel.kartenRandomVerteilenZweiBleibenUebrig(starpel.neuerKartenStarpel(),spielers);

        starteSpielrunde();
    }

    private void starteSpielrunde() {
        boolean spielleuft = true;
        while (spielleuft) {

        }

    }

}
