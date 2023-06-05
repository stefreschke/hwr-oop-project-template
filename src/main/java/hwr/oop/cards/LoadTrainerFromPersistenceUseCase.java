package hwr.oop.cards;

import java.io.IOException;
import java.util.List;

public class LoadTrainerFromPersistenceUseCase {
    private final PersistenceLoadPort persistenceLoadPort;

    public LoadTrainerFromPersistenceUseCase(PersistenceLoadPort persistenceLoadPort){
        this.persistenceLoadPort = persistenceLoadPort;
    }

    public Trainer loadTrainer(String filename) throws IOException {
        Trainer trainer;
        List<Box> boxList = (List)persistenceLoadPort.loadTrainingInstance(filename);
        trainer = Trainer.createTrainerFromBoxList(boxList);
        return trainer;
    }

}
