package hwr.oop.todo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class TaskControllerTest {

    @Test
    void canStoreTask () {
        TaskController testController = new TaskController();
        Task task = new Task("Title");

        UUID id = testController.addTask(task);

        assertEquals(task, testController.getTask(id));
    }

    @Test
    void cannotRetrieveTaskWithNonExistingID () {
        TaskController testController = new TaskController();
        UUID id = UUID.randomUUID();

        assertThrows(ToDoListException.class, () -> {
            testController.getTask(id);
        });
    }

    @Test
    void canStoreMultipleTasks () {
        TaskController testController = new TaskController();
        Task task1 = new Task("Title");
        Task task2 = new Task("Title2");

        UUID id1 = testController.addTask(task1);
        UUID id2 = testController.addTask(task2);

        assertNotEquals(id1, id2);
    }
}
