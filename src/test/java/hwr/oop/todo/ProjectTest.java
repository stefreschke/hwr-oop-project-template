package hwr.oop.todo;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProjectTest {

    @Test
    void canCreateProject(){
        UUID id = UUID.randomUUID();
        Project task = new Project(id, "Name");

        assertEquals(id, task.getId());
        assertEquals("Name", task.getName());
    }

    @Test
    void canCreateProjectFromProjectData(){
        UUID id = UUID.randomUUID();
        ProjectData data = new ProjectData("Name");

        Project task = Project.fromData(id, data);

        assertEquals(id, task.getId());
        assertEquals(data.getName(), task.getName());
    }

    @Test
    void projectsAreEqualWithEqualContents(){
        UUID id = UUID.randomUUID();
        Project first = new Project(id, "Name");
        Project second = new Project(id, "Name");

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void projectsAreNotEqualWithDifferentContents(){
        UUID id = UUID.randomUUID();
        Project first = new Project(id, "Name_");
        Project second = new Project(id, "Name");

        assertNotEquals(first, second);
        assertNotEquals(first.hashCode(), second.hashCode());
    }


}
