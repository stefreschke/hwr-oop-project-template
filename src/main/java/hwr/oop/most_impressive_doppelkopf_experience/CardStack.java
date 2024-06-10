package hwr.oop.most_impressive_doppelkopf_experience;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardStack implements Serializable {
  private static final long serialVersionUID = 1L;

  CardGenerator cardGenerator = new CardGenerator();
  List<Card> gameCardStack = cardGenerator.generateAllCards();

  public List<Card> getCardStack() {
    return gameCardStack;
  }

  public List<Card> shuffleCardsStack(List<Card> cardStack) {
    List<Card> shuffledCards = new ArrayList<>(cardStack);
    Collections.shuffle(shuffledCards);
    return shuffledCards;
  }
}
