package hwr.oop.todo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ProjectTest {
    @Test
    void createProject() {
        Project project = new Project("Test","Unknown","00.00.0000");
        System.out.println("Test, if project is created.");
        Assertions.assertThat(project).isNotEqualTo(null);
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
        Assertions.assertThat("Test").isEqualTo(project.getStatus());
        project.setStatus("Test2");
        Assertions.assertThat("Test2").isEqualTo(project.getStatus());
    }

    @Test
    void testDeadline(){
        Project project = new Project("","","00.00.0000");
        Assertions.assertThat("00.00.0000").isEqualTo(project.getDeadline());
        String date = "18.04.2023";
        project.setDeadline(date);
        Assertions.assertThat(date).isEqualTo(project.getDeadline());
    }
}