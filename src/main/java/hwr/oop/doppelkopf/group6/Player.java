package hwr.oop.doppelkopf.group6;

import java.util.ArrayList;
import java.util.List;

public class Player {
  private final String name;
  private final int order;
  private final List<Card> ownCards;
  private int points;

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

  public Card showAndChooseCard(){
    System.out.println(this.name);
    for (Card i : this.ownCards){
      System.out.println(i.getColor() + "   " + i.getNumber());
    }
    int position = 3; //TODO: hier Logik einfügen, die vom User einliest, welche Karte genommen werden soll
    this.ownCards.remove(position);
    return this.ownCards.get(position);
  }


  public void addPoints(List<Card> cards){
    for (Card i : cards){
      this.points = this.points + i.getNumber().getPoints();
    }
    System.out.println(this.name + "   " + this.points);
  }
}
