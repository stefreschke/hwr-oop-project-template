package hwr.oop.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ProjectTest {
    @Test
    void createProject() {
        Project project = new Project();
        Assertions.assertNotEquals(project,null);
    }

    @Test
    @Disabled
    void addTask(){
        Project project = new Project();
    }

    @Test
    @Disabled
    void determineStatus(){
        Project project = new Project();
    }

    @Test
    @Disabled
    void setDeadline(){

    }
}