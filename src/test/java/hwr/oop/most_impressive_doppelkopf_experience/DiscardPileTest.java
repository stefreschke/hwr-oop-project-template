package hwr.oop.most_impressive_doppelkopf_experience;

import hwr.oop.most_impressive_doppelkopf_experience.enums.CardColours;
import hwr.oop.most_impressive_doppelkopf_experience.enums.CardSymbols;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

public class DiscardPileTest {
  @Test
  void findHighestValueTest() {
    var discardPile = new DiscardPile();

    var d9 = new Card(CardSymbols.NINE, CardColours.TRUMP, 10, "D9", 0);
    var h10 = new Card(CardSymbols.TEN, CardColours.TRUMP, 100, "H10", 10);
    var s9b = new Card(CardSymbols.NINE, CardColours.SPADES, 0, "S9", 0);
    var cab = new Card(CardSymbols.ACE, CardColours.CLUBS, 4, "CA", 11);

    discardPile.setDiscardCards(List.of(d9, h10, s9b, cab));

    var highestCard = discardPile.findHighestValue();

    assertThat(highestCard).isEqualTo(h10);
  }
}
