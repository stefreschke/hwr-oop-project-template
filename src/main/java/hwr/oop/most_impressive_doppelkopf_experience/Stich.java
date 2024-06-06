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
}

