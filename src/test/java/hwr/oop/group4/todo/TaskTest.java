package hwr.oop.group4.todo;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void buildDefaultTask() {
        final Task task = new Task.TaskBuilder().build();

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
        final Task task = new Task.TaskBuilder()
                .name("named task")
                .description("desc")
                .priority(10)
                .deadline(deadline)
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
        final Task task = new Task.TaskBuilder().fromIdea(idea).build();

        assertEquals("Name from idea", task.getName());
        assertEquals("name from Description", task.getDescription());
    }

    @Test
    void buildTaskInProject() {
        final Project project = new Project.ProjectBuilder().name("project").description("desc").build();
        final Task taskInProject = new Task.TaskBuilder().name("task1").project(project).build();
        final Task taskWithoutProject = new Task.TaskBuilder().name("task2").build();

        assertTrue(project.getTasks().contains(taskInProject));
        assertFalse(project.getTasks().contains(taskWithoutProject));
    }

    @Test
    void equals() {
        final Task defaultTask = new Task.TaskBuilder().build();
        final Task defaultTask2 = new Task.TaskBuilder().build();
        final Task namedTask = new Task.TaskBuilder().name("named").build();
        final Task namedTask2 = new Task.TaskBuilder().name("named").build();
        final Task complexTask = new Task.TaskBuilder()
                .name("123")
                .description("desc")
                .priority(10)
                .addTag(new Tag("1234"))
                .deadline(LocalDateTime.of(1970, 1, 1, 0, 0)).build();
        final Task complexTask2 = new Task.TaskBuilder()
                .name("123")
                .description("desc")
                .priority(10)
                .addTag(new Tag("1234"))
                .deadline(LocalDateTime.of(1970, 1, 1, 0, 0)).build();

        assertEquals(defaultTask, defaultTask2);
        assertEquals(namedTask, namedTask2);
        assertEquals(complexTask, complexTask2);

        complexTask2.closed();

        assertNotEquals(defaultTask, namedTask);
        assertNotEquals(defaultTask, complexTask);
        assertNotEquals(defaultTask, complexTask2);
        assertNotEquals(namedTask, complexTask);
        assertNotEquals(namedTask, complexTask2);
        assertNotEquals(complexTask, complexTask2);
    }
}