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

        assertEquals("named task", task.getName());
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
    void buildTaskInProject() {
        final Project project = new Project("project", "desc");
        final Task taskInProject = new TaskBuilder().setName("task1").setProject(project).build();
        final Task taskWithoutProject = new TaskBuilder().setName("task2").build();

        assertTrue(project.getTasks().contains(taskInProject));
        assertFalse(project.getTasks().contains(taskWithoutProject));
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

        assertEquals("name", task.getName());
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

    @Test
    void equals() {
        final Task defaultTask = new TaskBuilder().build();
        final Task defaultTask2 = new TaskBuilder().build();
        final Task namedTask = new TaskBuilder().setName("named").build();
        final Task namedTask2 = new TaskBuilder().setName("named").build();
        final Task complexTask = new TaskBuilder()
                .setName("123")
                .setDescription("desc")
                .setPriority(10)
                .addTag(new Tag("1234"))
                .setDeadline(LocalDateTime.of(1970, 1, 1, 0, 0)).build();
        final Task complexTask2 = new TaskBuilder()
                .setName("123")
                .setDescription("desc")
                .setPriority(10)
                .addTag(new Tag("1234"))
                .setDeadline(LocalDateTime.of(1970, 1, 1, 0, 0)).build();

        assertEquals(defaultTask, defaultTask2);
        assertEquals(namedTask, namedTask2);
        assertEquals(complexTask, complexTask2);

        complexTask2.setStatus(Status.CLOSED);

        assertNotEquals(defaultTask, namedTask);
        assertNotEquals(defaultTask, complexTask);
        assertNotEquals(defaultTask, complexTask2);
        assertNotEquals(namedTask, complexTask);
        assertNotEquals(namedTask, complexTask2);
        assertNotEquals(complexTask, complexTask2);
    }
}