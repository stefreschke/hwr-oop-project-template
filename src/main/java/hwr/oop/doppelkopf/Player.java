package hwr.oop.doppelkopf;

import java.util.ArrayList;
import java.util.List;

public class Player {
  private final String name;

  public List<Card> getOwnCards() {
    return ownCards;
  }

  public String getName() {
    return name;
  }

  private final List<Card> ownCards;

  public Player(String name) {
    this.name = name;
    this.ownCards = new ArrayList<>();
  }
}
