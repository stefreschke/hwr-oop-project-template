package hwr.oop.cards;

import java.util.Collection;

public interface PersistenceLoadPort {

    Collection<Card> loadCards(String persistenceInstanceName);

    Collection<Box> loadTrainingInstance(String persistenceInstanceName);
}
