package hwr.oop.cards;

import java.io.IOException;
import java.util.Collection;

public interface NewPersistenceLoadPort {
    Topic loadTopic(String persistenceInstanceName) throws IOException;

    Collection<NewBox> loadLernsession(String persistenceInstanceName) throws IOException;
}
