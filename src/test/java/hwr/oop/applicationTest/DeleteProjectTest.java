package hwr.oop.applicationTest;

import hwr.oop.application.CantDeleteNonexistentProjectException;
import hwr.oop.application.DeleteProjectService;
import hwr.oop.application.DeleteProjectUseCase;
import hwr.oop.application.Project;
import hwr.oop.persistence.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.PersistenceAdapter;
import hwr.oop.persistence.SavePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;


class DeleteProjectTest {

    String directory = "./OOPTest/";
    LoadPort load;
    SavePort save;
    DeleteProjectUseCase deleteProject;

    AppData appData;

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

    @Test
    void deletedProject_notInAppData() {
        Project project = RandomTestData.getRandomProject();
        List<Project> projectList = RandomTestData.getRandomProjects();
        projectList.add(project);
        appData = new AppData(projectList, RandomTestData.getRandomUsers());
        save.saveData(appData);

        deleteProject.deleteProject(project);

        assertThat(load.loadData().getProjectList()).doesNotContain(project);
    }

    @Test
    void deleteProjectOnEmptyProjectList_throwsException() {
        appData = new AppData(new ArrayList<>(), RandomTestData.getRandomUsers());
        save.saveData(appData);

        try {
            deleteProject.deleteProject(RandomTestData.getRandomProject());
            fail("should throw exception");
        } catch (CantDeleteNonexistentProjectException e) {
            e.printStackTrace();
        }
    }
}
