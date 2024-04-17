package hwr.oop;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public final String name;
    public List<Card> ownCards;

    public Player(String name) {
        this.name = name;
        this.ownCards = new ArrayList<>();
    }
}
