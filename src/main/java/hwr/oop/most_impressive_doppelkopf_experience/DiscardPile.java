package hwr.oop.most_impressive_doppelkopf_experience;

import java.util.ArrayList;
import java.util.List;

public class DiscardPile {
  List<Card> discardCards = new ArrayList<>();

  public void discardCard(Card card) {
    discardCards.add(card);
  }
}
