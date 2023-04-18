package hwr.oop.cards;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
        LocalDate date = card.getDate();
        LocalDate now = LocalDate.now();


        assertThat(date).isEqualTo(now);
    }

    @Test
    void canGetQuestionOfCard(){

        Card card = new Card("Is this a test?", "Yes");

        String question = card.getQuestion();
        assertThat(question).isEqualTo("Is this a test?");
    }

    @Test
    void canGetAnswerOfCard(){
         Card card = new Card("Is the earth flat?", "Yes");

         assertThat(card.getAnswer()).isEqualTo("Yes");
    }
}
