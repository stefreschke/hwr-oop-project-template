package hwr.oop;

import java.util.ArrayList;
import java.util.Random;

public class StartPlayer{

    public static String selectRandomPlayer(ArrayList<String> players) {
        Random random = new Random();
        int randomIndex = random.nextInt(players.size());
        return players.get(randomIndex);
    }
    public static String choseStartPlayer(String[] args) {
        ArrayList<String> player = new ArrayList<>();
        player.add("Spieler 1");
        player.add("Spieler 2");
        player.add("Spieler 3");
        player.add("Spieler 4");

        Random random = new Random();
        int randomIndex = random.nextInt(player.size());
        String selectetPlayer = player.get(randomIndex);
        return selectetPlayer;
    }
    public static int getRandomNumber() {
        Random random = new Random();  // Erstellt ein Random-Objekt
        return random.nextInt(4);  // Generiert eine Zahl zwischen 0 (inklusiv) und 4 (exklusiv)
    }
}

