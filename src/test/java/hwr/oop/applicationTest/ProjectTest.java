package hwr.oop.applicationTest;

import hwr.oop.application.CreateProjectUseCase;
import hwr.oop.application.Project;
import hwr.oop.persistence.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.PersistenceAdapter;
import hwr.oop.persistence.SavePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
        File file = new File(directory + AppData.class + ".json");
        if (file.exists()) {
            file.delete();
        }
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
            randomProjects.add(
                    Arguments.of(
                            new Project(rando.getId(), rando.getTaskList(), rando.getTitle(),
                                    Map.of(RandomTestData.getRandomUser(), Boolean.TRUE)
                            )
                    )
            );
        }
        return  randomProjects.stream();
    }

    @ParameterizedTest
    @MethodSource("randomProjectsWithSingleUser")
    void canCreateProject(Project expected) {
        AppData appData = new AppData(new ArrayList<>(), new ArrayList<>());
        createProject.createProject(save, appData, expected.getTitle(), expected.getTaskList());
        assertThat(load.loadData().getProjectList().get(0)).isEqualTo(expected);
    }

}
