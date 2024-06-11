package hwr.oop.most_impressive_doppelkopf_experience;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class HandOutCardsTest {
  @Test
  void handOutCardsTest(){
      var game = new Game();
      game.addPlayer("Mugtaba");
      game.addPlayer("Simon");
      game.addPlayer("Galatea");
      game.addPlayer("Hajer");
      game.handOutCards();
  assertThat(game.stack.cardGenerator.da.getValue()).isBetween(11,101);
  assertThat(game.stack.cardGenerator.dab.getValue()).isBetween(11,101);
  }
  @Test
  void handOutCardsAreValidTestFiveNines() {
    var game = new Game();
    game.addPlayer("Mugtaba");

    game.setStartPlayer(game.getPlayers().getFirst());

    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.ACE, CardColours.TRUMP, 0, "S9", 11)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.TEN, CardColours.TRUMP, 0, "S9", 10)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.KING, CardColours.TRUMP, 0, "S9", 4)));

    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.HEARTS, 0, "H9", 0)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.CLUBS, 0, "C9", 0)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.CLUBS, 0, "C9", 0)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.SPADES, 0, "S9", 0)));

    assertSoftly(
            softly -> {
              assertThat(game.handOutCardsAreValid(game.getPlayers().getFirst())).isTrue();

              game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.SPADES, 0, "S9", 0)));

              assertThat(game.handOutCardsAreValid(game.getPlayers().getFirst())).isFalse();
            });
  }

  @Test
  void handOutCardsAreValidTestFourNinesWithEveryColour() {
    var game = new Game();
    game.addPlayer("Mugtaba");

    game.setStartPlayer(game.getPlayers().getFirst());

    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.ACE, CardColours.TRUMP, 0, "S9", 11)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.TEN, CardColours.TRUMP, 0, "S9", 10)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.KING, CardColours.TRUMP, 0, "S9", 4)));

    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.HEARTS, 0, "H9", 0)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.CLUBS, 0, "C9", 0)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.TRUMP, 0, "C9", 0)));

    assertSoftly(
            softly -> {
              assertThat(game.handOutCardsAreValid(game.getPlayers().getFirst())).isTrue();

              game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.SPADES, 0, "S9", 0)));

              assertThat(game.handOutCardsAreValid(game.getPlayers().getFirst())).isFalse();
            });
  }

  @Test
  void handOutCardsAreValidTestFourNinesAndFourKings() {
    var game = new Game();
    game.addPlayer("Mugtaba");

    game.setStartPlayer(game.getPlayers().getFirst());

    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.ACE, CardColours.TRUMP, 0, "S9", 11)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.TEN, CardColours.TRUMP, 0, "S9", 10)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.TEN, CardColours.TRUMP, 0, "S9", 10)));


    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.HEARTS, 0, "H9", 0)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.CLUBS, 0, "C9", 0)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.CLUBS, 0, "C9", 0)));

    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.KING, CardColours.HEARTS, 0, "H9", 0)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.KING, CardColours.CLUBS, 0, "C9", 0)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.KING, CardColours.CLUBS, 0, "C9", 0)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.KING, CardColours.SPADES, 0, "S9", 0)));

    assertSoftly(
            softly -> {
              assertThat(game.handOutCardsAreValid(game.getPlayers().getFirst())).isTrue();

              game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.SPADES, 0, "S9", 0)));
              assertThat(game.handOutCardsAreValid(game.getPlayers().getFirst())).isFalse();

              game.getPlayers().getFirst().getHand().removeLast();
              game.getPlayers().getFirst().getHand().removeLast();
              assertThat(game.handOutCardsAreValid(game.getPlayers().getFirst())).isTrue();

              game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.KING, CardColours.SPADES, 0, "S9", 0)));
              assertThat(game.handOutCardsAreValid(game.getPlayers().getFirst())).isTrue();

              game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.SPADES, 0, "S9", 0)));
              assertThat(game.handOutCardsAreValid(game.getPlayers().getFirst())).isFalse();
            });
  }

  @Test
  void handOutCardsAreValidTestLessThanTreeTrumpCards() {
    var game = new Game();
    game.addPlayer("Mugtaba");

    game.setStartPlayer(game.getPlayers().getFirst());

    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.TRUMP, 0, "H9", 0)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.KING, CardColours.TRUMP, 0, "C9", 4)));

    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.HEARTS, 0, "C9", 0)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.SPADES, 0, "C9", 0)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.HEARTS, 0, "C9", 0)));

    assertSoftly(
            softly -> {
              assertThat(game.handOutCardsAreValid(game.getPlayers().getFirst())).isFalse();

              game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.TEN, CardColours.TRUMP, 0, "S9", 10)));

              assertThat(game.handOutCardsAreValid(game.getPlayers().getFirst())).isTrue();
            });
  }

  @Test
  void handOutCardsAreValidTestTrumpCardHigherJack() {
    var game = new Game();
    game.addPlayer("Matilda");

    game.setStartPlayer(game.getPlayers().getFirst());

    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.TRUMP, 0, "H9", 0)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.TRUMP, 0, "H9", 0)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.JACK, CardColours.TRUMP, 0, "H9", 2)));

    assertSoftly(
            softly -> {
              assertThat(game.handOutCardsAreValid(game.getPlayers().getFirst())).isFalse();


              game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.QUEEN, CardColours.TRUMP, 0, "S9", 3)));
              assertThat(game.handOutCardsAreValid(game.getPlayers().getFirst())).isTrue();

              game.getPlayers().getFirst().getHand().removeFirst();
              assertThat(game.handOutCardsAreValid(game.getPlayers().getFirst())).isTrue();

              game.getPlayers().getFirst().getHand().removeFirst();
              assertThat(game.handOutCardsAreValid(game.getPlayers().getFirst())).isFalse();
            });
  }

  @Test
  void handOutCardsAreValidTestSevenOrMoreFullCards() {
    var game = new Game();
    game.addPlayer("Mugtaba");

    game.setStartPlayer(game.getPlayers().getFirst());

    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.ACE, CardColours.TRUMP, 0, "H9", 11)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.TEN, CardColours.TRUMP, 0, "C9", 10)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.ACE, CardColours.TRUMP, 0, "H9", 11)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.TEN, CardColours.SPADES, 0, "C9", 10)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.ACE, CardColours.CLUBS, 0, "H9", 11)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.TEN, CardColours.CLUBS, 0, "C9", 10)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.ACE, CardColours.HEARTS, 0, "H9", 11)));

    assertThat(game.handOutCardsAreValid(game.getPlayers().getFirst())).isFalse();
  }

  @Test
  void handOutCardsAreValidTestTrue() {
    var game = new Game();
    game.addPlayer("Mugtaba");

    game.setStartPlayer(game.getPlayers().getFirst());

    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.ACE, CardColours.TRUMP, 0, "H9", 11)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.TEN, CardColours.TRUMP, 0, "C9", 10)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.SPADES, 0, "H9", 0)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.KING, CardColours.SPADES, 0, "H9", 4)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.TRUMP, 0, "C9", 0)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.KING, CardColours.TRUMP, 0, "C9", 4)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.ACE, CardColours.CLUBS, 0, "H9", 11)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.ACE, CardColours.CLUBS, 0, "H9", 11)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.TEN, CardColours.CLUBS, 0, "C9", 10)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.TEN, CardColours.CLUBS, 0, "C9", 10)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.KING, CardColours.HEARTS, 0, "H9", 4)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.HEARTS, 0, "H9", 0)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.HEARTS, 0, "H9", 0)));

    assertThat(game.handOutCardsAreValid(game.getPlayers().getFirst())).isTrue();
  }

}
