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
    Card HighestTemp = discardCards.getFirst();
    int TempValue = HighestTemp.getValue();
    int PositionOfHighestCard = 0;

    for (int i = 0; i < discardCards.size(); i++) {

      if (discardCards.get(i).getValue() > TempValue) {
        HighestTemp = discardCards.get(i);
        TempValue = HighestTemp.getValue();
        PositionOfHighestCard = i;
      }
    }

    return PositionOfHighestCard;
  }
}

