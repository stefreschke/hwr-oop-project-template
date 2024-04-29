package hwr.oop.doppelkopf.group6;

import java.util.ArrayList;
import java.util.List;

public class Player {
  private final String name;
  private final int order;
  private final List<Card> ownCards;
  private final int points;

  public List<Card> getOwnCards() {
    return ownCards;
  }

  public String getName() {
    return name;
  }

  public int getOrder() {
    return order;
  }

  public int getPoints() {
    return points;
  }

  public Player(String name, int order, int points) {
    this.name = name;
    this.order = order;
    this.points = points;
    this.ownCards = new ArrayList<>();
  }
}
