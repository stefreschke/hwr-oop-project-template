package hwr.oop.cards;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTests {

    @Test
    public void canGetQuestion(){

        Card card = new Card("What was the first item to be sold on Ebay?", "A broken laser pointer", 0);

        String testQuestion = card.getQuestion();
        assertThat(testQuestion).isEqualTo("What was the first item to be sold on Ebay?");
    }

    @Test
    public void canGetAnswer(){

        Card card = new Card("What is the largest living organism on earth?", "The Great Barrier Reef", 0);

        String testQuestion = card.getAnswer();
        assertThat(testQuestion).isEqualTo("The Great Barrier Reef");
    }
}
