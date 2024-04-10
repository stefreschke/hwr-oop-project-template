package hwr.oop;

public class Spieler {
    private String name; //Muss unic sein zur identification
    private int gewonneneRunden;
    private int gespieleteRunden;

    public Spieler(String name) {
        this.name = name;
        this.gewonneneRunden = 0;
        this.gespieleteRunden = 0;
    }

    public String getName() {
        return name;
    }
}
