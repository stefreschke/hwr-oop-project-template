package hwr.oop.doppelkopf;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
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
  void testShuffle() {
    CreateRandomDeck createRandomDeck = new CreateRandomDeck();
    DoppelkopfGame game = new DoppelkopfGame();

    List<Card> unshuffledCards = game.initializeCards();
    List<Card> shuffledCards = createRandomDeck.shuffleDeck(unshuffledCards);

    assertThat(shuffledCards).isNotEmpty().doesNotContainSequence(unshuffledCards);
    }

  @Test
  void testColor() {
    List<Card> cards = Arrays.asList(
            new Card(Color.HERZ, Type.BUBE),
            new Card(Color.KREUZ, Type.NEUN),
            new Card(Color.PIK, Type.ZEHN)
    );
    boolean result1 = new DoppelkopfGame().hasCard(cards, Color.KARO, Type.KOENIG);
    boolean result2 = new DoppelkopfGame().hasCard(cards, Color.PIK, Type.ZEHN); // Hier eine nicht vorhandene Karte

    SoftAssertions.assertSoftly(
            softly -> {
              assertThat(result1).isFalse();
              assertThat(result2).isTrue();
            });
  }
}

/*
9 - 0
König - 1
10 (außer Herz 10) - 2
Ass - 3
Bube - 4
Dame - 5
Herz 10 - 6
*/
