package hwr.oop.doppelkopf.group6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
  private List<Card> cards;
  private boolean shuffled;

  public Deck() {
    this.cards = new ArrayList<>();
  }

  public List<Card> getCards() {
    return cards;
  }

  public boolean getShuffled() {
    return this.shuffled;
  }

  public boolean hasCard(Color color, Type number) {
    for (Card i : this.cards) {
      if (i.getColor() == color && i.getNumber() == number) {
        return true;
      }
    }
    return false;
  }

  public void addCard(Card card) {
    this.cards.add(card);
  }

  public void initializeCards() {
    for (int k = 0; k < 2; k++) {
      for (Color i : Color.values()) {
        for (Type j : Type.values()) {
          createCard(i, j);
        }
      }
    }
    this.shuffled = false;
  }

  private void createCard(Color color, Type number) {
    Card newCard;
    if (color == Color.KARO
        || number == Type.BUBE
        || number == Type.DAME
        || ((color.getShortcut() + number.getShortcut()).equals("H10"))) {
      newCard = new Card(color, number, Group.TRUMPF, color.getShortcut() + number.getShortcut());
    } else {
      switch (color) {
        case HERZ ->
            newCard =
                new Card(color, number, Group.HERZ, color.getShortcut() + number.getShortcut());
        case PIK ->
            newCard =
                new Card(color, number, Group.PIK, color.getShortcut() + number.getShortcut());
        default ->
            newCard =
                new Card(color, number, Group.KREUZ, color.getShortcut() + number.getShortcut());
      }
    }
    this.cards.add(newCard);
  }

  public void shuffleDeck() {
    var shuffleCards = this.getCards();
    Collections.shuffle(shuffleCards);
    this.cards = shuffleCards;
    this.shuffled = true;
  }

  public void dealCards(List<Player> players) {
    for (Player player : players) {
      for (int i = 0; i < 12; i++) {
        player.getHand().addCard(this.cards.getFirst());
        this.cards.removeFirst();
      }
    }
  }
}
