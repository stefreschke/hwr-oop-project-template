package hwr.oop.doppelkopf.group6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Stich {
  private final List<Card> cards;
  private int points;
  private int winnerPos;

  public Stich() {
    this.cards = new ArrayList<>();
    this.points = 0;
    this.winnerPos = 0;
  }

  public void addCard(Card... cards) {
    List<Card> cardList = Arrays.asList(cards);
    this.cards.addAll(cardList);
  }

  public int getWinnerPos() {
    return this.winnerPos;
  }

  public int getPoints() {
    return this.points;
  }

  public void setPoints() {
    for (Card card : this.cards) {
      this.points += card.getNumber().getPoints();
    }
  }

  public int findHighestCard() {
    Card highestCard = this.cards.getFirst();
    Card firstCard = highestCard;
    int winnerNumber = 0;
    this.cards.removeFirst();
    for (int i = 0; i < this.cards.size(); i++) {
      if (((this.cards.get(i).getGroup().equals(Group.TRUMPF)) || this.cards.get(i).getColor() == highestCard.getColor())
              && (this.cards.get(i).getNumber().getStrength()
                  > highestCard.getNumber().getStrength())
          || Objects.equals(this.cards.get(i).getShortcut(), "H10")) {
        highestCard = this.cards.get(i);
        winnerNumber = i + 1;
      }
    }
    this.cards.add(firstCard);
    this.winnerPos = winnerNumber;
    setPoints();
    return this.winnerPos + 1;
  }

  public boolean checkCard(List<Card> playersCards, Card playedCard) {
      if (playedCard.getGroup().equals(this.cards.getFirst().getGroup())) {
        return true;
      } else {
        for (Card i : playersCards) {
          if (i.getGroup().equals(this.cards.getFirst().getGroup())) {
            return false;
          }
        }
      }
    return true;
  }
}
