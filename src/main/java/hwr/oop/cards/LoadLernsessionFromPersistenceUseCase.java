package hwr.oop.cards;

import java.io.IOException;
import java.util.List;

public class LoadLernsessionFromPersistenceUseCase {
    private final PersistenceLoadPort persistenceLoadPort;

    public LoadLernsessionFromPersistenceUseCase(PersistenceLoadPort persistenceLoadPort){
        this.persistenceLoadPort = persistenceLoadPort;
    }

    public Lernsession loadTrainer(String filename) throws IOException {
        Lernsession lernsession;
        List<BoxInterface> boxList = (List)persistenceLoadPort.loadTrainingInstance(filename);
        lernsession = Lernsession.createLernsessionFromBoxList(boxList);
        return lernsession;
    }
}
