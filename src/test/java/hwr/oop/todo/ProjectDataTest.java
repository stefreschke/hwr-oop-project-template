package hwr.oop.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectDataTest {

    @Test
    void canCreateProjectWithName(){
        ProjectData projectData = new ProjectData("Name");

        String name = projectData.getName();

        assertEquals("Name", name);
    }

    @Test
    void canAddTaskById(){
        Project project = new Project(UUID.randomUUID(), "Name");

        UUID id = UUID.randomUUID();
        Task task = new Task(id, "Title", "Description");

        project.addTask(id);

        Assertions.assertIterableEquals(Arrays.asList(id), project.getTaskIds());
    }

    @Test
    void canAddTask(){
        Project project = new Project(UUID.randomUUID(), "Name");

        UUID id = UUID.randomUUID();
        Task task = new Task(id, "Title", "Description");

        project.addTask(task);

        Assertions.assertIterableEquals(Arrays.asList(id), project.getTaskIds());
    }

    @Test
    void projectsAreEqualWithEqualNames(){
        ProjectData first = new ProjectData("Name");
        ProjectData second = new ProjectData("Name");

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void projectsAreEqualWithEqualContents(){
        UUID id = UUID.randomUUID();
        ProjectData first = new ProjectData("Name");
        ProjectData second = new ProjectData("Name");

        first.addTask(id);
        second.addTask(id);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void tasksAreNotEqualWithDifferentTitles(){
        ProjectData first = new ProjectData("Name_");
        ProjectData second = new ProjectData("Name");

        assertNotEquals(first, second);
        assertNotEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void tasksAreNotEqualWithDifferentContents(){
        ProjectData first = new ProjectData("Name");
        ProjectData second = new ProjectData("Name");

        first.addTask(UUID.randomUUID());
        second.addTask(UUID.randomUUID());

        assertNotEquals(first, second);
        assertNotEquals(first.hashCode(), second.hashCode());
    }
}
