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

public class BoxPersistenceTests {

    @Nested
    class emptyPersistenceInstanceNameThrowsExceptionTests{

        @Test
        void save(){

            PersistenceSavePort pa = new JsonPersistenceAdapter();

            Box box1 = new Box();
            box1.addCard(new Card("Box 1?", "Box 1!", 0));
            List<Box> boxes = List.of(box1);

            assertThrows(IllegalArgumentException.class, () -> pa.saveTrainingInstance(boxes, ""));
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

            Box box1 = new Box();
            Box box2 = new Box();
            Box box3 = new Box();

            box1.addCard(new Card("Box 1?", "Box 1!", 0));
            box2.addCard(new Card("Box 2?", "Box 2!", 1));
            box3.addCard(new Card("Box 3?", "Box 3!", 2));

            List<Box> boxes = List.of(box1, box2, box3);

            PersistenceSavePort persistenceSavePort = new JsonPersistenceAdapter();
            try {
                persistenceSavePort.saveTrainingInstance(boxes, "test_box");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            PersistenceLoadPort persistenceLoadPort = new JsonPersistenceAdapter();
            Collection<Box> testBoxes = null;
            try {
                testBoxes = persistenceLoadPort.loadTrainingInstance("test_box");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertThat(testBoxes).isEqualTo(boxes);
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
