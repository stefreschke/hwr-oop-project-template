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

            NewPersistenceLoadPort pa = new NewJsonPersistenceAdapter();

            assertThrows(IllegalArgumentException.class, () -> pa.loadLernsession(""));
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
        }

        @Test
        void makeSureToOverwritePreviousSaves(){

            Boxes mediator = Boxes.createBoxes(3);
            Boxes mediator2 = Boxes.createBoxes(3);
            NewBox box1 = mediator.retrieve(0).get();
            NewBox box2 = mediator.retrieve(1).get();
            NewBox box3 = mediator.retrieve(2).get();
            NewBox box1a = mediator.retrieve(0).get();
            NewBox box2a = mediator.retrieve(1).get();
            NewBox box3a = mediator.retrieve(2).get();

            box1.addCard(new Card("Box 1?", "Box 1!", 0));
            box2.addCard(new Card("Box 2?", "Box 2!", 1));
            box3.addCard(new Card("Box 3?", "Box 3!", 2));
            box1a.addCard(new Card("neue Box 1?", "Box 1!", 0));
            box2a.addCard(new Card("neue Box 2?", "Box 2!", 1));
            box3a.addCard(new Card("neue Box 3?", "Box 3!", 2));

            List<NewBox> boxes1 = mediator.createBoxList();
            List<NewBox> boxes2 = mediator2.createBoxList();

            NewPersistenceSavePort persistenceSavePort = new NewJsonPersistenceAdapter();
            try {
                persistenceSavePort.saveLernsession(boxes1, "test_box");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                persistenceSavePort.saveLernsession(boxes2, "test_box");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Collection<NewBox> loadedBox;
            NewPersistenceLoadPort persistenceLoadPort = new NewJsonPersistenceAdapter();
            try {
                loadedBox = persistenceLoadPort.loadLernsession("test_box");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertThat(boxes1).isNotEqualTo(loadedBox);
            assertThat(boxes2).isEqualTo(loadedBox);
        }
    }
}
