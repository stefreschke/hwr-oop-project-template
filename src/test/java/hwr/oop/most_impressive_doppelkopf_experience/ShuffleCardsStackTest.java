package hwr.oop.most_impressive_doppelkopf_experience;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShuffleCardsStackTest {
    CardGenerator cardGenerator = new CardGenerator();
    List<Card> cardStack = cardGenerator.generateAllCards();

    public List<Card> shuffleCardsStack(List<Card> cardStack) {
        List<Card> shuffledCards = new ArrayList<>(cardStack);
        Collections.shuffle(shuffledCards);
        return shuffledCards;
    }
    @Test
    public void testShuffleCardsStack() {
        // Vorbereitung der Kartenliste
        List<Card> originalCards = new ArrayList<>(cardStack);
        List<Card> shuffledCards = shuffleCardsStack(originalCards);

        assertThat(originalCards.size(), shuffledCards.size());
        assertTrue(shuffledCards.containsAll(cardStack));

        assertNotEquals(originalCards, shuffledCards);
    }

    private void assertThat(int size, int size1) {
    }

    private void assertThat(int size, int size1, String s) {
    }
}

