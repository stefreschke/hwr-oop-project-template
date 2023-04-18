package hwr.oop.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskGetterTest {

    @Test
    void canGetTitle() {
        final Task task = new Task("Title", "Description");
        String taskTitle = task.getTitle();
        assertThat(taskTitle).isEqualTo("Title");
    }

    @Test
    void canGetDescription() {
        final Task task = new Task("Title", "Description");
        String taskDescription = task.getDescription();
        assertThat(taskDescription).isEqualTo("Description");
    }

    @Test
    void canGetStatus() {
        final Task task = new Task("Title", "Description");
        TaskStatus taskStatus = task.getStatus();
        assertThat(taskStatus).isEqualTo(TaskStatus.IN_TRACE);
    }

    @Test
    void canGetPriority() {
        final Task task = new Task("Title", "Description");
        TaskPriority priority = task.getPriority();
        assertThat(priority).isEqualTo(TaskPriority.UNDETERMINED);
    }

    @Test
    void canGetDateCreated() {
        final Task task = new Task("Title", "Description");
        LocalDateTime dateCreated = task.getDateTimeCreated();
        assertThat(dateCreated).isNotNull();
    }
}
