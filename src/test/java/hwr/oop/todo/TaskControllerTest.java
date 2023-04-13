package hwr.oop.todo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.UUID;

public class TaskControllerTest {

    @Test
    void CanStoreTask () {
        TaskController testController = new TaskController();
        Task task = new Task("Title");

        UUID id = testController.addTask(task);

        Assertions.assertEquals(task, testController.getTask(id));

    }

    @Test
    void CannotRetrieveTaskWithNonExistingID () {
        TaskController testController = new TaskController();
        UUID id = UUID.randomUUID();

        Assertions.assertNull(testController.getTask(id));
    }

    @Test
    void CanStoreMultipleTasks () {
        TaskController testController = new TaskController();
        Task task1 = new Task("Title");
        Task task2 = new Task("Title2");

        UUID id1 = testController.addTask(task1);
        UUID id2 = testController.addTask(task2);

        Assertions.assertNotEquals(id1, id2);
    }
}
