package hwr.oop.doppelkopf.group6;

import java.util.List;

public class Player {
  private final String name;
  private final int order;
  private final Hand ownCards;
  private int points;
  private PlayerGroup group;

  public Player(String name, int order, int points) {
    this.name = name;
    this.order = order;
    this.points = points;
    this.ownCards = new Hand();
  }

  public String getName() {
    return name;
  }

  public int getOrder() {
    return order;
  }

  public Hand getHand(){
    return ownCards;
  }

  public List<Card> getOwnCards() {
    return ownCards.getAllCards();
  }

  public int getPoints() {
    return points;
  }

  public PlayerGroup getGroup() {
    return group;
  }

  public void setGroup() {
    int countKrD = 0;
    for (Card card : this.getOwnCards()) {
      if (card.getShortcut().equals("KrD")) {
        countKrD ++ ;
      }
    }
    switch (countKrD) {
      case 1:
        this.group = PlayerGroup.RE;
        break;
      case 0:
        this.group = PlayerGroup.KONTRA;
        break;
      default:
        this.group = PlayerGroup.HOCHZEIT;
        break;
    }
  }

  public void setGroup(PlayerGroup group) {
    this.group = group;
  }

  public void addPoints(int points) {
    this.points += points;
  }

  public void resetPoints(){
    this.points = 0;
  }
}
