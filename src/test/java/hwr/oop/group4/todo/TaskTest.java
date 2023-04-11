package hwr.oop.group4.todo;

import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class TaskTest {
    void createTask() {
        var createdAt = LocalDateTime.now();
        Task task = new Task("aName", "aDesc", 3, createdAt, new HashSet<>());
        assertThat(task.getName()).isEqualTo("aName");
        assertThat(task.getDescription()).isEqualTo("aDesc");
        assertThat(task.getPriority()).isEqualTo(3);
        assertThat(task.getTags().size()).isEqualTo(0);
    }
}
