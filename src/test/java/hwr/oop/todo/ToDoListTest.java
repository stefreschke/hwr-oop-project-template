package hwr.oop.todo;

import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.project.ProjectData;
import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.tag.TagException;
import hwr.oop.todo.library.todolist.DuplicateIdException;
import hwr.oop.todo.library.todolist.ToDoList;
import hwr.oop.todo.library.todolist.ToDoListException;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.task.TaskFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListTest {

    @Test
    void canStoreTask () {
        ToDoList testController = new ToDoList();

        Task taskData = TaskFactory.createTask("Title");

        testController.addTask(taskData);
    }

    @Test
    void canGetTask() {
        ToDoList testController = new ToDoList();
        Task taskData = TaskFactory.createTask("Title");
        testController.addTask(taskData);
        UUID id = taskData.getId();

        assertNotNull(testController.getTask(id));

    }

    @Test
    void cannotRetrieveTaskWithNonExistingID () {
        ToDoList testController = new ToDoList();
        UUID id = UUID.randomUUID();

        assertThrows(ToDoListException.class, () -> testController.getTask(id));
    }

    @Test
    void canStoreMultipleTasks () {
        ToDoList testController = new ToDoList();

        Task firstTask = TaskFactory.createTask("Title");
        Task secondTask = TaskFactory.createTask("Title2");

        testController.addTask(firstTask);
        testController.addTask(secondTask);
    }

    @Test
    void canStoreMultipleTasksWithSameTitle () {
        ToDoList testController = new ToDoList();

        Task firstTask = TaskFactory.createTask("Title");
        Task secondTask = TaskFactory.createTask("Title");

        testController.addTask(firstTask);
        testController.addTask(secondTask);
    }

    @Test
    void canNotStoreTwoTaskWithSameId(){
        ToDoList toDoList = new ToDoList();

        Task task = TaskFactory.createTask("Title");

        toDoList.addTask(task);

        assertThrows(DuplicateIdException.class, () -> toDoList.addTask(task));
    }
    
    @Test
    void canCreateProject(){
        ToDoList todoList = new ToDoList();
        ProjectData projectData = new ProjectData("Name");

        Project project = todoList.createProject(projectData);

        assertEquals(projectData.getName(), project.getName());
        assertEquals(projectData.getTaskIds(), project.getTaskIds());
    }

    @Test
    void canCreateMultipleProjects(){
        ToDoList todoList = new ToDoList();

        Project project1 = todoList.createProject(new ProjectData("Project1"));
        Project project2 = todoList.createProject(new ProjectData("Project2"));

        assertNotEquals(project1, project2);
    }

    @Test
    void canGetProjectById(){
        ToDoList todoList = new ToDoList();
        ProjectData projectData = new ProjectData("Name");

        Project project = todoList.createProject(projectData);

        assertEquals(project, todoList.getProject(project.getId()));
    }

    @Test
    void cannotGetProjectThatDoesNotExist(){
        ToDoList todoList = new ToDoList();
        assertThrows(ToDoListException.class, () -> todoList.getProject(UUID.randomUUID()));
    }

    @Test
    void canGetTagController(){
        ToDoList toDoList = new ToDoList();
        Assertions.assertNotNull(toDoList.getTags());
    }

    @Test
    void canUpdateTagController() {
        ToDoList toDoList = new ToDoList();
        Tag first = new Tag("Important");
        Tag second = new Tag("Test");
        toDoList.createTag(first);
        toDoList.createTag(second);

        List<Tag> tagList = toDoList.getTags();
        assertEquals(1, Collections.frequency(tagList, first));
        assertEquals(second, tagList.get(1));
    }

    @Test
    void canDeleteTagFromController(){
        ToDoList toDoList = new ToDoList();
        Tag tag = new Tag("Important");

        toDoList.createTag(tag);
        toDoList.removeTag(tag);

        List<Tag> tagList = toDoList.getTags();
        assertEquals(0, Collections.frequency(tagList, "Important"));
    }

    @Test
    void cannotRemoveTagThatDoesNotExist(){
        ToDoList toDoList = new ToDoList();
        Tag tag = new Tag("Name");

        assertThrows(TagException.class, () -> toDoList.removeTag(tag));
    }

    @Test
    void cannotCreateTagThatAlreadyExists(){
        ToDoList toDoList = new ToDoList();
        Tag first = new Tag("Name");
        Tag second = new Tag("Name");

        toDoList.createTag(first);

        assertThrows(TagException.class, () -> toDoList.createTag(second));
    }
}
