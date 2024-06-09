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

  public boolean getShuffled(){
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
          Card newCard;
          if (i == Color.KARO
              || j == Type.BUBE
              || j == Type.DAME
              || ((i.getShortcut() + j.getShortcut()).equals("H10"))) {
            newCard = new Card(i, j, Group.TRUMPF, i.getShortcut() + j.getShortcut());
          } else {
            switch (i) {
              case HERZ -> newCard = new Card(i, j, Group.HERZ, i.getShortcut() + j.getShortcut());
              case PIK -> newCard = new Card(i, j, Group.PIK, i.getShortcut() + j.getShortcut());
              default -> newCard = new Card(i, j, Group.KREUZ, i.getShortcut() + j.getShortcut());
            }
          }
          this.cards.add(newCard);
        }
      }
    }
    this.shuffled = false;
  }

  public void shuffleDeck() {
    var shuffleCards = this.getCards();
    Collections.shuffle(shuffleCards);
    this.cards = shuffleCards;
    this.shuffled = true;
  }


  public void dealCards(List<Player> players) {
    for (Player player : players) {
      for (int i = 0; i < 12; i++){
        player.getHand().addCard(this.cards.getFirst());
        this.cards.removeFirst();
      }
    }
  }
}
