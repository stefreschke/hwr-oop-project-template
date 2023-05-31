package hwr.oop.applicationTest;

import hwr.oop.application.CreateProjectUseCase;
import hwr.oop.application.Project;
import hwr.oop.persistence.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.PersistenceAdapter;
import hwr.oop.persistence.SavePort;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProjectTest {
    CreateProjectUseCase projectCreation;
    LoadPort load = new PersistenceAdapter("./OOPTest");
    SavePort save = new PersistenceAdapter("./OOPTest");

    static Stream<Arguments> randomProject() {
        return Stream.of(Arguments.of(RandomTestData.getRandomProject()));
    }

    @ParameterizedTest
    @MethodSource("randomProject")
    void canCreateProject(Project expected) {
        AppData appData = new AppData(new ArrayList<>(), new ArrayList<>());
        projectCreation.createProject(appData, expected.getTitle(), expected.getTaskList(), expected.getPermissions());
        assertThat(load.loadData().getProjectList().get(0)).isEqualTo(expected);
    }
}
