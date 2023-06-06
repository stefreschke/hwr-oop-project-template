package hwr.oop.applicationTest;

import hwr.oop.application.*;
import hwr.oop.persistence.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.SavePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



public class ChangeTitleTest {

    private LoadPort loadPort;
    private SavePort savePort;
    private ChangeTitleService changeTitleService;
    AppData appDataMock;
    @BeforeEach
    void setUp() {

        List<Project> projects = new ArrayList<>();
        UUID id = UUID.randomUUID();
        Project project = new Project(id,new ArrayList<>(),"Test Project",null);
        projects.add(project);

        List<User> users = new ArrayList<>();
        users.add(new User(UUID.randomUUID(),"TestUser",new ArrayList<>(),new ArrayList<>()));

        appDataMock = new AppData(projects,users);

        loadPort = () -> appDataMock;
        savePort = appData -> appDataMock = appData;

        changeTitleService = new ChangeTitleService(loadPort, savePort);
    }
    @Test
    void changeTitleSuccesfully(){
        Project project= loadPort.loadData().getProjectList().get(0);
        assertThat(loadPort.loadData().getProjectList().get(0).getTitle()).isEqualTo("Test Project");
        changeTitleService.changeTitle(project,"Title");
        assertThat(loadPort.loadData().getProjectList().get(0).getTitle()).isEqualTo("Title");
    }
}
