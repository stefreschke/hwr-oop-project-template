package hwr.oop.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskGetterTest {
    private final TaskBuilder taskBuilder = new TaskBuilder();

    @Test
    void canGetTitle() {
        final Task task = this.taskBuilder
                .setTitle("Title")
                .setDescription("Description")
                .build();
        String taskTitle = task.getTitle();
        assertThat(taskTitle).isEqualTo("Title");
    }

    @Test
    void canGetDescription() {
        final Task task = this.taskBuilder
                .setTitle("Title")
                .setDescription("Description")
                .build();
        String taskDescription = task.getDescription();
        assertThat(taskDescription).isEqualTo("Description");
    }

    @Test
    void canGetStatus() {
        final Task task = this.taskBuilder
                .setTitle("Title")
                .setDescription("Description")
                .build();
        TaskStatus taskStatus = task.getStatus();
        assertThat(taskStatus).isEqualTo(TaskStatus.IN_TRAY);
    }

    @Test
    void canGetPriority() {
        final Task task = this.taskBuilder
                .setTitle("Title")
                .setDescription("Description")
                .build();
        TaskPriority priority = task.getPriority();
        assertThat(priority).isEqualTo(TaskPriority.UNDETERMINED);
    }

    @Test
    void canGetDateCreated() {
        final Task task = this.taskBuilder
                .setTitle("Title")
                .setDescription("Description")
                .build();
        LocalDateTime dateCreated = task.getDateTimeCreated();
        assertThat(dateCreated).isNotNull();
    }
}
