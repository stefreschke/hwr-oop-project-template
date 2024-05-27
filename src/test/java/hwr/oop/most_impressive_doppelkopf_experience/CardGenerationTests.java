package hwr.oop.most_impressive_doppelkopf_experience;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardGenerationTests {
  // scenario : Card will be generated
  @Test
  void testGenerateCardStack() {
    final var cardStack = new CardGenerator();
    final var generatedCardDeck = cardStack.generateAllCards();
    assertThat(generatedCardDeck).isNotEmpty().isNotNull().hasSize(48);
  }
}