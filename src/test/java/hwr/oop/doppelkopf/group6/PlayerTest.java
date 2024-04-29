package hwr.oop.doppelkopf.group6;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class PlayerTest {
  @Test
  void testCreatePlayer() {
    DoppelkopfGame game = new DoppelkopfGame();

    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(game.player1.getName()).isEqualTo("Spieler1");
          softly.assertThat(game.player2.getName()).isEqualTo("Spieler2");
          softly.assertThat(game.player3.getName()).isEqualTo("Spieler3");
          softly.assertThat(game.player4.getName()).isEqualTo("Spieler4");

          softly.assertThat(game.player1.getOwnCards()).isEmpty();
          softly.assertThat(game.player2.getOwnCards()).isEmpty();
          softly.assertThat(game.player3.getOwnCards()).isEmpty();
          softly.assertThat(game.player4.getOwnCards()).isEmpty();

          softly.assertThat(game.player1.getOrder()).isEqualTo(1);
          softly.assertThat(game.player2.getOrder()).isEqualTo(2);
          softly.assertThat(game.player3.getOrder()).isEqualTo(3);
          softly.assertThat(game.player4.getOrder()).isEqualTo(4);
        });
  }

  @Test
  void checkPlayerHands() {
    DoppelkopfGame game = new DoppelkopfGame();
    CreateRandomDeck deck = new CreateRandomDeck();
    game.dealCards(deck.shuffleDeck(game.initializeCards()));

    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(game.player1.getOwnCards()).hasSize(12);
          softly.assertThat(game.player2.getOwnCards()).hasSize(12);
          softly.assertThat(game.player3.getOwnCards()).hasSize(12);
          softly.assertThat(game.player4.getOwnCards()).hasSize(12);
        });
  }
}
