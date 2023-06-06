package hwr.oop.cards;

import java.io.IOException;
import java.util.List;

public class LoadLernsessionFromPersistenceUseCase {
    private final NewPersistenceLoadPort persistenceLoadPort;

    public LoadLernsessionFromPersistenceUseCase(NewPersistenceLoadPort persistenceLoadPort){
        this.persistenceLoadPort = persistenceLoadPort;
    }

    public Lernsession loadLernsession(String filename) throws IOException {
        Lernsession lernsession;
        List<BoxInterface> boxList = (List)persistenceLoadPort.loadLernsessionInstance(filename);
        lernsession = Lernsession.createLernsessionFromBoxList(boxList);
        return lernsession;
    }
}
