package hwr.oop.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class ProjectTest {
    @Test
    void testProject() {
        Assertions.assertDoesNotThrow(() -> new Project("Test",LocalDate.of(2000,1,1)));
    }

    @Test
    @Disabled
    void addTask(){
        Project project = new Project("",LocalDate.of(2000,1,1));
        /*
        Task task = new Task("");
        project.addTask(task);
        Assertions.assertThat(task).isEqualTo(project.task-list.get(0));
         */
    }

    @Test
    void testDeadline(){
        Project project = new Project("",null);
        assertThat(project.getDeadline()).isEqualTo("00.00.0000");
        LocalDate date = LocalDate.of(2024, 4, 4);
        project.setDeadline(date);
        assertThat(date).isEqualTo(project.getDeadline());
    }
}