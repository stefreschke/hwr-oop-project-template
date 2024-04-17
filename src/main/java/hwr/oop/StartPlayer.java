package hwr.oop;

import java.util.ArrayList;
import java.util.Random;

public class StartPlayer{


    public static String selectRandomPlayer(ArrayList<String> players) {
        Random random = new Random();
        int randomIndex = random.nextInt(players.size());
        return players.get(randomIndex);
    }
    public static void choseStartPlayer(String[] args) {
        ArrayList<String> player = new ArrayList<>();
        player.add("Spieler 1");
        player.add("Spieler 2");
        player.add("Spieler 3");
        player.add("Spieler 4");

        Random random = new Random();
        int randomIndex = random.nextInt(player.size());
        String selectetPlayer = player.get(randomIndex);
    }
}

