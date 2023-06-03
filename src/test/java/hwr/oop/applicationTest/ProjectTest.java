package hwr.oop.applicationTest;

import hwr.oop.application.CreateProjectService;
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

import java.io.File;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProjectTest {

    AppData appData;
    LoadPort load;

    @BeforeEach
    void setUp() {
        appData = new AppData(new ArrayList<>(), new ArrayList<>(),"./OOPTest");
        load = new PersistenceAdapter(appData);
        File file = new File(appData.getFilePath());
        if (file.exists()) {
            file.delete();
        }
    }

    static Stream<Arguments> randomProjects() {
        return Stream.of(
                Arguments.of(
                        RandomTestData.getRandomProject(),
                        RandomTestData.getRandomProject(),
                        RandomTestData.getRandomProject()
                )
        );
    }

    @ParameterizedTest
    @MethodSource("randomProjects")
    void canCreateProject(Project expected) {
        CreateProjectUseCase createProject = new CreateProjectService(appData);
        createProject.createProject(expected.getTitle(), expected.getTaskList(), expected.getPermissions());
        AppData resultAppData = load.loadData();
        Project result = resultAppData.getProjectList().get(0);
        assertThat(result).isEqualTo(expected);
    }

}
