package hwr.oop.doppelkopf.group6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hand {
  private final List<Card> allCards;
  private final List<String> trumpCards = new ArrayList<>();
  private final List<String> herzCards = new ArrayList<>();
  private final List<String> pikCards = new ArrayList<>();
  private final List<String> kreuzCards = new ArrayList<>();

  public Hand() {
    this.allCards = new ArrayList<>();
  }

  public List<Card> getAllCards() {
    return allCards;
  }

  public List<String> getTrumpCards() {
    return new ArrayList<>(trumpCards);
  }

  public List<String> getHerzCards() {
    return new ArrayList<>(herzCards);
  }

  public List<String> getPikCards() {
    return new ArrayList<>(kreuzCards);
  }

  public List<String> getKreuzCards() {
    return new ArrayList<>(kreuzCards);
  }

  public void removeCard(int i) {
    this.allCards.remove(i);
  }

  public void removeCard(String i) {
    for (Card card : allCards) {
      if (card.getShortcut().equals(i)) {
        this.allCards.remove(card);
        break;
      }
    }
  }

  public void addCard(Card... cardsToAdd) {
    List<Card> cards = Arrays.asList(cardsToAdd);
    this.allCards.addAll(cards);
  }

  public int countPlayersTrumpCards() {
    List<Card> playersTrumpCards =
        this.allCards.stream().filter(card -> card.getGroup().equals(Group.TRUMPF)).toList();

    return playersTrumpCards.size();
  }

  public void sortCards() {
    for (Card i : allCards) {
      if (i.getGroup().equals(Group.TRUMPF)) {
        trumpCards.add(i.getShortcut());
      } else if (i.getColor() == Color.HERZ) {
        herzCards.add(i.getShortcut());
      } else if (i.getColor() == Color.PIK) {
        pikCards.add(i.getShortcut());
      } else if (i.getColor() == Color.KREUZ) {
        kreuzCards.add(i.getShortcut());
      }
    }
  }

  public void playFirstCard(int position, Stich stich) {
    stich.addCard(this.allCards.get(position));
    removeCard(position);
  }

  public void playFirstCard(String shortcut, Stich stich) {
    for (Card card : this.getAllCards()) {
      if (card.getShortcut().equals(shortcut)) {
        stich.addCard(card);
        removeCard(shortcut);
        break;
      }
    }
  }

  public void playCard(int position, Stich stich) {
    while (!stich.checkCard(this.allCards, this.allCards.get(position))) {
      position++;
    }
    stich.addCard(this.allCards.get(position));
    removeCard(position);
  }

  public void playCard(String shortcut, Stich stich) {
    for (Card card : this.allCards) {
      if (card.getShortcut().equals(shortcut) && stich.checkCard(this.allCards, card)) {
        stich.addCard(card);
        removeCard(card.getShortcut());
        break;
      }
    }
  }
}
