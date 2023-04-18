package hwr.oop.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

class ProjectTest {
    @Test
    void testProject() {
        Assertions.assertDoesNotThrow(() -> new Project("Test", null,LocalDate.of(2000, 1, 1)));
        Project project = new Project("Test", null,LocalDate.of(2000, 1, 1));
        assertThat(project.Title()).isEqualTo("Test");
    }

    @Test
    void testTask() {
        ArrayList<Task> task_list = new ArrayList<Task>();
        Project project = new Project("", task_list, LocalDate.of(2000, 1, 1));


        project.addTask(new Task(null,null,null,null,null, null));
        assertThat(project.getTasklist().get(0)).isNotNull();
    }

    @Test
    void testDeadline() {
        Project project = new Project("", null, LocalDate.of(2000, 1, 1));
        assertThat(project.getDeadline()).isEqualTo(LocalDate.of(2000, 1, 1));
        LocalDate date = LocalDate.of(2024, 4, 4);
        project.setDeadline(date);
        assertThat(date).isEqualTo(project.getDeadline());
    }
}