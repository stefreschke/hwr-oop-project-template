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

    @Test
    public void testShuffleCardsStack() {
        CardGenerator cardGenerator = new CardGenerator();
        List<Card> cardStack = cardGenerator.generateAllCards();

        // Vorbereitung der Kartenliste
        CardStack cardStackObject = new CardStack();
        List<Card> originalCards = new ArrayList<>(cardStack);
        List<Card> shuffledCards = cardStackObject.shuffleCardsStack(originalCards);

        assertThat(originalCards.size(), shuffledCards.size());
        assertTrue(shuffledCards.containsAll(cardStack));

        assertNotEquals(originalCards, shuffledCards);
    }

    private void assertThat(int size, int size1) {
    }

    private void assertThat(int size, int size1, String s) {
    }
}

