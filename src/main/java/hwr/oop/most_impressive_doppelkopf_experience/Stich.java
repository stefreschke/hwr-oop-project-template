package hwr.oop.most_impressive_doppelkopf_experience;
import java.util.ArrayList;
import java.util.List;

public class Stich {
  public void setDiscardCards(List<Card> discardCards) {
    this.discardCards = discardCards;
  }

  List<Card> discardCards = new ArrayList<>();

  public List<Card> getDiscardPile() {
    return this.discardCards;
  }

  public void discardCard(Card card) {
    discardCards.add(card);
  }

  public int getPositionOfHighestCardInDiscardPile() {
    Card highestTemp = discardCards.getFirst();
    int tempValue = highestTemp.getValue();
    int positionOfHighestCard = 0;

    for (int i = 0; i < discardCards.size(); i++) {

      if (discardCards.get(i).getValue() > tempValue) {
        highestTemp = discardCards.get(i);
        tempValue = highestTemp.getValue();
        positionOfHighestCard = i;
      }
    }

    return positionOfHighestCard;
  }
}

