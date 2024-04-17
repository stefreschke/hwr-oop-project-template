package hwr.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StartPlayerTest {
    @Test
     void testSelectPlayer() {
        ArrayList<String> players = new ArrayList<>();
        players.add("Spieler 1");
        players.add("Spieler 2");
        players.add("Spieler 3");
        players.add("Spieler 4");

        Set<String> selectedPlayers = new HashSet<>();

        for (int i = 0; i < 100; i++) {
            String selectedPlayer = StartPlayer.selectRandomPlayer(players);
            selectedPlayers.add(selectedPlayer);
        }

        assertEquals(4, selectedPlayers.size());
    }
}
