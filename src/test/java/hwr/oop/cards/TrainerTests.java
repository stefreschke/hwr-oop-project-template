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
    void canLoadTopic(){
        Trainer trainer = new Trainer();
        Topic topic = new Topic("Spanish");
        topic.createCard("Marco", "Polo");
        trainer.loadTopic(topic);
        Card cardRef1 = topic.getCardList().get(0);
        Card cardRef2 = trainer.getBoxList().get(0).getRandomCard();
        Assertions.assertThat(cardRef1).isEqualTo(cardRef2);
    }
}
