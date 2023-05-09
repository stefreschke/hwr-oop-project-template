package hwr.oop.cards;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.util.Collection;
import java.util.List;

public class PersistenceTests {

    @Nested
    class cardPersistence{

        @Nested
        class saveCardTests{

            @Test
            public void canSaveSingleCard(){

                PersistenceSavePort persistenceSavePort = new PersistenceAdapter();
                PersistenceLoadPort persistenceLoadPort = new PersistenceAdapter();

                List<Card> cards = List.of(new Card("Question?", "Answer!"));
                persistenceSavePort.saveCards(cards, "test");

                Collection<Card> testCardList = persistenceLoadPort.loadCards("test");
                Assertions.assertThat(testCardList).isEqualTo(cards);
            }

            @Test
            public void canSaveMultipleCards(){

                PersistenceSavePort persistenceSavePort = new PersistenceAdapter();
                PersistenceLoadPort persistenceLoadPort = new PersistenceAdapter();

                List<Card> cards = List.of(new Card("Question?", "Answer!"), new Card("Frage?", "Antwort!"));
                persistenceSavePort.saveCards(cards, "test");

                Collection<Card> testCardList = persistenceLoadPort.loadCards("test");
                Assertions.assertThat(testCardList).isEqualTo(cards);

            }

            @Test void canMakeSureToOverwriteSaves(){

                PersistenceSavePort persistenceSavePort = new PersistenceAdapter();
                PersistenceLoadPort persistenceLoadPort = new PersistenceAdapter();

                List<Card> cards = List.of(new Card("Question?", "Answer!"));
                persistenceSavePort.saveCards(cards, "test");

                List<Card> cards1 = List.of(new Card("Frage?????", "Antwort!!!!!!"));
                persistenceSavePort.saveCards(cards1, "test");

                Collection<Card> testCardList = persistenceLoadPort.loadCards("test");
                Assertions.assertThat(testCardList).isEqualTo(cards1);

            }
        }
    }
}
