package hwr.oop.doppelkopf.group6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateRandomDeck {
  private List<Card> deck;

  public List<Card> shuffleDeck(List<Card> cards) {
    deck = cards;
    final var mutableList = new ArrayList<>(deck);
    Collections.shuffle(mutableList);
    return mutableList;
  }
}
