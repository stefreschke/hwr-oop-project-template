package hwr.oop;

import java.util.Collections;
import java.util.List;

public class CreateRandomDeck {

  public List<Card> shuffleDeck() {
    DoppelkopfGame cardList = new DoppelkopfGame();
    List<Card> shuffledCards = cardList.initializeCards();
    Collections.shuffle(shuffledCards);
    return shuffledCards;
  }
}
