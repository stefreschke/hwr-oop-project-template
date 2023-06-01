package hwr.oop.applicationTest;

import hwr.oop.application.*;
import hwr.oop.persistence.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.SavePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class CreateTaskTest {

    private LoadPort loadPort;
    private SavePort savePort;
    private CreateTaskService createTaskService;

    AppData appDataMock;
    @BeforeEach
    void setUp() {

        List<Project> projects = new ArrayList<>();
        projects.add(new Project(new ArrayList<>(),"Test Project",null));

        Task taskTmp = new Task("TestTask","This is a task for testing", TaskState.DONE,null);
        projects.get(0).getTaskList().add(taskTmp);

        List<User> users = new ArrayList<>();
        users.add(new User("TestUser",new ArrayList<>(),new ArrayList<>()));

        appDataMock = new AppData(projects,users);
        loadPort = new LoadPort() {
            @Override
            public AppData loadData() {
                return appDataMock;
            }
        };
        savePort = new SavePort() {
            @Override
            public void saveData(AppData appData) {
                 appDataMock = appData;
            }
        };

        createTaskService = new CreateTaskService(loadPort,savePort);
    }

    @Test
    void testCreateTaskInProject() {
        // Arrange
        String title = "Task Title";
        String content = "Task Content";
        TaskState taskState = TaskState.IN_PROGRESS;
        LocalDateTime deadline = LocalDateTime.now();
        Project project =loadPort.loadData().getProjectList().get(0);

        createTaskService.createTaskInProject(title,content,taskState,deadline,project);

        Task createdTask = loadPort.loadData().getProjectList().get(0).getTaskList().get(1);

        assertThat(createdTask.getTitle()).isEqualTo(title);
        assertThat(createdTask.getContent()).isEqualTo(content);
        assertThat(createdTask.getTaskState()).isEqualTo(taskState);
        assertThat(createdTask.getDeadline()).isEqualTo(deadline);
    }

    @Test
     void testCreateTaskInProject_ThrowsExceptionWhenProjectNotFound() {
        // Arrange
        String title = "Task Title";
        String content = "Task Content";
        TaskState taskState = TaskState.IN_PROGRESS;
        LocalDateTime deadline = LocalDateTime.now();

        // Act & Assert
        assertThatThrownBy(() -> createTaskService.createTaskInProject(title, content, taskState, deadline, project))
                .isInstanceOf(CreateTaskException.class)
                .hasMessage("Project not found");

    }

    @Test
     void testCreateTaskInContextList() {
        // Arrange
        String title = "Task Title";
        String content = "Task Content";
        TaskState taskState = TaskState.IN_PROGRESS;
        LocalDateTime deadline = LocalDateTime.now();


        assertThat(user.getContextList()).hasSize(1);
        Task createdTask = user.getContextList().get(0);
        assertThat(createdTask.getTitle()).isEqualTo(title);
        assertThat(createdTask.getContent()).isEqualTo(content);
        assertThat(createdTask.getTaskState()).isEqualTo(taskState);
        assertThat(createdTask.getDeadLine()).isEqualTo(deadline);
    }

    @Test
     void testCreateTaskInContextList_ThrowsExceptionWhenUserNotFound() {
        // Arrange
        String title = "Task Title";
        String content = "Task Content";
        TaskState taskState = TaskState.IN_PROGRESS;
        LocalDateTime deadline = LocalDateTime.now();


        // Act & Assert
        assertThatThrownBy(() -> createTaskService.createTaskInContextList(title, content, taskState, deadline, user))
                .isInstanceOf(CreateTaskException.class)
                .hasMessage("User not found");

    }
}