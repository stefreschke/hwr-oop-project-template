package hwr.oop.most_impressive_doppelkopf_experience;
import java.util.ArrayList;
import java.util.List;

public class DiscardPile {
  List<Card> discardCards = new ArrayList<>();

  public int getIdOfWinner() {
    return idOfWinner;
  }

  int idOfWinner;
  public Card findHighestValue() {
    Card highestTemp = discardCards.getFirst();
    for (int i = 0; i < discardCards.size(); i++) {
      Card currentCard = discardCards.get(i);
      int currentValue = currentCard.getValue();
      if(currentValue > highestTemp.getValue()) {
        highestTemp = currentCard;
        idOfWinner = i;
      }
    }
    discardCards.clear();
  return highestTemp;
  }

  public void discardCard(Card card) {
    discardCards.add(card);
  }
}

