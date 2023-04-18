package hwr.oop.todo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TaskTest {
    @Test
    void createTaskTest() {
        ArrayList<TaskTag> list = new ArrayList<>();
        list.add(new TaskTag("phone"));

        Task task = new Task("Water plants", list, "Water all the plants in the living room and in the bedroom.", LocalDate.of(2023, 05, 30), TaskStatus.TODO, TaskPriority.HIGH);
        assertThat(task.title()).isEqualTo("Water plants");
        assertThat(task.tags()).isEqualTo(list);
        assertThat(task.description()).isEqualTo("Water all the plants in the living room and in the bedroom.");
        assertThat(task.deadline()).isEqualTo(LocalDate.of(2023, 05, 30));
        assertThat(task.status()).isEqualTo(TaskStatus.TODO);
        assertThat(task.priority()).isEqualTo(TaskPriority.HIGH);
    }

}
