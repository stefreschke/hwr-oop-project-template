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

        task.addTag("Homework");
        task.addTag("Important");
        Assertions.assertEquals("[Homework, Important]", task.getTags()+"");
        Assertions.assertIterableEquals(Arrays.asList("Homework", "Important"), task.getTags());

        task.removeTag("Important");
        task.addTag("Birthday Present");
        Assertions.assertIterableEquals(Arrays.asList("Homework", "Birthday Present"), task.getTags());
    }
}
