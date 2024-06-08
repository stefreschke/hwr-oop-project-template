package hwr.oop.doppelkopf.group6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
  private final String name;
  private final int order;
  private final List<Card> ownCards;
  private int points;
  private String group;

  public Player(String name, int order, int points) {
    this.name = name;
    this.order = order;
    this.points = points;
    this.ownCards = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public int getOrder() {
    return order;
  }

  public List<Card> getOwnCards() {
    return ownCards;
  }

  public int getPoints() {
    return points;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup() {
    int countKrD = 0;
    for (Card card : this.ownCards) {
      if (card.getShortcut().equals("KrD")) {
        countKrD ++ ;
      }
    }
    switch (countKrD) {
      case 1:
        this.group = "Re";
        break;
      case 0:
        this.group = "Kontra";
        break;
      default:
        this.group = "Hochzeit";
        break;
    }
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public void removeCard (int i){
    this.ownCards.remove(i);
  }

  public void playFirstCard(int position, Stich stich) {
    stich.addCard(this.ownCards.get(position));
    this.ownCards.remove(position);
  }

  public void playCard(int position, Stich stich) {
    while (!stich.checkCard(this.ownCards,this.ownCards.get(position))) {
      position++;
    }
    stich.addCard(this.ownCards.get(position));
    this.ownCards.remove(position);
  }

  public void addCard(Card... cardsToAdd) {
    List<Card> cards = Arrays.asList(cardsToAdd);
    this.ownCards.addAll(cards);
  }

  public void addPoints(int points) {
    this.points += points;
  }

  public void resetPoints(){
    this.points = 0;
  }

  public int countPlayersTrumpCards() {
    List<Card> playersTrumpCards = this.ownCards.stream().filter(card -> card.getGroup().equals(Group.TRUMPF)).toList();

    return playersTrumpCards.size();
  }
}
