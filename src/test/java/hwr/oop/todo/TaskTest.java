package hwr.oop.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    void canCreateTaskWithTitle(){
        Task task = new Task("Title");

        String title = task.getTitle();

        assertEquals("Title", title);
    }

    @Test
    void canCreateTaskWithDescription(){
        Task task = new Task("Title", "Description");

        String title = task.getTitle();
        String desc = task.getDescription();

        assertEquals("Title", title);
        assertEquals("Description", desc);
    }

    @Test
    void canAddAndRemoveTags(){
        Task task = new Task("Title", "Description");

        Tag homework = new Tag("Homework");
        Tag important = new Tag("Important");
        Tag birthday = new Tag("Birthday Present");

        task.addTag(homework);
        task.addTag(important);
        assertIterableEquals(Arrays.asList(homework, important), task.getTags());

        task.removeTag(important);
        task.addTag(birthday);
        assertIterableEquals(Arrays.asList(homework, birthday), task.getTags());
    }

    @Test
    void tasksAreEqualWithEqualTitles(){
        Task first = new Task("First");
        Task second = new Task("First");

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void tasksAreEqualWithEqualContents(){
        Task first = new Task("Title", "Description");
        Task second = new Task("Title", "Description");

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void tasksAreNotEqualWithDifferentContents(){
        Task first = new Task("Title_", "Description");
        Task second = new Task("Title", "Description");

        assertNotEquals(first, second);
        assertNotEquals(first.hashCode(), second.hashCode());
    }
}
