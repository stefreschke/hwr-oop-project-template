package hwr.oop.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskTest {

    @Test
    void CanCreateTaskWithTitle(){
        Task task = new Task("Title");

        String title = task.getTitle();

        Assertions.assertEquals("Title", title);
    }

    @Test
    void CanCreateTaskWithDescription(){
        Task task = new Task("Title", "Description");

        String title = task.getTitle();
        String desc = task.getDescription();

        Assertions.assertEquals("Title", title);
        Assertions.assertEquals("Description", desc);
    }

    @Test
    void CanAddAndRemoveTags(){
        Task task = new Task("Title", "Description");

        Tag homework = new Tag("Homework");
        Tag important = new Tag("Important");
        Tag birthday = new Tag("Birthday Present");

        task.addTag(homework);
        task.addTag(important);
        Assertions.assertIterableEquals(Arrays.asList(homework, important), task.getTags());

        task.removeTag(important);
        task.addTag(birthday);
        Assertions.assertIterableEquals(Arrays.asList(homework, birthday), task.getTags());
    }
}
