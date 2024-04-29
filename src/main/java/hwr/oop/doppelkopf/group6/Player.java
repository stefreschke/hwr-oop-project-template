package hwr.oop.doppelkopf.group6;

import java.util.ArrayList;
import java.util.List;

public class Player {
  private final String name;
  private final List<Card> ownCards;

  public List<Card> getOwnCards() {
    return ownCards;
  }

  public String getName() {
    return name;
  }

  public Player(String name) {
    this.name = name;
    this.ownCards = new ArrayList<>();
  }
}
