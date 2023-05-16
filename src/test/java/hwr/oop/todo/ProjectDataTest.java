package hwr.oop.todo;

import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.project.ProjectData;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.task.TaskFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
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

        Task task = TaskFactory.createTask("Title", "Description");
        project.addTask(task);

        List<UUID> ids = Arrays.asList(task.getId());

        Assertions.assertIterableEquals(ids, project.getTaskIds());
    }

    @Test
    void canAddTask(){
        Project project = new Project(UUID.randomUUID(), "Name");

        Task task = TaskFactory.createTask("Title", "Description");
        project.addTask(task);

        List<UUID> ids = Arrays.asList(task.getId());

        Assertions.assertIterableEquals(ids, project.getTaskIds());
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
