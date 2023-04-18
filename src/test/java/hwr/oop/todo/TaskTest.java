package hwr.oop.todo;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TaskTest {

    @Test
    void canCreateTask(){
        UUID id = UUID.randomUUID();
        Task task = new Task(id, "Title", "Description");

        assertEquals(id, task.getId());
        assertEquals("Title", task.getTitle());
        assertEquals("Description", task.getDescription());
    }

    @Test
    void canCreateTaskFromTaskData(){
        UUID id = UUID.randomUUID();
        TaskData data = new TaskData("Title", "Description");

        Task task = Task.fromData(id, data);

        assertEquals(id, task.getId());
        assertEquals(data.getTitle(), task.getTitle());
        assertEquals(data.getDescription(), task.getDescription());
    }

    @Test
    void tasksAreEqualWithEqualContents(){
        UUID id = UUID.randomUUID();
        Task first = new Task(id, "First", "First");
        Task second = new Task(id, "First", "First");

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void tasksAreNotEqualWithDifferentContents(){
        UUID id = UUID.randomUUID();
        Task first = new Task(id, "First_", "First");
        Task second = new Task(id, "First", "First");

        assertNotEquals(first, second);
        assertNotEquals(first.hashCode(), second.hashCode());
    }


}
