package hwr.oop.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class TaskTest {

    @Test
    void CanCreateTaskWithTitle(){
        TaskData data = new TaskData("Title");
        Task task = new Task(data, "id:123");

        String title = task.getTitle();

        Assertions.assertEquals("Title", title);
    }

    @Test
    void CanCreateTaskWithDescription(){
        TaskData data = new TaskData("Title", "Description");
        Task task = new Task(data, "id:123");

        String title = task.getTitle();
        String desc = task.getDescription();

        Assertions.assertEquals("Title", title);
        Assertions.assertEquals("Description", desc);
    }
}
