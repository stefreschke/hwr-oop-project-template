package hwr.oop.todo;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListTest {

    @Test
    void canStoreTask () {
        ToDoList testController = new ToDoList();
        TaskData taskData = new TaskData("Title");

        Task task = testController.addTask(taskData);
        UUID id = task.getId();

        assertEquals(task, testController.getTask(id));
    }

    @Test
    void cannotRetrieveTaskWithNonExistingID () {
        ToDoList testController = new ToDoList();
        UUID id = UUID.randomUUID();

        assertThrows(ToDoListException.class, () -> testController.getTask(id));
    }

    @Test
    void canStoreMultipleTasks () {
        ToDoList testController = new ToDoList();

        Task task1 = testController.addTask(new TaskData("Title"));
        Task task2 = testController.addTask(new TaskData("Title2"));

        assertNotEquals(task1, task2);
    }

    @Test
    void canStoreMultipleTasksWithSameTitle () {
        ToDoList testController = new ToDoList();

        Task task1 = testController.addTask(new TaskData("Title"));
        Task task2 = testController.addTask(new TaskData("Title"));

        assertNotEquals(task1, task2);
    }
    
    @Test
    void canCreateProject(){
        ToDoList todoList = new ToDoList();
        ProjectData projectData = new ProjectData("Name");

        Project project = todoList.createProject(projectData);

        assertEquals(projectData.getName(), project.getName());
        assertEquals(projectData.getTaskIds(), project.getTaskIds());
    }

    @Test
    void canGetProjectById(){
        ToDoList todoList = new ToDoList();
        ProjectData projectData = new ProjectData("Name");

        Project project = todoList.createProject(projectData);

        assertEquals(project, todoList.getProject(project.getId()));
    }

    @Test
    void cannotGetProjectThatDoesNotExist(){
        ToDoList todoList = new ToDoList();
        assertThrows(ToDoListException.class, () -> todoList.getProject(UUID.randomUUID()));
    }
}
