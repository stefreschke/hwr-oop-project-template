package hwr.oop.applicationTest;

import hwr.oop.application.CreateProjectService;
import hwr.oop.application.CreateProjectUseCase;
import hwr.oop.application.Task;
import hwr.oop.application.User;
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
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserTest {

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

    static Stream<Arguments> randomUsers() {
        return Stream.of(
                Arguments.of(
                        RandomTestData.getRandomUser(),
                        RandomTestData.getRandomUser(),
                        RandomTestData.getRandomUser(),
                        RandomTestData.getRandomUser(),
                        RandomTestData.getRandomUser()
                )
        );
    }

    @Test
    void getNameTest() {
        UUID uuid = UUID.randomUUID();
        String name = "testName";
        User user = new User(uuid, name, null, null);
        appData.addUser(user);
        save.saveData(appData);
        assertThat(load.loadUserbyId(uuid).getName()).hasToString(name);
    }

    @Test
    void getInboxTest() {
        UUID uuid = UUID.randomUUID();
        List<String> inbox = RandomTestData.getRandomTaskTitles();
        User user = new User(uuid, null, inbox, null);
        appData.addUser(user);
        save.saveData(appData);
        assertThat(load.loadUserbyId(uuid).getInbox()).isEqualTo(inbox);
    }

    @Test
    void getContextListTest() {
        UUID uuid = UUID.randomUUID();
        List<Task> contextList = RandomTestData.getRandomtaskList();
        User user = new User(uuid, null, null, contextList);
        appData.addUser(user);
        save.saveData(appData);
        assertThat(load.loadUserbyId(uuid).getContextList()).isEqualTo(contextList);
    }

    @ParameterizedTest
    @MethodSource("randomUsers")
    void equalsTest(User user) {
        User copy = new User(user.getId(), user.getName(), user.getInbox(), user.getContextList());
        assertThat(copy).isEqualTo(user);
        assertThat(user).isEqualTo(copy);
        assertThat(user.hashCode()).isEqualTo(copy.hashCode());
    }

    @ParameterizedTest
    @MethodSource("randomUsers")
    void equalsSameObjectTest(User user) {
        assertThat(user).isEqualTo(user);
    }

    @ParameterizedTest
    @MethodSource("randomUsers")
    void differentId_doesNotEqual(User user) {
        User copy = new User(UUID.randomUUID(), user.getName(), user.getInbox(), user.getContextList());
        assertThat(user).isNotEqualTo(copy);
        assertThat(copy).isNotEqualTo(user);
        assertThat(user.hashCode()).isNotEqualTo(copy.hashCode());
    }

    @ParameterizedTest
    @MethodSource("randomUsers")
    void differentClasses_doesNotEqual(User user) {
        assertThat(user).isNotEqualTo(new Object());
        assertThat(new Object()).isNotEqualTo(user);
    }
}
