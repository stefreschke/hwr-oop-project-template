package hwr.oop.cards;

import java.util.Collection;

public interface PersistenceLoadPort {

    Collection<Card> loadCards(String filename);

    Collection<Box> loadTrainingInstance(String filename);
}
