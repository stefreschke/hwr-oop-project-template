package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @Test
    void testCreatePlayer() {
        DoppelkopfGame game = new DoppelkopfGame();

        Assertions.assertThat(game.player1.name).isEqualTo("Spieler1");
        Assertions.assertThat(game.player2.name).isEqualTo("Spieler2");
        Assertions.assertThat(game.player3.name).isEqualTo("Spieler3");
        Assertions.assertThat(game.player4.name).isEqualTo("Spieler4");

        Assertions.assertThat(game.player1.ownCards).isEmpty();
        Assertions.assertThat(game.player2.ownCards).isEmpty();
        Assertions.assertThat(game.player3.ownCards).isEmpty();
        Assertions.assertThat(game.player4.ownCards).isEmpty();
    }

  @Test
  void checkPlayerHands() {
        DoppelkopfGame game = new DoppelkopfGame();
        CreateRandomDeck deck = new CreateRandomDeck();
        game.dealCards(deck.shuffleDeck());

        Assertions.assertThat(game.player1.ownCards.size()).isEqualTo(12);
        Assertions.assertThat(game.player2.ownCards.size()).isEqualTo(12);
        Assertions.assertThat(game.player3.ownCards.size()).isEqualTo(12);
        Assertions.assertThat(game.player4.ownCards.size()).isEqualTo(12);


  }
}
