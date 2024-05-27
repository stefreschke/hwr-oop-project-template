package hwr.oop.doppelkopf.group6;

import java.util.ArrayList;
import java.util.List;

public class Player {
  private final String name;
  private final int order;
  private final List<Card> ownCards;
  private int points;
  private String group;

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

  public String getGroup() {
    return group;
  }

  public Player(String name, int order, int points) {
    this.name = name;
    this.order = order;
    this.points = points;
    this.ownCards = new ArrayList<>();
  }

  public Card playCard(int position) {
    Card chosenCard = this.ownCards.get(position);
    this.ownCards.remove(position);
    return chosenCard;
  }

  public void addPoints(List<Card> cards) {
    for (Card i : cards) {
      this.points = this.points + i.getNumber().getPoints();
    }
  }

  public void addCard(Card card) {
    this.ownCards.add(card);
  }

  public void setGroup(){
    for (Card card : this.ownCards) {
      if (card.getShortcut().equals("KrD")){
        this.group = "Re";
        return;
      }
    }
    this.group = "Kontra";
  }
}
