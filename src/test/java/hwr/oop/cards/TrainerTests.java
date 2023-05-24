package hwr.oop.cards;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.ArrayList;
import java.util.List;

public class TrainerTests {

    @Test
    void canCreateTrainerWith3Boxes(){
        Trainer trainer = new Trainer.TrainerBuilder().buildTrainerWith5Boxes();
        int length = trainer.getBoxList().size();
        Assertions.assertThat(length).isEqualTo(3);
    }@Test
    void canCreateTrainerWith5Boxes(){
        Trainer trainer = new Trainer.TrainerBuilder().buildTrainerWith5Boxes();
        int length = trainer.getBoxList().size();
        Assertions.assertThat(length).isEqualTo(5);
    }@Test
    void canCreateTrainerWith7Boxes(){
        Trainer trainer = new Trainer.TrainerBuilder().buildTrainerWith5Boxes();
        int length = trainer.getBoxList().size();
        Assertions.assertThat(length).isEqualTo(7);
    }
    @Test
    void CanCreateTrainerFromSave(){
        Trainer trainer = new Trainer.TrainerBuilder().buildTrainerFromSave();
    }
    @Test
    void canLoadTopic_OneCard(){
        Trainer trainer = new Trainer.TrainerBuilder().buildTrainerWith3Boxes();
        Topic topic = new Topic("Spanish");
        topic.createCard("Marco", "Polo");
        trainer.loadTopic(topic);
        Card cardRef1 = topic.getCardList().get(0);
        Card cardRef2 = trainer.getBoxList().get(0).getRandomCard();
        Assertions.assertThat(cardRef1).isEqualTo(cardRef2);
    }
    @Test
    void canLoadTopic_ThreeCards(){
        Trainer trainer = new Trainer.TrainerBuilder().buildTrainerWith3Boxes();
        Topic topic = new Topic("Spanish");
        topic.createCard("Marco", "Polo");
        topic.createCard("Julius", "Caesar");
        topic.createCard("Freddy", "Faulig");
        trainer.loadTopic(topic);
        Card cardRef1_topic = topic.getCardList().get(0);
        Card card1_rand = trainer.getBoxList().get(0).getRandomCard();
        Assertions.assertThat(topic.getCardList()).contains(card1_rand);
        Card cardRef2_topic = topic.getCardList().get(1);
        Card card2_rand = trainer.getBoxList().get(0).getRandomCard();        Assertions.assertThat(topic.getCardList()).contains(card1_rand);
        Assertions.assertThat(topic.getCardList()).contains(card2_rand);
        Card cardRef3_topic = topic.getCardList().get(2);
        Card card3_rand = trainer.getBoxList().get(0).getRandomCard();
        Assertions.assertThat(topic.getCardList()).contains(card3_rand);
    }

    //andere Testklassen
    @Test
    void canGetRandomBoxIndex(){
        Trainer trainer = new Trainer.TrainerBuilder().buildTrainerWith3Boxes();
        int randomBoxIndex = trainer.getRandomBoxIndex();
        Assertions.assertThat(randomBoxIndex).isIn(List.of(0,1,2));
    }
    @Test
    void canGetRandomCardFromRandomBox(){

    }
    @Test
    void canAskQuestionToCLI(){

    }
    @Test
    void getAnswer(){

    }

}
