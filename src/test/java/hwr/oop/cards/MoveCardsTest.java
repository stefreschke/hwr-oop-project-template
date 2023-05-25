package hwr.oop.cards;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveCardsTest {
    private Trainer trainer;
    private Topic topic;
    private Box box1;
    private Box box2;
    private Box box3;
    private Card compareCard;
    @BeforeEach
    void setup(){
        trainer = new Trainer.TrainerBuilder().buildTrainerWith3Boxes();
        topic = new Topic("Random");
        box1 = trainer.getBoxList().get(0);
        box2 = trainer.getBoxList().get(1);
        box3 = trainer.getBoxList().get(2);
        topic.createCard("Penny", "Doku");
        compareCard = topic.getCardList().get(0);
        trainer.loadTopic(topic);
    }

    @Test
    void canMoveCardUp(){
        Card card = box1.getRandomCard();
        trainer.moveCardUp(card);
        Assertions.assertThat(box2.getRandomCard()).isEqualTo(compareCard);
    }

    @Test
    void canMoveCardUp2Times(){
        Card card = box1.getRandomCard();
        trainer.moveCardUp(card);
        card = box2.getRandomCard(); //davor müsste spezieiferzt werden, dass es aus Box2 kommt und currentBoxIndex auf 1 setzten
        trainer.moveCardUp(card);
        Assertions.assertThat(box3.getRandomCard()).isEqualTo(compareCard);
    }
    @Test
    void canMoveCardUpTopBox(){
        Card card = box1.getRandomCard();
        trainer.moveCardUp(card);
        card = box2.getRandomCard(); //davor müsste spezieiferzt werden, dass es aus Box2 kommt und currentBoxIndex auf 1 setzten
        trainer.moveCardUp(card);
        card = box3.getRandomCard();
        trainer.moveCardUp(card); // sollte immer noch in 3. Box sein
        Assertions.assertThat(box3.getRandomCard()).isEqualTo(compareCard);
    }

    @Test
    void canMoveCardDown(){
        Card card = box1.getRandomCard();
        trainer.moveCardUp(card);
        card = box2.getRandomCard();
        trainer.moveCardDown(card);
        Assertions.assertThat(box1.getRandomCard()).isEqualTo(compareCard);
    }
    @Test
    void canMoveCardDown2Times(){
        Card card = box1.getRandomCard();
        trainer.moveCardUp(card);
        card = box2.getRandomCard();
        trainer.moveCardUp(card);
        card = box3.getRandomCard();

        trainer.moveCardDown(card);
        card = box2.getRandomCard();
        trainer.moveCardDown(card);
        Assertions.assertThat(box1.getRandomCard()).isEqualTo(compareCard);
    }
    @Test
    void canMoveCardDownBottomBox(){
        Card card = box1.getRandomCard();
        trainer.moveCardDown(card);
        Assertions.assertThat(box1.getRandomCard()).isEqualTo(compareCard);
    }

    @Test
    void canMoveRandomCard(){

    }
}