package hwr.oop.cards;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class NewBoxPersistenceTest {

    @Nested
    class emptyPersistenceInstanceNameThrowsExceptionTests{

        @Test
        void save(){

            NewPersistenceSavePort pa = new NewJsonPersistenceAdapter();

            Boxes mediator = Boxes.createBoxes(3);
            NewBox box1 = mediator.retrieve(0).get();
            box1.addCard(new Card("Box 1?", "Box 1!", 0));
            List<NewBox> boxes = List.of(box1);

            assertThrows(IllegalArgumentException.class, () -> pa.saveLernsession(boxes, ""));
        }

        @Test
        void load(){

            PersistenceLoadPort pa = new JsonPersistenceAdapter();

            assertThrows(IllegalArgumentException.class, () -> pa.loadTrainingInstance(""));
        }
    }

    @Nested
    class SaveBoxesTests{

        @Test
        void canSave3Boxes(){

            Boxes mediator = Boxes.createBoxes(3);
            NewBox box1 = mediator.retrieve(0).get();
            NewBox box2 = mediator.retrieve(1).get();
            NewBox box3 = mediator.retrieve(2).get();

            box1.addCard(new Card("Box 1?", "Box 1!", 0));
            box2.addCard(new Card("Box 2?", "Box 2!", 1));
            box3.addCard(new Card("Box 3?", "Box 3!", 2));

            List<NewBox> saveList = mediator.createBoxList();
            NewPersistenceSavePort persistenceSavePort = new NewJsonPersistenceAdapter();
            try {
                persistenceSavePort.saveLernsession(saveList, "test_box");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            NewPersistenceLoadPort persistenceLoadPort = new NewJsonPersistenceAdapter();
            Collection<NewBox> loadedList = null;
            try {
                loadedList = persistenceLoadPort.loadLernsession("test_box");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            assertThat(loadedList).isEqualTo(saveList);
            assertThat(loadedList).isEqualTo(saveList);
            assertThat(loadedList).isEqualTo(saveList);
        }

        @Test
        void makeSureToOverwritePreviousSaves(){

            Box box1 = new Box();
            Box box2 = new Box();
            Box box3 = new Box();

            box1.addCard(new Card("Box 1?", "Box 1!", 0));
            box2.addCard(new Card("Box 2?", "Box 2!", 1));
            box3.addCard(new Card("Box 3?", "Box 3!", 2));

            List<Box> boxes1 = List.of(box1, box2, box3);
            List<Box> boxes2 = List.of(box3, box2, box1);

            PersistenceSavePort persistenceSavePort = new JsonPersistenceAdapter();
            try {
                persistenceSavePort.saveTrainingInstance(boxes1, "test_box");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                persistenceSavePort.saveTrainingInstance(boxes2, "test_box");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Collection<Box> testBox;
            PersistenceLoadPort persistenceLoadPort = new JsonPersistenceAdapter();
            try {
                testBox = persistenceLoadPort.loadTrainingInstance("test_box");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            assertThat(boxes2).isEqualTo(testBox);
        }
    }
}
