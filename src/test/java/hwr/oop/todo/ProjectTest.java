package hwr.oop.todo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {
    @Test
    void createProject() {
        Project project = new Project("Test");
        System.out.println("Test, if project is created.");
        assertNotEquals(project,null);
    }

    @Test
    @Disabled
    void addTask(){
        Project project = new Project("");
        /*
        Task task = new Task("");
        project.addTask(task);
        assertEquals(task, project.getTask());
         */
    }

    @Test
    @Disabled
    void determineStatus(){
        Project project = new Project("");
        /*
        Status status = new Status("Done");
        project.setStatus(status);
        assertEquals(status, project.getStatus());
         */
    }

    @Test
    @Disabled
    void setDeadline(){
        Project project = new Project("");
        /*
        Date date = 18.04.2023;
        project.setDate = date;
        assertEquals(dare, project.getDate());
         */
    }
}