package hwr.oop;
import java.util.Random;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestShuffleCards {
    //scenario : Sorted Card stack will be shuffled
    @Test
    void TestShuffleCardStack() {
        //Given
        final var cardStack = new CardStack();
        //When
        final void shuffledDeck = cardStack.shuffleStack();
        //Then
        assertThat(cardStack.cards())
                .isNotEmpty()
                .doesNotContainSequence(cardStack);
    }
}
