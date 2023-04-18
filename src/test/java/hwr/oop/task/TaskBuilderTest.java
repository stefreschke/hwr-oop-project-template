package hwr.oop.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class TaskBuilderTest {
    final TaskBuilder taskBuilder = new TaskBuilder();

    @Test
    void canSetDeadlineOnBuild() {
        final LocalDateTime deadline = LocalDateTime.now();
        final Task task = this.taskBuilder
                .setTitle("Title")
                .setDescription("Description")
                .setDateTimeDeadline(deadline)
                .build();
        assertThat(task.getDateTimeDeadline()).isEqualTo(deadline);
    }

    @Test
    void canSetPriorityOnBuild() {
        final Task task = taskBuilder
                .setTitle("Title")
                .setDescription("Description")
                .setPriority(TaskPriority.LOW)
                .build();
        assertThat(task.getPriority()).isEqualTo(TaskPriority.LOW);
    }
}
