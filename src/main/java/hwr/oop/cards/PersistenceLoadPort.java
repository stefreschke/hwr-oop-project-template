package hwr.oop.cards;

import java.io.IOException;
import java.util.Collection;

public interface PersistenceLoadPort {

    Collection<Card> loadCards(String persistenceInstanceName) throws IOException;

    Collection<Box> loadTrainingInstance(String persistenceInstanceName) throws IOException;
}
