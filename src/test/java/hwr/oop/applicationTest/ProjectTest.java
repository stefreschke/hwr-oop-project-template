package hwr.oop.applicationTest;

import hwr.oop.application.*;
import hwr.oop.persistence.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class ProjectTest {
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


    @Test
    void getAttributesTest() {
        UUID id = UUID.randomUUID();
        String title = "testTitle";
        List<Task> taskList = RandomTestData.getRandomtaskList();
        Map<User, Boolean> permissions = RandomTestData.getRandomPermissions();

        Project project = new Project(id, taskList, title, permissions);
        appData.addProject(project);
        save.saveData(appData);

        Project result = load.loadProjectById(project.getId());
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getTitle()).isEqualTo(title);
        assertThat(result.getTaskList()).isEqualTo(taskList);
        assertThat(result.getPermissions()).isEqualTo(permissions);
    }

    @Test
    void loadProjectWithWrongId_throwsException(){
        appData = new AppData(RandomTestData.getRandomProjects(), RandomTestData.getRandomUsers());
        save.saveData(appData);

        UUID uuid = UUID.randomUUID();
        try {
            load.loadProjectById(uuid);
            fail("should throw exception");
        } catch (ProjectNotInAppDataException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @MethodSource("randomProjects")
    void equalsTest(Project project) {
        Project copy = new Project(project.getId(), project.getTaskList(), project.getTitle(),
                project.getPermissions());
        assertThat(project).isEqualTo(copy);
        assertThat(copy).isEqualTo(project);
        assertThat(project.hashCode()).isEqualTo(copy.hashCode());
    }

    @ParameterizedTest
    @MethodSource("randomProjects")
    void equals_sameProjectDifferentID_returnsFalseTest(Project project) {
        Project copy = new Project(UUID.randomUUID(), project.getTaskList(), project.getTitle(),
                project.getPermissions());
        assertThat(project).isNotEqualTo(copy);
        assertThat(copy).isNotEqualTo(project);
        assertThat(project.hashCode()).isNotEqualTo(copy.hashCode());
    }

    @ParameterizedTest
    @MethodSource("randomProjects")
    void equals_differentClasses_returnsFalseTest(Project project) {
        Object o = new Object();
        assertThat(project).isNotEqualTo(o);
    }

    @ParameterizedTest
    @MethodSource("randomProjects")
    void equalsSameObjectTest(Project project) {
        assertThat(project).isEqualTo(project);
    }

    @Test
    void wrongId_throwsException() {
        Project project = RandomTestData.getRandomProject();
        appData.addProject(project);
        UUID uuid = UUID.randomUUID();

        try {
            Project result = load.loadProjectById(uuid);
            fail("should throw exception");
        } catch (ProjectNotInAppDataException e) {
            e.printStackTrace();
        }
    }
}
