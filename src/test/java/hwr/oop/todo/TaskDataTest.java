package hwr.oop.todo;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TaskDataTest {

    @Test
    void canCreateTaskWithTitle(){
        TaskData taskData = new TaskData("Title");

        String title = taskData.getTitle();

        assertEquals("Title", title);
    }

    @Test
    void canCreateTaskWithDescription(){
        TaskData taskData = new TaskData("Title", "Description");

        String title = taskData.getTitle();
        String desc = taskData.getDescription();

        assertEquals("Title", title);
        assertEquals("Description", desc);
    }

    @Test
    void canAddTags(){
        TaskData taskData = new TaskData("Title", "Description");

        Tag homework = new Tag("Homework");
        Tag important = new Tag("Important");

        taskData.addTag(homework);
        taskData.addTag(important);
        assertIterableEquals(Arrays.asList(homework, important), taskData.getTags());


    }

    @Test
    void canRemoveTags(){
        TaskData taskData = new TaskData("Title", "Description");

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
    void tasksAreEqualWithEqualTitles(){
        TaskData first = new TaskData("First");
        TaskData second = new TaskData("First");

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void tasksAreEqualWithEqualContents(){
        TaskData first = new TaskData("Title", "Description");
        TaskData second = new TaskData("Title", "Description");

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void tasksAreNotEqualWithDifferentContents(){
        TaskData first = new TaskData("Title_", "Description");
        TaskData second = new TaskData("Title", "Description");

        assertNotEquals(first, second);
        assertNotEquals(first.hashCode(), second.hashCode());
    }
}
