package hwr.oop.applicationTest;

import hwr.oop.application.*;
import hwr.oop.persistence.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.PersistenceAdapter;
import hwr.oop.persistence.SavePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProjectTest {
    String directory = "./OOPTest/";
    AppData appData;
    CreateProjectUseCase createProject;
    LoadPort load;
    SavePort save;

    @BeforeEach
    void setUp() {
        appData = new AppData(new ArrayList<>(), new ArrayList<>());
        load = new PersistenceAdapter(directory);
        save = new PersistenceAdapter(directory);
        createProject = new CreateProjectService();

        final File parent = new File(directory);
        parent.mkdirs();
        File file = new File(directory + AppData.class + ".json");
        file.delete();
    }

    static Stream<Arguments> randomProjects() {
        return Stream.of(
                Arguments.of(
                        RandomTestData.getRandomProject(),
                        RandomTestData.getRandomProject(),
                        RandomTestData.getRandomProject(),
                        RandomTestData.getRandomProject(),
                        RandomTestData.getRandomProject()
                )
        );
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


    @Test
    void getIdTest() {
        UUID id = UUID.randomUUID();
        Project project = new Project(id, null, null, null);
        assertThat(project.getId()).isEqualTo(id);
    }

    @Test
    void getTitleTest() {
        String title = "testTitle";
        Project project = new Project(UUID.randomUUID(), null, title, null);
        assertThat(project.getTitle()).isEqualTo(title);
    }

    @Test
    void getTaskListTest() {
        List<Task> taskList = RandomTestData.getRandomtaskList();
        Project project = new Project(UUID.randomUUID(), taskList, null, null);
        assertThat(project.getTaskList()).isEqualTo(taskList);
    }

    @Test
    void getPermissionsTest() {
        Map<User, Boolean> permissions = RandomTestData.getRandomPermissions();
        Project project = new Project(UUID.randomUUID(), null, null, permissions);
        assertThat(project.getPermissions()).isEqualTo(permissions);
    }

    @ParameterizedTest
    @MethodSource("randomProjectsWithSingleUser")
    void canCreateProject(Project expected, User user) {
        AppData appData = new AppData(new ArrayList<>(), new ArrayList<>());

        createProject.createProject(save, appData, expected.getTitle(), expected.getTaskList(), user);

        Project result = load.loadData().getProjectList().get(0);
        assertThat(result.getTitle()).hasToString(expected.getTitle());
        assertThat(result.getTaskList()).isEqualTo(expected.getTaskList());
        assertThat(result.getPermissions()).isEqualTo(expected.getPermissions());
    }

    @ParameterizedTest
    @MethodSource("randomProjectsWithSingleUser")
    void createProjectAddsOneToProjectList(Project project, User user) {
        AppData appData = new AppData(RandomTestData.getRandomProjects(), RandomTestData.getRandomUsers());
        int originalSize = appData.getProjectList().size();
        createProject.createProject(save, appData, project.getTitle(), project.getTaskList(), user);

        AppData result = load.loadData();
        assertThat(appData.getProjectList().size()).isEqualTo(originalSize+1);
    }
}
