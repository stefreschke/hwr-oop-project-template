package hwr.oop.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    void CanCreateTaskWithTitle(){
        Task task = new Task("Title");

        String title = task.getTitle();

        Assertions.assertEquals("Title", title);
    }

    @Test
    void CanCreateTaskWithDescription(){
        Task task = new Task("Title", "Description");

        String title = task.getTitle();
        String desc = task.getDescription();

        Assertions.assertEquals("Title", title);
        Assertions.assertEquals("Description", desc);
    }
}
