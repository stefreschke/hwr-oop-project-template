package hwr.oop.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProjectTest {
    @Test
    void createProjectTest() {
        Project project = new Project();
        Assertions.assertNotEquals(project,null);
    }
}