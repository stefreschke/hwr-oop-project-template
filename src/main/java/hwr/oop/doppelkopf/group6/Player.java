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

  public void resetScore(){
    this.points = 0;
  }

  public void removeCard (int i){
    this.ownCards.remove(i);
  }

  public Player(String name, int order, int points) {
    this.name = name;
    this.order = order;
    this.points = points;
    this.ownCards = new ArrayList<>();
  }

  public Card playFirstCard(int position) {
    Card chosenCard = this.ownCards.get(position);
    this.ownCards.remove(position);
    checkCard(chosenCard);
    return chosenCard;
  }

  public Card playCard(int position, Color firstPlayedColor) {
    while (!checkCard(firstPlayedColor, this.ownCards.get(position))) {
      position++;
    }
    Card chosenCard = this.ownCards.get(position);
    this.ownCards.remove(position);
    return chosenCard;
  }

  public Card playCard(int position) {
    while (!checkCard(this.ownCards.get(position))) {
      position++;
    }
    Card chosenCard = this.ownCards.get(position);
    this.ownCards.remove(position);
    return chosenCard;
  }

  public void addPoints(List<Card> cards){
    for (Card card : cards) {
      this.points = this.points + card.getNumber().getPoints();
    }
  }

  public void addCard(Card card) {
    this.ownCards.add(card);
  }

  public void addCards(List<Card> cards) {
    for (Card card : cards) {
      this.ownCards.add(card);
    }
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
        case 2:
          this.group = "Hochzeit";
          break;
      default:
        this.group = "Kontra";
        break;
    }
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public boolean checkCard(Color firstPlayedColor, Card playedCard) {
    if (playedCard.getColor() == firstPlayedColor) {
      return true;
    } else {
      for (Card i : this.ownCards) {
        if (i.getColor() == firstPlayedColor) {
          return false;
        }
      }
    }
    return true;
  }

  public boolean checkCard(Card playedCard) {
    if (playedCard.isTrump()) {
      return true;
    } else {
      for (Card i : this.ownCards) {
        if (i.isTrump()) {
          return false;
        }
      }
    }
    return true;
  }
}
