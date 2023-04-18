package hwr.oop.Cards;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTests {

    @Test
    void canGetCardQuestion(){

        Card card = new Card("Hallo?", "Yes");
        String question = card.getQuestion();

        assertThat(question).isEqualTo("Hallo?");
    }

    @Test
    void canGetDateOfCard(){
        Card card = new Card("Ja?", "Yes");
        String date = card.getDate();

        assertThat(date).isEqualTo("");
    }

    @Test
    void canGetQuestionOfCard(){

        Card card = new Card("Is this a test?", "Yes");

        assertThat(card.getQuestion()).isEqualTo("Is this a test?");
    }

    @Test
    void canGetAnswerOfCard(){
         Card card = new Card("Is the earth flat?", "Yes");

         assertThat(card.getAnswer()).isEqualTo("Yes");
    }
}
