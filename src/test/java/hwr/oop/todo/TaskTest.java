package hwr.oop.todo;

import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.task.TaskFactory;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

 class TaskTest {

    @Test
    void canCreateTaskWithTitle(){
        Task taskData = TaskFactory.createTask("Title");

        String title = taskData.getTitle();

        assertEquals("Title", title);
    }

    @Test
    void canCreateTaskWithDescription(){
        Task taskData = TaskFactory.createTask("Title", "Description");

        String title = taskData.getTitle();
        String desc = taskData.getDescription();

        assertEquals("Title", title);
        assertEquals("Description", desc);
    }

    @Test
    void canAddTags(){
        Task taskData = TaskFactory.createTask("Title", "Description");

        Tag homework = new Tag("Homework");
        Tag important = new Tag("Important");

        taskData.addTag(homework);
        taskData.addTag(important);
        assertIterableEquals(Arrays.asList(homework, important), taskData.getTags());


    }

    @Test
    void canRemoveTags(){
        Task taskData = TaskFactory.createTask("Title", "Description");

        Tag homework = new Tag("Homework");
        Tag important = new Tag("Important");
        Tag birthday = new Tag("Birthday Present");

        taskData.addTag(homework);
        taskData.addTag(important);
        taskData.removeTag(important);
        taskData.addTag(birthday);

        assertIterableEquals(Arrays.asList(homework, birthday), taskData.getTags());
    }

    @Test
    void tasksAreNotEqualWithEqualTitles(){
        Task first = TaskFactory.createTask("First");
        Task second = TaskFactory.createTask("First");

        assertNotEquals(first, second);
        assertNotEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void tasksAreNotEqualWithEqualContents(){
        Task first = TaskFactory.createTask("Title", "Description");
        Task second = TaskFactory.createTask("Title", "Description");

        assertNotEquals(first, second);
        assertNotEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void tasksAreNotEqualWithDifferentContents(){
        Task first = TaskFactory.createTask("Title_", "Description");
        Task second = TaskFactory.createTask("Title", "Description");

        assertNotEquals(first, second);
        assertNotEquals(first.hashCode(), second.hashCode());
    }
}
