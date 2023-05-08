package hwr.oop.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TaskControllerTest {

    @Test
    void canGetTaskController(){
        TaskController testController = TaskController.get();
        Assertions.assertNotNull(testController);
    }

    @Test
    void canGetToDoList(){
        TaskController testController = TaskController.get();
        Assertions.assertNotNull(testController.getToDoList());
    }

    @Test
    void canStoreTask() {
        TaskController testController = new TaskController();
        TaskData taskData = new TaskData("Title");

        Task task = testController.addTask(taskData);
        UUID id = task.getId();

        assertEquals(task, testController.getTask(id));
    }


    @Test
    void cannotRetrieveTaskWithNonExistingID() {
        TaskController testController = new TaskController();
        UUID id = UUID.randomUUID();

        assertThrows(ToDoListException.class, () -> testController.getTask(id));
    }


    @Test
    void canStoreMultipleTasks() {
        TaskController testController = new TaskController();

        Task task1 = testController.addTask(new TaskData("Title"));
        Task task2 = testController.addTask(new TaskData("Title2"));

        assertNotEquals(task1, task2);
    }


    @Test
    void canStoreMultipleTasksWithSameTitle() {
        TaskController testController = new TaskController();

        Task task1 = testController.addTask(new TaskData("Title"));
        Task task2 = testController.addTask(new TaskData("Title"));

        assertNotEquals(task1, task2);
    }


}