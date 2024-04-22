package hwr.oop;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class PlayerTest {
  @Test
  void testCreatePlayer() {
    DoppelkopfGame game = new DoppelkopfGame();

    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(game).isNotNull();
        });
    Assertions.assertThat(game.player1.getName()).isEqualTo("Spieler1");
    Assertions.assertThat(game.player2.getName()).isEqualTo("Spieler2");
    Assertions.assertThat(game.player3.getName()).isEqualTo("Spieler3");
    Assertions.assertThat(game.player4.getName()).isEqualTo("Spieler4");

    Assertions.assertThat(game.player1.getOwnCards()).isEmpty();
    Assertions.assertThat(game.player2.getOwnCards()).isEmpty();
    Assertions.assertThat(game.player3.getOwnCards()).isEmpty();
    Assertions.assertThat(game.player4.getOwnCards()).isEmpty();
  }

  @Test
  void checkPlayerHands() {
    DoppelkopfGame game = new DoppelkopfGame();
    CreateRandomDeck deck = new CreateRandomDeck();
    game.dealCards(deck.shuffleDeck());

    Assertions.assertThat(game.player1.getOwnCards()).hasSize(12);
    Assertions.assertThat(game.player2.getOwnCards()).hasSize(12);
    Assertions.assertThat(game.player3.getOwnCards()).hasSize(12);
    Assertions.assertThat(game.player4.getOwnCards()).hasSize(12);
  }
}
