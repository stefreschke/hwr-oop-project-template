package hwr.oop.cards;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateTrainerTest {

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
    void canCreateTrainerFromSave(){
        Trainer trainer = new Trainer.TrainerBuilder().buildTrainerFromSave();
        Assertions.assertThat(trainer).isNotNull();
    }
}
