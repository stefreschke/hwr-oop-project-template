package hwr.oop.doppelkopf.group6;

import java.util.ArrayList;
import java.util.List;

public class DoppelkopfGame {
  public final Player player1 = new Player("Spieler1");
  public final Player player2 = new Player("Spieler2");
  public final Player player3 = new Player("Spieler3");
  public final Player player4 = new Player("Spieler4");

  public DoppelkopfGame() {
    initializeCards();
  }

  public int oneRound() {
    List<Card> roundCards = new ArrayList<>();
    roundCards.add(player1.showAndChooseCard());
    roundCards.add(player2.showAndChooseCard());
    roundCards.add(player3.showAndChooseCard());
    roundCards.add(player4.showAndChooseCard());
    Color playedColor = roundCards.getFirst().getColor();
    int winnerCard = findHighestCard(playedColor, roundCards);
    System.out.println("Winnercard-Number" + winnerCard);
    switch (winnerCard) {
      case 0:
        player1.addPoints(roundCards);
        break;
      case 1:
        player2.addPoints(roundCards);
        break;
      case 2:
        player3.addPoints(roundCards);
        break;
      case 3:
        player4.addPoints(roundCards);
        break;
      default:
        break;
    }
    return winnerCard;
  }

  public List<Card> initializeCards() {
    List<Card> cards = new ArrayList<>();

    for (int k = 0; k < 2; k++) {
      for (Color i : Color.values()) {
        for (Type j : Type.values()) {
          Card newCard = new Card(i, j);
          cards.add(newCard);
        }
      }
    }
    return cards;
  }

  public boolean hasCard(List<Card> cards, Color color, Type number) {
    for (Card i : cards) {
      if (i.getColor() == color && i.getNumber() == number) {
        return true;
      }
    }
    return false;
  }

  public void dealCards(List<Card> cards) {
    for (int i = 0; i < 12; i++) {
      player1.getOwnCards().add(cards.getFirst());
      cards.remove(cards.getFirst());
      player2.getOwnCards().add(cards.getFirst());
      cards.remove(cards.getFirst());
      player3.getOwnCards().add(cards.getFirst());
      cards.remove(cards.getFirst());
      player4.getOwnCards().add(cards.getFirst());
      cards.remove(cards.getFirst());
    }
  }

  public int findHighestCard(Color playedColor, List<Card> cards) {
    for (int i = 1; i < 4; i++) {
      if (cards.get(i).getColor() != playedColor) {
        cards.remove(cards.get(i));
      }
    }
    int highestCard = 0;
    for (int i = 1; i < 4; i++) {
      if (cards.get(i).getNumber().getStrenght()
          >= cards.get(highestCard).getNumber().getStrenght()) {
        highestCard = i;
      }
    }
    return highestCard;
  }
}
