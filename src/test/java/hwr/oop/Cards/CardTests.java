package hwr.oop.Cards;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTests {

    @Test
    void canGetCardQuestion(){

        Card card = new Card("Hallo?");
        String question = card.getQuestion();

        assertThat(question).isEqualTo("Hallo?");
    }

    @Test
    void canGetDateOfCard(){
        Card card = new Card("Ja?");
        String date = card.getDate();

        assertThat(date).isEqualTo("");
    }

    @Test
    void canGetTopicName(){

        Topic topic = new Topic("Japanese");
        String name = topic.getName();

        assertThat(name).isEqualTo("Japanese");
    }

    @Test
    void canGetLevelCountOfBox(){

        Box box = new Box();
        int count = box.getCount();

        assertThat(count).isEqualTo(5);
    }
}
