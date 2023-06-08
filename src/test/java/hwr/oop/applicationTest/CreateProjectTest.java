package hwr.oop.applicationTest;

import hwr.oop.application.CreateProjectService;
import hwr.oop.application.CreateProjectUseCase;
import hwr.oop.application.Project;
import hwr.oop.application.User;
import hwr.oop.persistence.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.PersistenceAdapter;
import hwr.oop.persistence.SavePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CreateProjectTest {
    String directory = "./OOPTest/";
    AppData appData;
    CreateProjectUseCase createProject;
    LoadPort load;
    SavePort save;

    @BeforeEach
    void setUp() {
        load = new PersistenceAdapter(directory);
        save = new PersistenceAdapter(directory);
        createProject = new CreateProjectService(load, save);

        final File parent = new File(directory);
        parent.mkdirs();
        File file = new File(directory + "AppData.json");
        file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        appData = load.loadData();
    }

    static Stream<Arguments> randomProjectsWithSingleUser() {
        List<Arguments> randomProjects = new ArrayList<>();
        for (int i=0; i<5; i++) {
            Project rando = RandomTestData.getRandomProject();
            User user = RandomTestData.getRandomUser();
            randomProjects.add(
                    Arguments.of(
                            new Project(rando.getId(), rando.getTaskList(), rando.getTitle(),
                                    Map.of(user, Boolean.TRUE)
                            ), user
                    )
            );
        }
        return  randomProjects.stream();
    }

    @ParameterizedTest
    @MethodSource("randomProjectsWithSingleUser")
    void canCreateProject(Project expected, User user) {
        Project result = createProject.createProject(expected.getTitle(), expected.getTaskList(), user);

        assertThat(result.getTitle()).hasToString(expected.getTitle());
        assertThat(result.getTaskList()).isEqualTo(expected.getTaskList());
        assertThat(result.getPermissions()).isEqualTo(expected.getPermissions());
    }

    @ParameterizedTest
    @MethodSource("randomProjectsWithSingleUser")
    void createProject_AddsOneToProjectListTest(Project project, User user) {
        AppData appData = new AppData(RandomTestData.getRandomProjects(), RandomTestData.getRandomUsers());
        save.saveData(appData);

        int originalSize = appData.getProjectList().size();
        createProject.createProject(project.getTitle(), project.getTaskList(), user);

        assertThat(load.loadData().getProjectList().size()).isEqualTo(originalSize+1);
    }
}
