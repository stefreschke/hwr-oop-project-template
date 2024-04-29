package hwr.oop.doppelkopf.group6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateRandomDeck {

  public List<Card> shuffleDeck(List<Card> cards) {
    List<Card> shuffledCards = new ArrayList<>(cards);
    Collections.shuffle(cards);
    return shuffledCards;
  }
}
