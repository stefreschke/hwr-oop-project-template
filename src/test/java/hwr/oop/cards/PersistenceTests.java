package hwr.oop.cards;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.util.Collection;
import java.util.List;

public class PersistenceTests {

    @Nested
    class cardPersistence{

        @Test
        public void canSaveSingleCard(){

            PersistenceSavePort persistenceSavePort = new PersistenceAdapter();
            PersistenceLoadPort persistenceLoadPort = new PersistenceAdapter();

            List<Card> cards = List.of(new Card("Question?", "Answer!"));
            persistenceSavePort.saveCards(cards, "test");

            Collection<Card> testCardList = persistenceLoadPort.loadCards("test");
            Assertions.assertThat(testCardList).isEqualTo(cards);
        }
    }
}
