package hwr.oop.most_impressive_doppelkopf_experience;

import hwr.oop.most_impressive_doppelkopf_experience.enums.CardColours;
import hwr.oop.most_impressive_doppelkopf_experience.enums.CardSymbols;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

class StichTest {
  @Test
  void getPositionOfHighestCardTest() {
    var discardPile = new Stich();


    var h10 = new Card(CardSymbols.TEN, CardColours.TRUMP, 100, "H10", 10);
    var s9b = new Card(CardSymbols.NINE, CardColours.SPADES, 0, "S9", 0);
    var cab = new Card(CardSymbols.ACE, CardColours.CLUBS, 4, "CA", 5);

    discardPile.setDiscardCards(List.of(h10, h10 , s9b, cab));

    var highestCard = discardPile.getPositionOfHighestCardInDiscardPile();

    assertThat(highestCard).isZero();
  }

  @Test
  void testGetDiscardPile() {
    var discardPile = new Stich();

    var d9 = new Card(CardSymbols.NINE, CardColours.TRUMP, 10, "D9", 0);
    var h10 = new Card(CardSymbols.TEN, CardColours.TRUMP, 100, "H10", 10);
    var s9b = new Card(CardSymbols.NINE, CardColours.SPADES, 0, "S9", 0);
    var cab = new Card(CardSymbols.ACE, CardColours.CLUBS, 4, "CA", 5);

    discardPile.setDiscardCards(List.of(h10, d9, s9b, cab));
    List<Card> discardPile1 = discardPile.getDiscardPile();

    assertThat(discardPile1).hasSize(4);
  }

  @Test
  void getPositionOfHighestCardTest_HighestInMiddle() {
    Stich discardPile = new Stich();

    Card h10 = new Card(CardSymbols.TEN, CardColours.TRUMP, 100, "H10", 10);
    Card s9b = new Card(CardSymbols.NINE, CardColours.SPADES, 0, "S9", 0);
    Card cab = new Card(CardSymbols.ACE, CardColours.CLUBS, 4, "CA", 5);
    Card h12 = new Card(CardSymbols.QUEEN, CardColours.TRUMP, 200, "H12", 12); // Höchste Karte in der Mitte

    discardPile.setDiscardCards(List.of(h10, h12 , s9b, cab));

    int highestCard = discardPile.getPositionOfHighestCardInDiscardPile();

    assertThat(highestCard).isEqualTo(1);
  }

  @Test
  void getPositionOfHighestCardTest_HighestAtEnd() {
    Stich discardPile = new Stich();

    Card h10 = new Card(CardSymbols.TEN, CardColours.TRUMP, 100, "H10", 10);
    Card s9b = new Card(CardSymbols.NINE, CardColours.SPADES, 0, "S9", 0);
    Card cab = new Card(CardSymbols.ACE, CardColours.CLUBS, 4, "CA", 5);
    Card h12 = new Card(CardSymbols.QUEEN, CardColours.TRUMP, 200, "H12", 12); // Höchste Karte am Ende

    discardPile.setDiscardCards(List.of(h10, cab, s9b, h12));

    int highestCard = discardPile.getPositionOfHighestCardInDiscardPile();

    assertThat(highestCard).isEqualTo(3);
  }
}


