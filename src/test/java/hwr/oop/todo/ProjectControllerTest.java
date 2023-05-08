package hwr.oop.todo;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProjectControllerTest {

    @Test
    void canGetProjectController(){
        ProjectController testController = ProjectController.get();

        assertNotNull(testController);
    }

    @Test
    void canGetToDoList(){
        ProjectController testController = new ProjectController();

        assertNotNull(testController.getToDoList());
    }

    @Test
    void canCreateProject() {
        ProjectController testController = new ProjectController();
        ProjectData projectData = new ProjectData("Name");

        Project project = testController.createProject(projectData);

        assertEquals(projectData.getName(), project.getName());
        assertEquals(projectData.getTaskIds(), project.getTaskIds());
    }
    @Test
    void canCreateMultipleProjects(){
        ProjectController testController = new ProjectController();

        Project project1 = testController.createProject(new ProjectData("Project1"));
        Project project2 = testController.createProject(new ProjectData("Project2"));

        assertNotEquals(project1, project2);
    }

    @Test
    void canGetProjectById() {
        ProjectController testController = new ProjectController();
        ProjectData projectData = new ProjectData("Name");

        Project project = testController.createProject(projectData);

        assertEquals(project, testController.getProject(project.getId()));
    }

    @Test
    void cannotGetProjectThatDoesNotExist() {
        ProjectController testController = new ProjectController();
        assertThrows(ToDoListException.class, () -> testController.getProject(UUID.randomUUID()));
    }
}