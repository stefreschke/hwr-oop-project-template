package hwr.oop.cards;

import java.io.IOException;
import java.util.Collection;

public interface PersistenceLoadPort {

    Topic loadTopic(String persistenceInstanceName) throws IOException;

    Collection<Box> loadTrainingInstance(String persistenceInstanceName) throws IOException;
}
