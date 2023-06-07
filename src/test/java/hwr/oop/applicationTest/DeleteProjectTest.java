package hwr.oop.applicationTest;

import hwr.oop.application.DeleteProjectUseCase;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.PersistenceAdapter;
import hwr.oop.persistence.SavePort;
import org.junit.jupiter.api.BeforeEach;

public class DeleteProjectTest {

    String directory = "./OOPTest/";
    LoadPort load;
    SavePort save;
    DeleteProjectUseCase deleteProject;
}
