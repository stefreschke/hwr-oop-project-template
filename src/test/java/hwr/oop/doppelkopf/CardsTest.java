package hwr.oop.doppelkopf;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

class CardsTest {
  @Test
  void testAllCards() {
    DoppelkopfGame game = new DoppelkopfGame();
    List<Card> cards = game.initializeCards();

    for (Color color : Color.values()) {
      for (Type value : Type.values()) {
        assertTrue(game.hasCard(cards, color, value), "Karte " + color + " " + value + " fehlt im Spiel.");
      }
    }
  }

  @Test
  void testCardForInitilize() {
    DoppelkopfGame game = new DoppelkopfGame();
    List<Card> cards = List.of();
    for (Color color : Color.values()) {
      for (Type value : Type.values()) {
        assertThat(game.hasCard(cards, color, value)).isFalse();
      }
    }
  }

  @Test
  void testCountCards() {
    DoppelkopfGame game = new DoppelkopfGame();
    List<Card> cards = game.initializeCards();

    assertThat(cards).hasSize(48);
  }

  @Test
  void testshuffle() {
    DoppelkopfGame unshuffledCards = new DoppelkopfGame();
    CreateRandomDeck shuffledCards = new CreateRandomDeck();
    List<Card> shuffled = shuffledCards.shuffleDeck();
    List<Card> unshuffled = unshuffledCards.initializeCards();

    assertThat(unshuffled).isNotEmpty().doesNotContainSequence(shuffled);
  }
}
