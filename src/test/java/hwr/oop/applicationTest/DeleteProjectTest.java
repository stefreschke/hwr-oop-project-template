package hwr.oop.applicationTest;

import hwr.oop.application.DeleteProjectService;
import hwr.oop.application.DeleteProjectUseCase;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.PersistenceAdapter;
import hwr.oop.persistence.SavePort;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;

public class DeleteProjectTest {

    String directory = "./OOPTest/";
    LoadPort load;
    SavePort save;
    DeleteProjectUseCase deleteProject;

    @BeforeEach
    void setUp() {
        load = new PersistenceAdapter(directory);
        save = new PersistenceAdapter(directory);
        deleteProject = new DeleteProjectService(load, save);

        final File parent = new File(directory);
        parent.mkdirs();
        File file = new File(directory + "AppData.json");
        file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
