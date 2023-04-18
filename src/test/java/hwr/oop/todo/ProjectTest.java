package hwr.oop.todo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ProjectTest {
    @Test
    void createProject() {
        Project project = new Project("Test","Unknown","00.00.0000");
        System.out.println("Test, if project is created.");
        assertThat(project).isNotNull();
    }

    @Test
    @Disabled
    void addTask(){
        Project project = new Project("","","");
        /*
        Task task = new Task("");
        project.addTask(task);
        Assertions.assertThat(task).isEqualTo(project.task-list.get(0));
         */
    }

    @Test
    void testStatus(){
        Project project = new Project("","Test","");
        assertThat(project.getStatus()).isEqualTo("Test");
        project.setStatus("Test2");
        assertThat(project.getStatus()).isEqualTo("Test2");
    }

    @Test
    void testDeadline(){
        Project project = new Project("","","00.00.0000");
        assertThat(project.getDeadline()).isEqualTo("00.00.0000");
        String date = "18.04.2023";
        project.setDeadline(date);
        assertThat(date).isEqualTo(project.getDeadline());
    }
}