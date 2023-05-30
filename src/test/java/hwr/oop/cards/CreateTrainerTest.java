package hwr.oop.cards;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CreateTrainerTest {

    @Test
    void canCreateTrainerWith3Boxes(){
        Trainer trainer = Trainer.createTrainerWith3Boxes();
        int length = trainer.getBoxList().size();
        Assertions.assertThat(length).isEqualTo(3);
    }@Test
    void canCreateTrainerWith5Boxes(){
        Trainer trainer = Trainer.createTrainerWith5Boxes();
        int length = trainer.getBoxList().size();
        Assertions.assertThat(length).isEqualTo(5);
    }@Test
    void canCreateTrainerWith7Boxes(){
        Trainer trainer = Trainer.createTrainerWith7Boxes();
        int length = trainer.getBoxList().size();
        Assertions.assertThat(length).isEqualTo(7);
    }
    @Test
    void canCreateTrainerFromSave(){
        PersistenceLoadPort loadPort = new JsonPersistenceAdapter();
        LoadTrainerFromPersistenceUseCase creator = new LoadTrainerFromPersistenceUseCase(loadPort);
        Trainer trainer = null;
        try{
            trainer = creator.loadTrainer("test_box");
        }catch (IOException error){
            error.printStackTrace();
        }
        Assertions.assertThat(trainer.getBoxList()).isNotNull();
    }
}
