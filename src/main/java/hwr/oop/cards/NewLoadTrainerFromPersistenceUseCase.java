package hwr.oop.cards;

import java.io.IOException;
import java.util.List;

public class NewLoadTrainerFromPersistenceUseCase {
    private final PersistenceLoadPort persistenceLoadPort;

    public NewLoadTrainerFromPersistenceUseCase(PersistenceLoadPort persistenceLoadPort){
        this.persistenceLoadPort = persistenceLoadPort;
    }

    public NewTrainer loadTrainer(String filename) throws IOException {
        NewTrainer trainer;
        List<BoxInterface> boxList = (List)persistenceLoadPort.loadTrainingInstance(filename);
        trainer = NewTrainer.createTrainerFromBoxList(boxList);
        return trainer;
    }
}
