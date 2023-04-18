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

    @Test
    void canGetQuestionOfTopic(){


    }

    @Test
    void canCreateCardInBoxInTopic(){

     Topic topic = new Topic("German");
     Box box = new Box();
     Card card = new Card("?");

     box.addCard(card);
     topic.addBox(box);

     Box testBox = topic.getBoxOfIndex(0);
     Card testCard = testBox.getCardOfIndex(0);

     assertThat(testBox).isEqualTo(box);
     assertThat(testCard).isEqualTo(card);
    }
}
