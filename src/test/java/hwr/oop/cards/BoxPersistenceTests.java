package hwr.oop.cards;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

public class BoxPersistenceTests {

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
            persistenceSavePort.saveTrainingInstance(boxes, "test_box");

            PersistenceLoadPort persistenceLoadPort = new JsonPersistenceAdapter();
            Collection<Box> testBoxes = persistenceLoadPort.loadTrainingInstance("test_box");
            Assertions.assertThat(testBoxes).isEqualTo(boxes);
        }
    }
}
