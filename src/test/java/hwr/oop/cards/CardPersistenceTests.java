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

            assertThrows(IllegalArgumentException.class, () -> pa.loadCards(""));
        }

        @Test
        void save(){

            JsonPersistenceAdapter pa = new JsonPersistenceAdapter();

            assertThrows(IllegalArgumentException.class, () -> pa.saveCards(List.of(new Card("", "", 0)), ""));
        }
    }

    @Nested
    class saveCardTests{

        @Test
        public void canSaveSingleCard(){

            PersistenceSavePort persistenceSavePort = new JsonPersistenceAdapter();
            PersistenceLoadPort persistenceLoadPort = new JsonPersistenceAdapter();

            List<Card> cards = List.of(new Card("Question?", "Answer!", 0));
            try {
                persistenceSavePort.saveCards(cards, "test");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Collection<Card> testCardList = null;
            try {
                testCardList = persistenceLoadPort.loadCards("test");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Assertions.assertThat(testCardList).isEqualTo(cards);
        }

        @Test
        public void canSaveMultipleCards(){

            PersistenceSavePort persistenceSavePort = new JsonPersistenceAdapter();
            PersistenceLoadPort persistenceLoadPort = new JsonPersistenceAdapter();

            List<Card> cards = List.of(new Card("Question?", "Answer!", 0), new Card("Frage?", "Antwort!", 1));
            try {
                persistenceSavePort.saveCards(cards, "test");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Collection<Card> testCardList = null;
            try {
                testCardList = persistenceLoadPort.loadCards("test");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Assertions.assertThat(testCardList).isEqualTo(cards);

        }

        @Test void makeSureToOverwritePreviousSaves(){

            PersistenceSavePort persistenceSavePort = new JsonPersistenceAdapter();
            PersistenceLoadPort persistenceLoadPort = new JsonPersistenceAdapter();

            List<Card> cards = List.of(new Card("Question?", "Answer!", 0));
            try {
                persistenceSavePort.saveCards(cards, "test");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            List<Card> cards1 = List.of(new Card("Frage?????", "Antwort!!!!!!", 0));
            try {
                persistenceSavePort.saveCards(cards1, "test");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Collection<Card> testCardList = null;
            try {
                testCardList = persistenceLoadPort.loadCards("test");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Assertions.assertThat(testCardList).isEqualTo(cards1);

        }
    }

    @Nested
    class loadCardTests{

        @BeforeEach
        void setUp(){

            PersistenceSavePort pa = new JsonPersistenceAdapter();

            List<Card> cards = List.of(new Card("Question?", "Answer!", 0), new Card("Frage?", "Antwort!", 1));
            try {
                pa.saveCards(cards, "test");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Test
        void canLoadSavedContent(){

            PersistenceLoadPort pa = new JsonPersistenceAdapter();

            Collection<Card> loadedCards = null;
            try {
                loadedCards = pa.loadCards("test");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Assertions.assertThat(loadedCards).isEqualTo(List.of(new Card("Question?", "Answer!", 0), new Card("Frage?", "Antwort!", 1)));
        }
    }

    // Just a little manual test to be able to look at the file
    // JDK 16+ needed for this specifically
    @AfterAll
    static void afterAll(){

        PersistenceSavePort pa = new JsonPersistenceAdapter();

        List<Card> cards = List.of(new Card("Question?", "Answer!", 0), new Card("Frage?", "Antwort!", 0));
        try {
            pa.saveCards(cards, "manual_test");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
