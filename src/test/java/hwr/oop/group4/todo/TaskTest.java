package hwr.oop.group4.todo;

import hwr.oop.group4.todo.builder.TaskBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void buildDefaultTask() {
        final Task task = new TaskBuilder().build();

        assertEquals("unnamed task", task.getName());
        assertEquals("", task.getDescription());
        assertEquals(0, task.getPriority());
        assertNull(task.getDeadline());
        assertEquals(0, task.getTags().size());
        assertEquals(Status.OPEN, task.getStatus());
    }

    @Test
    void buildTask() {
        final LocalDateTime deadline = LocalDateTime.now().plusMonths(2);
        final Tag testTagA = new Tag("tagA");
        final Tag testTagB = new Tag("123");
        final Tag testTagC = new Tag("2345");
        final Task task = new TaskBuilder()
                .setName("named task")
                .setDescription("desc")
                .setPriority(10)
                .setDeadline(deadline)
                .addTag(testTagA)
                .addTags(testTagB, testTagC)
                .build();

        assertEquals( "named task", task.getName());
        assertEquals("desc", task.getDescription());
        assertEquals(10, task.getPriority());
        assertEquals(deadline, task.getDeadline());
        assertEquals(3, task.getTags().size());
        assertTrue(task.getTags().contains(testTagA));
        assertTrue(task.getTags().contains(testTagB));
        assertTrue(task.getTags().contains(testTagC));
        assertEquals(task.getStatus(), Status.OPEN);
    }

    @Test
    void buildFromIdea() {
        final Idea idea = new Idea("Name from idea", "name from Description");
        final Task task = new TaskBuilder().fromIdea(idea).build();

        assertEquals("Name from idea", task.getName());
        assertEquals("name from Description", task.getDescription());
    }

    @Test
    void setters() {
        final LocalDateTime deadline = LocalDateTime.now().plusMonths(10);
        final Tag testTagA = new Tag("tagA");
        final Tag testTagB = new Tag("123");
        final Task task = new TaskBuilder().build();

        task.setName("name");
        task.setDescription("description");
        task.setPriority(4);
        task.setDeadline(deadline);
        task.addTag(testTagA);
        task.addTag(testTagB);
        task.setStatus(Status.IN_PROGRESS);

        assertEquals( "name", task.getName());
        assertEquals("description", task.getDescription());
        assertEquals(4, task.getPriority());
        assertEquals(deadline, task.getDeadline());
        assertEquals(2, task.getTags().size());
        assertTrue(task.getTags().contains(testTagA));
        assertTrue(task.getTags().contains(testTagB));
        assertEquals(task.getStatus(), Status.IN_PROGRESS);

        task.setStatus(Status.CLOSED);

        assertEquals(task.getStatus(), Status.CLOSED);
    }
}
