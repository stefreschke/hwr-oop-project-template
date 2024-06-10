package hwr.oop.most_impressive_doppelkopf_experience;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardStack implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  CardGenerator cardGenerator = new CardGenerator();
  private final List<Card> gameCardStack = cardGenerator.generateAllCards();

  public List<Card> getCardStack() {
    return gameCardStack;
  }

  public List<Card> shuffleCardsStack(List<Card> cardStack) {
    List<Card> shuffledCards = new ArrayList<>(cardStack);
    Collections.shuffle(shuffledCards);
    return shuffledCards;
  }
}
