package hwr.oop.cards;

import java.util.Collection;

public interface PersistenceSavePort {

    void saveCards(Collection<Card> cards, String persistenceInstanceName);

    void saveTrainingInstance(Collection<Box> boxes, String persistenceInstanceName);
}
