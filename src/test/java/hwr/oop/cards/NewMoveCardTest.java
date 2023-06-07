package hwr.oop.cards;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NewMoveCardTest {
    private Lernsession lernsession;
    private Topic topic;
    private NewBox box1;
    private NewBox box2;
    private NewBox box3;
    private Card compareCard;
    @BeforeEach
    void setup(){
        Lernsession lernsession = Lernsession.createLernsessionWith3Boxes();
        topic = new Topic("Random");
        box1 = lernsession.getBoxes().retrieve(0).get();
        box2 = lernsession.getBoxes().retrieve(1).get();
        box3 = lernsession.getBoxes().retrieve(2).get();
        topic.createCard("Penny", "Doku");
        compareCard = topic.getCardList().get(0);
        lernsession.loadTopic(topic);
    }

    @Test
    void canMoveCardUp(){
        Card card = box1.getRandomCard();
        box1.moveCardUp(card);
        Assertions.assertThat(box1.isEmpty()).isTrue();
        Assertions.assertThat(box2.getRandomCard()).isEqualTo(compareCard);
    }
    @Test
    void canMoveCardUp2Times(){
        Card card = box1.getRandomCard();
        box1.moveCardUp(card);
        card = box2.getRandomCard();
        box2.moveCardUp(card);
        Assertions.assertThat(box1.isEmpty()).isTrue();
        Assertions.assertThat(box2.isEmpty()).isTrue();
        Assertions.assertThat(box3.getRandomCard()).isEqualTo(compareCard);
    }
    @Test
    void canMoveCardUpTopBox(){
        Card card = box1.getRandomCard();
        box1.moveCardUp(card);
        card = box2.getRandomCard();
        box2.moveCardUp(card);
        card = box3.getRandomCard();
        box3.moveCardUp(card); // sollte immer noch in 3. Box sein
        Assertions.assertThat(box1.isEmpty()).isTrue();
        Assertions.assertThat(box2.isEmpty()).isTrue();
        Assertions.assertThat(box3.getRandomCard()).isEqualTo(compareCard);
    }

    @Test
    void canMoveCardDown(){
        Card card = box1.getRandomCard();
        box1.moveCardUp(card);
        card = box2.getRandomCard();
        box2.moveCardDown(card);
        Assertions.assertThat(box2.isEmpty()).isTrue();
        Assertions.assertThat(box1.getRandomCard()).isEqualTo(compareCard);
    }
    @Test
    void canMoveCardDown2Times(){
        Card card = box1.getRandomCard();
        box1.moveCardUp(card);
        card = box2.getRandomCard();
        box2.moveCardUp(card);
        card = box3.getRandomCard();

        box3.moveCardDown(card);
        card = box2.getRandomCard();
        box2.moveCardDown(card);
        Assertions.assertThat(box2.isEmpty()).isTrue();
        Assertions.assertThat(box3.isEmpty()).isTrue();
        Assertions.assertThat(box1.getRandomCard()).isEqualTo(compareCard);
    }

    @Test
    void canMoveCardDownBottomBox(){
        Card card = box1.getRandomCard();
        box1.moveCardDown(card);
        Assertions.assertThat(box1.getRandomCard()).isEqualTo(compareCard);
    }

    /*@Test
    void canMoveRandomCardUp(){}*/
}