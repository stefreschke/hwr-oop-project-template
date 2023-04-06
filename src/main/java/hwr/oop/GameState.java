package hwr.oop;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class GameState {
    ArrayList<Player> players = new ArrayList<>();

    int currentFrame = -1;
    int temporaryFrame = -1;
    // This variable is used for a single player advancing in the case that a Player is added after the game has
    // already started.
    Instant gameStartTime;

    public GameState() {
        gameStartTime = Instant.now();
    }

    public void addPlayer(String name) {
        players.add(new Player(name));
    }
}
