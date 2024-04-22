package hwr.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class CardGenerationTests {
    //scenario : Cards will be generated
    @Test
    void TestGenerateCardStack(){
        final var cardStack = new CardGenerator();
        final var generatedCardDeck = cardStack.generateAllCards();
        assertThat(generatedCardDeck)
                .isNotEmpty()
                .isNotNull()
                .hasSize(48);
    }
}
class PlayerCardHandTests {
    //scenario card stack will be hand out to players hands
    @Test
    void HandOutCard(){
        final var cardStack = new CardStack();
        final var hand1 = cardStack.ShuffleCardStack();
        final var hand2 = cardStack.ShuffleCardStack();
        assertThat(hand1).isNotEqualTo(hand2);
    }
}
/*class TestShuffleCards {
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
}*/
