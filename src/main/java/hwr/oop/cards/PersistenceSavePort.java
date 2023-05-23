package hwr.oop.cards;

import java.io.IOException;
import java.util.Collection;

public interface PersistenceSavePort {

    void saveTopic(Topic topic, String persistenceInstanceName) throws IOException;

    void saveTrainingInstance(Collection<Box> boxes, String persistenceInstanceName) throws IOException;
}
