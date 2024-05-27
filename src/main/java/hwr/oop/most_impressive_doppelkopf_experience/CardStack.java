package hwr.oop.most_impressive_doppelkopf_experience;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardStack {

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
