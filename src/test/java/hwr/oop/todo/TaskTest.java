package hwr.oop.todo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

public class TaskTest {
    @Test
    void createTaskTest() {
        Task task = new Task("Water plants", new ArrayList<TaskTag>(), "Water all the plants in the living room and in the bedroom.", "2023-05-30", TaskStatus.TODO, TaskPriority.HIGH);
        assertThat(task.title).isEqualTo("Water plants");
        assertThat(task.tags).isEqualTo(new ArrayList<TaskTag>());
        assertThat(task.description).isEqualTo("Water all the plants in the living room and in the bedroom.");
        assertThat(task.deadline).isEqualTo("2023-05-30");
        assertThat(task.status).isEqualTo(TaskStatus.TODO);
        assertThat(task.priority).isEqualTo(TaskPriority.HIGH);
    }

}
