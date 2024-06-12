package hwr.oop.most_impressive_doppelkopf_experience;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class ShuffleCardsStackTest {

  @Test
  void testShuffleCardsStack() {
    CardGenerator cardGenerator = new CardGenerator();
    List<Card> cardStack = cardGenerator.generateAllCards();

    CardStack cardStackObject = new CardStack();
    List<Card> originalCards = new ArrayList<>(cardStack);
    List<Card> shuffledCards = cardStackObject.shuffleCardsStack(originalCards);

    assertSoftly(
        softly -> {
          assertThat(originalCards).hasSameSizeAs(shuffledCards);
          assertThat(originalCards).hasSameSizeAs(shuffledCards);
          assertThat(shuffledCards).doesNotContainSequence(originalCards);
          assertTrue(shuffledCards.containsAll(cardStack));
          assertNotEquals(originalCards, shuffledCards);
        });
  }
}
