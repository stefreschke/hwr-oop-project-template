package hwr.oop.cards;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class CardPersistenceTests {

    @Nested
    class emptyPersistenceInstanceNameThrowsExceptionTests{

        @Test
        void load(){

            JsonPersistenceAdapter pa = new JsonPersistenceAdapter();

            assertThrows(IllegalArgumentException.class, () -> pa.loadTopic(""));
        }

        @Test
        void save(){

            JsonPersistenceAdapter pa = new JsonPersistenceAdapter();

            Topic topic = new Topic("testTopic");
            topic.createCard("", "");
            assertThrows(IllegalArgumentException.class, () -> pa.saveTopic(topic, ""));
        }
    }

    @Nested
    class saveCardTests{

        @Test
        public void canSaveSingleCard(){

            PersistenceSavePort persistenceSavePort = new JsonPersistenceAdapter();
            PersistenceLoadPort persistenceLoadPort = new JsonPersistenceAdapter();

            Topic topic = new Topic("testTopic");
            topic.createCard("Question?", "Answer!");
            try {
                persistenceSavePort.saveTopic(topic, "test");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Topic testTopic = null;
            try {
                testTopic = persistenceLoadPort.loadTopic("test");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Assertions.assertThat(testTopic).isEqualTo(topic);
        }

        @Test
        public void canSaveMultipleCards(){

            PersistenceSavePort persistenceSavePort = new JsonPersistenceAdapter();
            PersistenceLoadPort persistenceLoadPort = new JsonPersistenceAdapter();

            Topic topic = new Topic("testTopic");
            topic.createCard("Question?", "Answer!");
            topic.createCard("Frage?", "Antwort!");
            try {
                persistenceSavePort.saveTopic(topic, "test");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Topic testTopic = null;
            try {
                testTopic = persistenceLoadPort.loadTopic("test");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Assertions.assertThat(testTopic).isEqualTo(topic);

        }

        @Test void makeSureToOverwritePreviousSaves(){

            PersistenceSavePort persistenceSavePort = new JsonPersistenceAdapter();
            PersistenceLoadPort persistenceLoadPort = new JsonPersistenceAdapter();

            Topic topic = new Topic("testTopic");
            topic.createCard("Question?", "Answer!");
            try {
                persistenceSavePort.saveTopic(topic, "test");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Topic topic1 = new Topic("testTopic");
            topic.createCard("Question??????", "Answer!!!!!!");
            try {
                persistenceSavePort.saveTopic(topic1, "test");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Topic testTopic = null;
            try {
                testTopic = persistenceLoadPort.loadTopic("test");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Assertions.assertThat(testTopic).isEqualTo(topic1);

        }
    }

    @Nested
    class loadCardTests{
        Topic savedTopic;
        @BeforeEach
        void setUp(){
            PersistenceSavePort pa = new JsonPersistenceAdapter();

            savedTopic = new Topic("testTopic");
            savedTopic.createCard("Question?", "Answer!");
            savedTopic.createCard("Frage?", "Antwort!");
            try {
                pa.saveTopic(savedTopic, "test");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Test
        void canLoadSavedContent(){

            PersistenceLoadPort pa = new JsonPersistenceAdapter();

            Topic loadedTopic = null;
            try {
                loadedTopic = pa.loadTopic("test");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Assertions.assertThat(loadedTopic).isEqualTo(savedTopic);
        }
    }

    // Just a little manual test to be able to look at the file
    // JDK 16+ needed for this specifically
    @AfterAll
    static void afterAll(){

        PersistenceSavePort pa = new JsonPersistenceAdapter();

        Topic topic = new Topic("testTopic");
        topic.createCard("Question?", "Answer!");
        try {
            pa.saveTopic(topic, "manual_test");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
