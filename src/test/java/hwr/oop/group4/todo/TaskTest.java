package hwr.oop.group4.todo;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskTest {

    @Test
    void createTask() {
        var createdAt = LocalDateTime.now();
        Task task = new Task("aName", "aDesc", 3, createdAt, new HashSet<>());
        assertThat(task.getName()).isEqualTo("aName");
        assertThat(task.getDescription()).isEqualTo("aDesc");
        assertThat(task.getPriority()).isEqualTo(3);
        assertThat(task.getTags().size()).isEqualTo(0);
    }

    @Test
    void setters() {
        var createdAt = LocalDateTime.now();
        Task task = new Task("aName", "aDesc", 3, createdAt, new HashSet<>());
        task.setName("bName");
        assertThat(task.getName()).isEqualTo("bName");
        task.setDescription("bDesc");
        assertThat(task.getDescription()).isEqualTo("bDesc");
        task.setPriority(4);
        assertThat(task.getPriority()).isEqualTo(4);
        var updatedAt = createdAt.plusDays(3);
        task.setDeadline(updatedAt);
        assertThat(task.getDeadline()).isEqualTo(updatedAt);
        var myTag = new Tag("myTag");
        task.addTag(myTag);
        assertThat(task.getTags().size()).isEqualTo(1);
        assertTrue(task.getTags().contains(myTag));
    }
}
