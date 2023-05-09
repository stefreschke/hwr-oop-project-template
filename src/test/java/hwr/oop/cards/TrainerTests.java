package hwr.oop.cards;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

public class TrainerTests {
    @Test
    void canCreateTrainer(){
        Trainer trainer = new Trainer();
        Assertions.assertThat(trainer).isNotNull();
    }
    @Test
    void canCreateTrainer_WithBoxes(){
        Trainer trainer = new Trainer();
        int length = trainer.getBoxList().size();
        Assertions.assertThat(length).isEqualTo(3);
    }
    @Test
    void canLoadTopic_OneCard(){
        Trainer trainer = new Trainer();
        Topic topic = new Topic("Spanish");
        topic.createCard("Marco", "Polo");
        trainer.loadTopic(topic);
        Card cardRef1 = topic.getCardList().get(0);
        Card cardRef2 = trainer.getBoxList().get(0).getRandomCard();
        Assertions.assertThat(cardRef1).isEqualTo(cardRef2);
    }
    @Test
    void canLoadTopic_ThreeCards(){
        Trainer trainer = new Trainer();
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
}
