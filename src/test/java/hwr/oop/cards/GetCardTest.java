package hwr.oop.cards;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GetCardTest {
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
        topic.createCard("escalera", "Leiter");
        topic.createCard("caballo", "Pferd");
        trainer.loadTopic(topic);
    }
    @Test
    void canGetRandomBoxIndex(){
        Trainer trainer = new Trainer.TrainerBuilder().buildTrainerWith3Boxes();
        int randomBoxIndex = trainer.getRandomBoxIndex();
        Assertions.assertThat(randomBoxIndex).isIn(List.of(0,1,2));
    }
    @Test
    void canGetRandomCardFromRandomBox(){
        Card card = trainer.getRandomCard();
        Assertions.assertThat(card).isIn(topic.getCardList());
    }

}