package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class TaskTest {
    Task task;
    @BeforeEach
    void setUp() {
        task = new Task("vacuum", "25.05.22", "27.05.22");
    }

    @Test
    void task_getTaskName_returnsGivenTaskName() {
        assertThat(task.getTaskName()).isEqualTo("vacuum");
    }

    @Test
    void task_getDate_returnsGivenDate() {
        assertThat(task.getDate()).isEqualTo("25.05.22");
    }

    @Test
    void task_getDeadline_returnsGivenDeadline() {
        assertThat(task.getDeadline()).isEqualTo("27.05.22");
    }
}
